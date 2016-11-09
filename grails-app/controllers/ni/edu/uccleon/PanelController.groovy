package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ADMINISTRATIVE_SUPERVISOR', 'ROLE_ACADEMIC_SUPERVISOR', 'ROLE_PROTOCOL_SUPERVISOR'])
class PanelController {
    SpringSecurityService springSecurityService
    EmployeeService employeeService
    BirthdayService birthdayService

    static allowedMethods = [
        index: 'GET'
    ]

    def index() {
        User currentUser = springSecurityService.getCurrentUser()

        [
            printQuota: employeeService.getEmployeeCoordinationsPrintQuota(currentUser.id),
            birthdaysMonth: birthdayService.getBirthdaysMonth()
        ]
    }
}
