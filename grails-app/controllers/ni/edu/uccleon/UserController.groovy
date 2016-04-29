package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class UserController {
    def employeeService

    static allowedMethods = [
        index: ["GET", "POST"],
        show: "GET",
        update: "POST"
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


}
