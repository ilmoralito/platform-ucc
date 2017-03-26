package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_ADMINISTRATIVE_SUPERVISOR', 'ROLE_ACADEMIC_SUPERVISOR', 'ROLE_PROTOCOL_SUPERVISOR', 'ROLE_COPY_MANAGER', 'ROLE_COPY_ASSISTANT'])
class PanelController {
    CopyService copyService
    BirthdayService birthdayService
    SpringSecurityService springSecurityService

    static allowedMethods = [
        index: 'GET'
    ]

    def index() {
        [
            copyStatusList: createCopyStatus(),
            birthdaysMonth: birthdayService.getBirthdaysMonth()
        ]
    }

    private CopyStatus createCopyStatus() {
        User currentUser = springSecurityService.currentUser
        List<String> currentUserAuthorities = currentUser.authorities.authority
        Boolean asTheUserCopyRoles = ['ROLE_COPY_MANAGER', 'ROLE_COPY_ASSISTANT'].any { authority ->
            authority in currentUserAuthorities
        }

        new CopyStatus(
            statusList: asTheUserCopyRoles ? copyService.generalCopyStatus().json : copyService.copieStatus(currentUser.employee).json
        )
    }
}

