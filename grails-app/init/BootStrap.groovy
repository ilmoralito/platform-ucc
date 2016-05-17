import ni.edu.uccleon.*
import grails.util.Environment
import grails.util.DomainBuilder
import static java.util.Calendar.*

class BootStrap {
    def employeeService
    def classroomService

    def init = { servletContext ->
         if (Environment.DEVELOPMENT) {
            development()
        }

        if (Environment.PRODUCTION) {
            production()
        }
    }

    private development() {
        DomainBuilder builder = new DomainBuilder()
        Date today = new Date()
        List<ExternalCustomer> externalCustomers = []
        List<Activity> activies = []

        builder.classNameResolver = "ni.edu.uccleon"

        Role adminRole = new Role("ROLE_ADMIN").save failOnError: true
        Role administrativeSupervisorRole = new Role("ROLE_ADMINISTRATIVE_SUPERVISOR").save failOnError: true
        Role academicSupervisorRole = new Role("ROLE_ACADEMIC_SUPERVISOR").save failOnError: true
        Role protocolCoordinatorRole = new Role("ROLE_PROTOCOL_COORDINATOR").save failOnError: true
        Role userRole = new Role("ROLE_USER").save failOnError: true

        Map JR = employeeService.getEmployee(1)
        Map RL = employeeService.getEmployee(2)
        Map OG = employeeService.getEmployee(3)
        Map CV = employeeService.getEmployee(4)
        Map JM = employeeService.getEmployee(5)
        Map SC = employeeService.getEmployee(6)

        User jrUser = new User(
            JR.fullName,
            JR.id,
            JR.institutionalMail,
            "password"
        ).save failOnError: true

        User rlUser = new User(
            RL.fullName,
            RL.id,
            RL.institutionalMail,
            "password"
        ).save failOnError: true

        User ogUser = new User(
            OG.fullName,
            OG.id,
            OG.institutionalMail,
            "password"
        ).save failOnError: true

        User cvUser = new User(
            CV.fullName,
            CV.id,
            CV.institutionalMail,
            "password"
        ).save failOnError: true

        User jmUser = new User(
            JM.fullName,
            JM.id,
            JM.institutionalMail,
            "password"
        ).save failOnError: true

        User scUser = new User(
            SC.fullName,
            SC.id,
            SC.institutionalMail,
            "password"
        ).save failOnError: true

        UserRole.create jrUser, administrativeSupervisorRole, true
        UserRole.create rlUser, academicSupervisorRole, true
        UserRole.create ogUser, protocolCoordinatorRole, true
        UserRole.create ogUser, userRole, true
        UserRole.create cvUser, userRole, true
        UserRole.create jmUser, userRole, true
        UserRole.create scUser, userRole, true

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 6
        assert Role.count() == 5
        assert UserRole.count() == 7

        externalCustomers << builder.externalCustomer(
            name: "externalCustomer1",
            email: "externalCustomer1@domain.com",
            telephoneNumber: "12345678",
            id: "externalCustomer1"
        ) {
            contact(
                fullName: "contact1 name",
                identityCard: "291-290180-0001W",
                address: "Some address",
                email: "contact1@domain.com",
                telephoneNumber: "87894565"
            )
        }

        externalCustomers << builder.externalCustomer(
            name: "externalCustomer2",
            email: "externalCustomer2@domain.com",
            telephoneNumber: "12345679",
            id: "externalCustomer2"
        ) {
            contact(
                fullName: "contact2 name",
                identityCard: "291-290180-0002W",
                address: "Some other address",
                email: "contact2@domain.com",
                telephoneNumber: "98894565"
            )
        }

        externalCustomers << builder.externalCustomer(
            name: "externalCustomer3",
            email: "externalCustomer3@domain.com",
            telephoneNumber: "82385679",
            id: "externalCustomer3"
        ) {
            contact(
                fullName: "contact3 name",
                identityCard: "291-290180-0003W",
                address: "Yet another address",
                email: "contact3@domain.com",
                telephoneNumber: "56894565"
            )
        }

        externalCustomers.each { externalCustomer ->
            externalCustomer.save failOnError: true
        }
    }

    private production() {}

    def destroy = {}
}
