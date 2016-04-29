import ni.edu.uccleon.*
import grails.util.Environment
import grails.util.DomainBuilder
import static java.util.Calendar.*

class BootStrap {
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
        builder.classNameResolver = "ni.edu.uccleon"
        Date today = new Date()
        List<ExternalCustomer> externalCustomers = []
        List<Activity> activies = []

        Role adminRole = new Role("ROLE_ADMIN").save failOnError: true
        Role userRole = new Role("ROLE_USER").save failOnError: true

        User adminUser = new User("admin", 1, "admin.user@ucc.edu.ni", "password").save failOnError: true
        User userUser = new User("user", 2, "user.user@ucc.edu.ni", "password").save failOnError: true

        UserRole.create adminUser, adminRole
        UserRole.create userUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2

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

        /*
        activies << builder.activity(
            name: "Activity number one",
            activityDate: today + 10,
        ) {
            externalCustomer(refId: "externalCustomer1")

            event(
                dateOfTheEvent: today + 10,
                startTime: today + 10,
                endingTime: today + 10,
                location: ""
            )
        }
        */
    }

    private production() {}

    def destroy = {}
}
