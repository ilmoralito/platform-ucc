import static java.util.Calendar.*
import grails.util.DomainBuilder
import grails.util.Environment
import ni.edu.uccleon.*
import grails.core.*

class BootStrap {
    GrailsApplication grailsApplication
    ClassroomService classroomService
    EmployeeService employeeService

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
        Date today = new Date()

        // Roles
        Role assistantAdministrativeSupervisorRole = Role.findOrSaveByAuthority('ROLE_ASSISTANT_ADMINISTRATIVE_SUPERVISOR')
        Role administrativeSupervisorRole = Role.findOrSaveByAuthority('ROLE_ADMINISTRATIVE_SUPERVISOR')
        Role academicSupervisorRole = Role.findOrSaveByAuthority('ROLE_ACADEMIC_SUPERVISOR')
        Role protocolSupervisorRole = Role.findOrSaveByAuthority('ROLE_PROTOCOL_SUPERVISOR')
        Role adminRole = Role.findOrSaveByAuthority('ROLE_ADMIN')
        Role userRole = Role.findOrSaveByAuthority('ROLE_USER')

        // Employees
        Map protocolManager = employeeService.getEmployee(1)
        Map technicalSupportAssistant = employeeService.getEmployee(2)
        Map administrator = employeeService.getEmployee(3)
        Map sportManager = employeeService.getEmployee(4)
        Map assistantAdministration = employeeService.getEmployee(5)
        Map assistantAgronomyAndArchitecture = employeeService.getEmployee(6)
        Map coordinatorOfAgronomy = employeeService.getEmployee(7)
        Map architecturalAndCivilCoordinator = employeeService.getEmployee(8)

        // Users
        User orlandoGaitan = User.findByEmail(protocolManager.institutionalMail)
        if (!orlandoGaitan) {
            orlandoGaitan = new User (
                username: protocolManager.fullName,
                email: protocolManager.institutionalMail,
                employee: protocolManager.id
            ).save failOnError: true
        }

        User marioMartinez = User.findByEmail(technicalSupportAssistant.institutionalMail)
        if (!marioMartinez) {
            marioMartinez = new User (
                username: technicalSupportAssistant.fullName,
                email: technicalSupportAssistant.institutionalMail,
                employee: technicalSupportAssistant.id
            ).save failOnError: true
        }

        User jorgeRojas = User.findByEmail(administrator.institutionalMail)
        if (!jorgeRojas) {
            jorgeRojas = new User (
                username: administrator.fullName,
                email: administrator.institutionalMail,
                employee: administrator.id
            ).save failOnError: true
        }

        User robertoNesme = User.findByEmail(sportManager.institutionalMail)
        if (!robertoNesme) {
            robertoNesme = new User (
                username: sportManager.fullName,
                email: sportManager.institutionalMail,
                employee: sportManager.id
            ).save failOnError: true
        }

        User cristinaRojas = User.findByEmail(assistantAdministration.institutionalMail)
        if (!cristinaRojas) {
            cristinaRojas = new User(
                employee: assistantAdministration.id,
                username: assistantAdministration.fullName,
                email: assistantAdministration.institutionalMail
            ).save failOnError: true
        }

        User assistantAgronomyAndArchitectureUser = User.findByEmail(assistantAgronomyAndArchitecture.institutionalMail)
        if (!assistantAgronomyAndArchitectureUser) {
            assistantAgronomyAndArchitectureUser = new User(
                employee: assistantAgronomyAndArchitecture.id,
                username: assistantAgronomyAndArchitecture.fullName,
                email: assistantAgronomyAndArchitecture.institutionalMail
            ).save failOnError: true
        }

        User coordinatorOfAgronomyUser = User.findByEmail(coordinatorOfAgronomy.institutionalMail)
        if (!coordinatorOfAgronomyUser) {
            coordinatorOfAgronomyUser = new User(
                employee: coordinatorOfAgronomy.id,
                username: coordinatorOfAgronomy.fullName,
                email: coordinatorOfAgronomy.institutionalMail
            ).save failOnError: true
        }

        User architecturalAndCivilCoordinatorUser = User.findByEmail(architecturalAndCivilCoordinator.institutionalMail)
        if (!architecturalAndCivilCoordinatorUser) {
            architecturalAndCivilCoordinatorUser = new User(
                employee: architecturalAndCivilCoordinator.id,
                username: architecturalAndCivilCoordinator.fullName,
                email: architecturalAndCivilCoordinator.institutionalMail
            ).save failOnError: true
        }

        // USERROLES
        if (!UserRole.exists(orlandoGaitan.id, protocolSupervisorRole.id)) {
            UserRole.create orlandoGaitan, protocolSupervisorRole, true
        }

        if (!UserRole.exists(marioMartinez.id, adminRole.id)) {
            UserRole.create marioMartinez, adminRole, true
        }

        if (!UserRole.exists(jorgeRojas.id, administrativeSupervisorRole.id)) {
            UserRole.create jorgeRojas, administrativeSupervisorRole, true
        }

        if (!UserRole.exists(robertoNesme.id, userRole.id)) {
            UserRole.create robertoNesme, userRole, true
        }

        if (!UserRole.exists(cristinaRojas.id, userRole.id)) {
            UserRole.create cristinaRojas, userRole, true
        }

        if (!UserRole.exists(cristinaRojas.id, assistantAdministrativeSupervisorRole.id)) {
            UserRole.create cristinaRojas, assistantAdministrativeSupervisorRole, true
        }

        if (!UserRole.exists(assistantAgronomyAndArchitectureUser.id, userRole.id)) {
            UserRole.create assistantAgronomyAndArchitectureUser, userRole, true
        }

        if (!UserRole.exists(coordinatorOfAgronomyUser.id, userRole.id)) {
            UserRole.create coordinatorOfAgronomyUser, userRole, true
        }

        if (!UserRole.exists(architecturalAndCivilCoordinatorUser.id, userRole.id)) {
            UserRole.create architecturalAndCivilCoordinatorUser, userRole, true
        }

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        // Guests
        Guest guest1 = Guest.findOrSaveByFullName('John Doe')
        Guest guest2 = Guest.findOrSaveByFullName('Juan Perez')
        Guest guest4 = Guest.findOrSaveByFullName('Donald Trump')
        Guest guest3 = Guest.findOrSaveByFullName('Miguel de Cervantes')

        // Vouchers
        if (!Voucher.count()) {
            Voucher voucher1 = new Voucher(
                date: today,
                guest: guest1,
                activity: 'Activity#1',
                value: 200,
                createdBy: orlandoGaitan
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
                status: 'notified',
                createdBy: orlandoGaitan
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
                approvalDate: today,
                createdBy: orlandoGaitan
            )

            voucher3
                .addToFoods(new Food(name: 'dinner'))

            voucher3.save(failOnError: true)
        }
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
