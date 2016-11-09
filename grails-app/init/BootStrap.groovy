import grails.core.*
import ni.edu.uccleon.*
import grails.util.Environment
import grails.util.DomainBuilder
import static java.util.Calendar.*

class BootStrap {
    EmployeeService employeeService
    ClassroomService classroomService
    GrailsApplication grailsApplication

    def init = { servletContext ->
        grailsApplication.config.ni.edu.uccleon.activityList

        if (Environment.current == Environment.DEVELOPMENT) {
            development()
        }

        if (Environment.current == Environment.PRODUCTION) {
            production()
        }
    }

    private void development() {
        DomainBuilder builder = new DomainBuilder()
        Date today = new Date()
        List<ExternalCustomer> externalCustomers = []

        builder.classNameResolver = 'ni.edu.uccleon'

        Role adminRole = new Role('ROLE_ADMIN').save failOnError: true
        Role administrativeSupervisorRole = new Role('ROLE_ADMINISTRATIVE_SUPERVISOR').save failOnError: true
        Role academicSupervisorRole = new Role('ROLE_ACADEMIC_SUPERVISOR').save failOnError: true
        Role protocolSupervisorRole = new Role('ROLE_PROTOCOL_SUPERVISOR').save failOnError: true
        Role userRole = new Role('ROLE_USER').save failOnError: true

        Map MM = employeeService.getEmployee(1)
        Map JR = employeeService.getEmployee(2)
        Map CR = employeeService.getEmployee(3)
        Map RL = employeeService.getEmployee(4)
        Map WJ = employeeService.getEmployee(5)
        Map NC = employeeService.getEmployee(6)
        Map NN = employeeService.getEmployee(7)
        Map OG = employeeService.getEmployee(8)

        User marioMartinez = new User (
            username: MM.fullName,
            email: MM.institutionalMail,
            employee: MM.id
        ).save failOnError: true

        User jorgeRojas = new User (
            username: JR.fullName,
            email: JR.institutionalMail,
            employee: JR.id
        ).save failOnError: true

        User cristinaRojas = new User (
            username: CR.fullName,
            email: CR.institutionalMail,
            employee: CR.id
        ).save failOnError: true

        User rositaMollineda = new User (
            username: RL.fullName,
            email: RL.institutionalMail,
            employee: RL.id
        ).save failOnError: true

        User williamsJuarez = new User (
            username: WJ.fullName,
            email: WJ.institutionalMail,
            employee: WJ.id
        ).save failOnError: true

        User neylingCarrero = new User (
            username: NC.fullName,
            email: NC.institutionalMail,
            employee: NC.id
        ).save failOnError: true

        User nejamaNarvaez = new User (
            username: NN.fullName,
            email: NN.institutionalMail,
            employee: NN.id
        ).save failOnError: true

        User orlandoGaitan = new User (
            username: OG.fullName,
            email: OG.institutionalMail,
            employee: OG.id
        ).save failOnError: true

        UserRole.create marioMartinez, adminRole, true
        UserRole.create jorgeRojas, administrativeSupervisorRole, true
        UserRole.create cristinaRojas, administrativeSupervisorRole, true
        UserRole.create rositaMollineda, academicSupervisorRole, true
        UserRole.create williamsJuarez, userRole, true
        UserRole.create neylingCarrero, userRole, true
        UserRole.create nejamaNarvaez, userRole, true
        UserRole.create orlandoGaitan, protocolSupervisorRole, true

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 8
        assert Role.count() == 5
        assert UserRole.count() == 8

        externalCustomers << builder.externalCustomer(
            name: 'externalCustomer1',
            city: 'Leon',
            email: 'externalCustomer1@domain.com',
            telephoneNumber: '12345678',
            id: 'externalCustomer1'
        ) {
            contact(
                fullName: 'contact1 name',
                identityCard: '291-290180-0001W',
                address: 'Some address',
                email: 'contact1@domain.com',
                telephoneNumber: '87894565'
            )
        }

        externalCustomers << builder.externalCustomer(
            name: 'externalCustomer2',
            city: 'Leon',
            email: 'externalCustomer2@domain.com',
            telephoneNumber: '12345679',
            id: 'externalCustomer2'
        ) {
            contact(
                fullName: 'contact2 name',
                identityCard: '291-290180-0002W',
                address: 'Some other address',
                email: 'contact2@domain.com',
                telephoneNumber: '98894565'
            )
        }

        externalCustomers << builder.externalCustomer(
            name: 'externalCustomer3',
            city: 'Chinandega',
            email: 'externalCustomer3@domain.com',
            telephoneNumber: '82385679',
            id: 'externalCustomer3'
        ) {
            contact(
                fullName: 'contact3 name',
                identityCard: '291-290180-0003W',
                address: 'Yet another address',
                email: 'contact3@domain.com',
                telephoneNumber: '56894565'
            )
        }

        externalCustomers.each { externalCustomer ->
            externalCustomer.save failOnError: true
        }

        Guest guest1 = new Guest(fullName: 'John Doe').save failOnError: true
        Guest guest2 = new Guest(fullName: 'Juan Perez').save failOnError: true
        Guest guest3 = new Guest(fullName: 'Miguel de Cervantes').save failOnError: true
        Guest guest4 = new Guest(fullName: 'Donald Trump').save failOnError: true

        Voucher voucher1 = new Voucher(
            date: today,
            guest: guest1,
            activity: 'Activity#1',
            value: 200
        )

        voucher1
            .addToFoods(new Food(name: 'breakfast'))
            .addToFoods(new Food(name: 'lunch'))
            .addToFoods(new Food(name: 'dinner'))

        voucher1.save(failOnError: true)

        Voucher voucher2 = new Voucher(
            date: today + 5,
            guest: guest1,
            activity: 'Activity#2',
            value: 200,
            status: 'notified'
        )

        voucher2
            .addToFoods(new Food(name: 'breakfast'))
            .addToFoods(new Food(name: 'dinner'))

        voucher2.save(failOnError: true)

        Voucher voucher3 = new Voucher(
            date: today,
            guest: guest3,
            activity: 'Activity#3',
            value: 250,
            status: 'approved',
            approvalDate: today
        )

        voucher3
            .addToFoods(new Food(name: 'dinner'))

        voucher3.save(failOnError: true)
    }

