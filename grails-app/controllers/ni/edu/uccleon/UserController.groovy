package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class UserController {
    def springSecurityService
    def employeeService

    static allowedMethods = [
        index: ["GET", "POST"],
        show: "GET",
        update: "POST",
        profile: "GET",
        password: ["GET", "POST"]
    ]

    def index() {
        Closure users = {
            User.list()
        }

        if (request.post) {
            Integer id = params.int("employee")
            Map<String, String> employee = employeeService.getEmployee(id)
            User user = new User(
                username: employee.institutionalMail,
                email: employee.institutionalMail,
                employee: employee.id
            )

            if (!user.save()) {
                user.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                return [users: users(), user: user]
            }
        }

        [users: users()]
    }

    def show(Long id) {
        User user = User.get(id)

        if (!user) {
            response.sendError 404
        }

        Map<String, String> employee = employeeService.getEmployee(id)

        [user: user, employee: employee]
    }

    @Secured(["ROLE_ADMIN", "ROLE_SUPERVISOR", "ROLE_USER"])
    def profile() {
        User user = springSecurityService.currentUser

        [user: user]
    }

    @Secured(["ROLE_ADMIN", "ROLE_SUPERVISOR", "ROLE_USER"])
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
