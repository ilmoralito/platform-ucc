package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured("permitAll")
class BirthdayController {
    def employeeService
    def birthdayService
    def helperService

    static allowedMethods = [
        index: "GET"
    ]

    def index() {
        List employees = employeeService.getEmployees()
        List birthdaysMonth = birthdayService.getBirthdaysMonth(employees)

        [birthdaysMonth: birthdaysMonth, today: helperService.getDayOfMonth()]
    }
}
