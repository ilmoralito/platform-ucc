package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ADMINISTRATIVE_SUPERVISOR', 'ROLE_ACADEMIC_SUPERVISOR', 'ROLE_PROTOCOL_SUPERVISOR'])
class PanelController {
    CopyService copyService
    BirthdayService birthdayService
    SpringSecurityService springSecurityService

    static allowedMethods = [
        index: 'GET'
    ]

    def index() {
        [
            copyStatus: createCopyStatus(),
            birthdaysMonth: birthdayService.getBirthdaysMonth()
        ]
    }

    private CopyStatus createCopyStatus() {
        new CopyStatus(
            status: copyService.copieStatus(springSecurityService.currentUser.employee)
        )
    }
}

