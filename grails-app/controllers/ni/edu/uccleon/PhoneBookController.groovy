package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class PhoneBookController {

    @Secured("permitAll")
    def index() {

    }
}
