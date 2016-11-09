package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class BirthdayController {
    BirthdayService birthdayService
    HelperService helperService

    static allowedMethods = [
        index: 'GET'
    ]

    def index() {
        [
            birthdaysMonth: birthdayService.getBirthdaysMonth(),
            today: helperService.getDayOfMonth()
        ]
    }
}
