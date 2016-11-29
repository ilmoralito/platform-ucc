package ni.edu.uccleon

import grails.transaction.*
import grails.plugins.rest.client.RestResponse
import org.springframework.http.ResponseEntity
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class UserController {
    SpringSecurityService springSecurityService
    EmployeeService employeeService

    static allowedMethods = [
        index: 'GET',
        create: 'POST',
        edit: 'GET',
        update: 'POST',
        profile: 'GET',
        password: ['GET', 'POST']
    ]

    def index() {
        List<User> users = User.list()
        List employeeList = employeeService.getEmployees()
        List<Integer> userList = users.employee
        List employeesNotInUserList = employeeList.findAll { !(it.id in userList) }

        [users: users, employees: employeesNotInUserList]
    }

    def create(Long employee) {
        Map<String, String> e = employeeService.getEmployee(employee)
        User user = new User(
            username: e.fullName,
            email: e.institutionalMail,
            employee: e.id
        )

        if (!user.save()) {
            user.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }
        }

        flash.message = user.hasErrors() ? "A ocurrido un error" : "Tarea completada"
        redirect action: "index"
    }

    def edit(User user) {
        respond user, model: [
            roles: Role.list()
        ]
    }

    def update(User user) {
        List<String> authorities = params.list('authorities')

        if (!user) {
            render status: NOT_FOUND
        }

        if (!authorities) {
            flash.message = 'Parametros incorrectos'

            redirect action: 'edit', id: user.id
            return
        }

        if (user.hasErrors()) {
            flash.message = 'A ocurrido un error'

            respond user.errors, view: 'edit', model: [roles: Role.list()]
        }

        user.save()
        UserRole.removeAll(user, true)

        authorities.each { authority ->
            UserRole.create(user, Role.findByAuthority(authority), true)
        }

        redirect action: 'edit', id: user.id
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ADMINISTRATIVE_SUPERVISOR', 'ROLE_ACADEMIC_SUPERVISOR', 'ROLE_PROTOCOL_SUPERVISOR'])
    def profile() {
        User currentUser = springSecurityService.loadCurrentUser()
        Map employee = employeeService.getEmployee(currentUser.employee)

        [employee: employee]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ADMINISTRATIVE_SUPERVISOR', 'ROLE_ACADEMIC_SUPERVISOR', 'ROLE_PROTOCOL_SUPERVISOR'])
    def password(PasswordCommand command) {
        if (request.post) {
            if (command.hasErrors()) {
                command.errors.allErrors.each { error ->
                    log.error "$error.field: $error.defaultMessage"
                }

                flash.message = 'Datos incorrectos'
                return
            }

            User user = springSecurityService.currentUser

            user.password = command.newPassword
            user.save()

            flash.message = 'Clave actualizada'
        }
    }
}

class PasswordCommand {
    def springSecurityService
    def passwordEncoder

    String currentPassword
    String newPassword
    String repeatNewPassword

    static constraints = {
        currentPassword validator: { currentPassword, obj ->
            String currentUserPassword = obj.springSecurityService.currentUser.password

            obj.passwordEncoder.isPasswordValid(currentUserPassword, currentPassword, null)
        }
        repeatNewPassword validator: { repeatNewPassword, obj ->
            repeatNewPassword == obj.newPassword
        }
    }
}
