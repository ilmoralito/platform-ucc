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
        show: 'GET',
        edit: 'GET',
        update: 'POST',
        profile: 'GET',
        password: ['GET', 'POST']
    ]

    def index() {
        [
            userList: coordinationService.employeesGroupedByCoordination(),
            employeeWidget: createEmployee()
        ]
    }

    def save() {
        RestResponse result = employeeService.post(params)

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

                new UserRole(user: user, role: role).save(flush: true)
            }

            flash.message = 'Usuario creado'
        }

        redirect action: 'index'
    }

    def show(Long id) {
        [employee: employeeService.getEmployee(id), user: User.findByEmployee(id)]
    }

    def edit(Long id) {
        [
            employee: employeeService.getEmployee(id),
            employeeWidget: createEmployee(),
            user: User.findByEmployee(id)
        ]
    }

    def update() {
        RestResponse result = employeeService.put(params)

        if (result.status == 200) {
            User user = User.findByEmployee(params.id)

            UserRole.removeAll user, true
            user.username = params.fullName
            user.email = params.institutionalMail
            params.list('authorities').each { authority ->
                UserRole.create user, Role.findByAuthority(authority), true
            }

            user.save flush: true
            flash.message = 'Datos actualziados'
        } else {
            flash.message = 'Datos incorrectos'
        }

        redirect action: 'edit', id: params.id
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ADMINISTRATIVE_SUPERVISOR', 'ROLE_ACADEMIC_SUPERVISOR', 'ROLE_PROTOCOL_SUPERVISOR'])
    def profile() {
        User currentUser = springSecurityService.getCurrentUser()
        Map employee = employeeService.getEmployee(currentUser.employee)

        [
            employee: employee,
            currentUserAuthorities: currentUser.authorities.authority.collect {
                it.tokenize('_')[1]
            }
        ]
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

    private CreateEmployee createEmployee() {
        new CreateEmployee(
            coordinationList: coordinationService.getCoordinations(),
            roleList: Role.list()
        )
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

class CreateEmployee {
    List coordinationList = []
    List<Role> roleList = []
}

