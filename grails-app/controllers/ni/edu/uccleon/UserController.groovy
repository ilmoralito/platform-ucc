package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.http.ResponseEntity
import grails.plugins.rest.client.RestResponse

@Secured(["ROLE_ADMIN"])
class UserController {
    def springSecurityService
    def employeeService

    static allowedMethods = [
        index: "GET",
        create: "POST",
        show: "GET",
        update: "POST",
        profile: "GET",
        password: ["GET", "POST"]
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

    def show(Long id) {
        User user = User.get(id)

        if (!user) {
            response.sendError 404
        }

        [user: user, employee: employeeService.getEmployee(id)]
    }

    def update(Long id) {
        User user = User.get(id)
        def result

        if (!user) {
            response.sendError 404
        }

        user.properties = params

        if (!user.save()) {
            user.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = user
        } else {
            result = employeeService.updateEmployee(id, params?.fullName, params?.institutionalMail, params?.position, params?.authority, params?.identityCard, params?.inss)

            if (result.json.message) {
                flash.employeeBag = result.json
                log.error "A ocurrido el error $result.json.message"
            }
        }

        flash.message = user.hasErrors() || result.json.message ? "A ocurrido un error" : "Tarea completada"
        redirect action: "show", id: id
    }

    @Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ADMINISTRATIVE_SUPERVISOR", "ROLE_ACADEMIC_SUPERVISOR", "ROLE_PROTOCOL_SUPERVISOR"])
    def profile() {
        Integer currentUserId = springSecurityService.loadCurrentUser().id
        Map employee = employeeService.getEmployee(currentUserId)

        [employee: employee]
    }

    @Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ADMINISTRATIVE_SUPERVISOR", "ROLE_ACADEMIC_SUPERVISOR", "ROLE_PROTOCOL_SUPERVISOR"])
    def password(PasswordCommand command) {
        if (request.post) {
            if (command.hasErrors()) {
                command.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.message = "Datos incorrectos"
                return
            }

            User user = springSecurityService.currentUser

            user.password = command.newPassword
            user.save()

            flash.message = "Clave actualizada"
            redirect action: "password"
            return
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
