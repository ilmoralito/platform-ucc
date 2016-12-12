package ni.edu.uccleon

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import static org.springframework.http.HttpMethod.*
import static org.springframework.http.HttpStatus.*
import grails.plugins.rest.client.RestResponse
import org.springframework.http.ResponseEntity
import org.grails.web.json.JSONObject
import grails.transaction.*

@Secured('ROLE_ADMIN')
class UserController {
    SpringSecurityService springSecurityService
    CoordinationService coordinationService
    EmployeeService employeeService

    static allowedMethods = [
        index: 'GET',
        save: 'POST',
        edit: 'GET',
        update: 'POST',
        updateEmployee: 'POST',
        profile: 'GET',
        password: ['GET', 'POST']
    ]

    def index() {
        [
            users: User.list(),
            roles: Role.list(),
            coordinations: coordinationService.getCoordinations()
        ]
    }

    def save() {
        RestResponse result = employeeService.postEmployee(
            params.fullName,
            params.institutionalMail,
            params.authority,
            params.identityCard,
            params.inss,
            params.list('coordinations')
        )

        if (result.status >= 400) {
            flash.message = 'Parametros incorrectos'
        } else {
            JSONObject employee = employeeService.getEmployeeByInstitutionalMail(params.institutionalMail)

            User user = new User(
                username: employee.fullName,
                email: employee.institutionalMail,
                employee: employee.id
            )

            user.save(flush: true)

            params.list('authorities').each { authority ->
                Role role = Role.findByAuthority(authority)

                UserRole.create user, role, true
            }

            flash.message = 'Usuario creado'
        }

        redirect action: 'index'
    }

    def edit(User user) {
        respond user, model: [
            roles: Role.list(),
            employee: employeeService.getEmployee(user.employee),
            coordinations: coordinationService.getCoordinations()
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

    def updateEmployee() {
        RestResponse result = employeeService.putEmployee(
            params.int('employeeId'),
            params.fullName,
            params.institutionalMail,
            params.authority,
            params.identityCard,
            params.inss,
            params.list('coordinations')
        )

        flash.message = result.status >= 400 ? 'Parametros incorrectos' : 'Empleado actualizado'
        redirect action: 'edit', id: params.int('id')
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
