package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class BirthdayController {
    def employeeService
    def helperService

    static allowedMethods = [
        index: "GET",
        list: ["GET", "POST"]
    ]

    @Secured("permitAll")
    def index() {
        List employees = employeeService.getEmployees()
        List<Date> interval = helperService.getInterval("month")

        [employees: employees, daysOfMonth: interval.size()]
    }

    def list() {
        List employees = employeeService.getEmployees()//.groupBy { it. }

        [employees: employees]
    }
}