    private void production() {
        Role administrativeSupervisorRole = Role.findOrSaveByAuthority('ROLE_ADMINISTRATIVE_SUPERVISOR')
        Role academicSupervisorRole = Role.findOrSaveByAuthority('ROLE_ACADEMIC_SUPERVISOR')
        Role protocolSupervisorRole = Role.findOrSaveByAuthority('ROLE_PROTOCOL_SUPERVISOR')
        Role adminRole = Role.findOrSaveByAuthority('ROLE_ADMIN')
        Role userRole = Role.findOrSaveByAuthority('ROLE_USER')

        Map MM = employeeService.getEmployee(1)
        Map JR = employeeService.getEmployee(2)
        Map CR = employeeService.getEmployee(3)
        Map RL = employeeService.getEmployee(4)
        Map WJ = employeeService.getEmployee(5)
        Map NC = employeeService.getEmployee(6)
        Map NN = employeeService.getEmployee(7)
        Map OG = employeeService.getEmployee(8)

        User marioMartinez = User.findByEmail(MM.institutionalMail)
        if (!marioMartinez) {
            marioMartinez = new User (
                username: MM.fullName,
                email: MM.institutionalMail,
                employee: MM.id
            ).save failOnError: true
        }

        User jorgeRojas = User.findByEmail(JR.institutionalMail)
        if (!jorgeRojas) {
            jorgeRojas = new User (
                username: JR.fullName,
                email: JR.institutionalMail,
                employee: JR.id
            ).save failOnError: true
        }

        User cristinaRojas = User.findByEmail(CR.institutionalMail)
        if (!cristinaRojas) {
            cristinaRojas = new User (
                username: CR.fullName,
                email: CR.institutionalMail,
                employee: CR.id
            ).save failOnError: true
        }

        User rositaMollineda = User.findByEmail(RL.institutionalMail)
        if (!rositaMollineda) {
            rositaMollineda = new User (
                username: RL.fullName,
                email: RL.institutionalMail,
                employee: RL.id
            ).save failOnError: true
        }

        User williamsJuarez = User.findByEmail(WJ.institutionalMail)
        if (!williamsJuarez) {
            williamsJuarez = new User (
                username: WJ.fullName,
                email: WJ.institutionalMail,
                employee: WJ.id
            ).save failOnError: true
        }

        User neylingCarrero = User.findByEmail(NC.institutionalMail)
        if (!neylingCarrero) {
            neylingCarrero = new User (
                username: NC.fullName,
                email: NC.institutionalMail,
                employee: NC.id
            ).save failOnError: true
        }

        User nejamaNarvaez = User.findByEmail(NN.institutionalMail)
        if (!nejamaNarvaez) {
            nejamaNarvaez = new User (
                username: NN.fullName,
                email: NN.institutionalMail,
                employee: NN.id
            ).save failOnError: true
        }

        User orlandoGaitan = User.findByEmail(OG.institutionalMail)
        if (!orlandoGaitan) {
            orlandoGaitan = new User (
                username: OG.fullName,
                email: OG.institutionalMail,
                employee: OG.id
            ).save failOnError: true
        }

        if (!UserRole.exists(marioMartinez.id, adminRole.id)) {
            UserRole.create marioMartinez, adminRole, true
        }

        if (!UserRole.exists(jorgeRojas.id, administrativeSupervisorRole.id)) {
            UserRole.create jorgeRojas, administrativeSupervisorRole, true
        }

        if (!UserRole.exists(cristinaRojas.id, administrativeSupervisorRole.id)) {
            UserRole.create cristinaRojas, administrativeSupervisorRole, true
        }

        if (!UserRole.exists(rositaMollineda.id, academicSupervisorRole.id)) {
            UserRole.create rositaMollineda, academicSupervisorRole, true
        }

        if (!UserRole.exists(williamsJuarez.id, userRole.id)) {
            UserRole.create williamsJuarez, userRole, true
        }

        if (!UserRole.exists(neylingCarrero.id, userRole.id)) {
            UserRole.create neylingCarrero, userRole, true
        }

        if (!UserRole.exists(nejamaNarvaez.id, userRole.id)) {
            UserRole.create nejamaNarvaez, userRole, true
        }

        if (!UserRole.exists(orlandoGaitan.id, protocolSupervisorRole.id)) {
            UserRole.create orlandoGaitan, protocolSupervisorRole, true
        }

        UserRole.withSession {
            it.flush()
            it.clear()
        }
    }

    def destroy = {}
}
