package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class PanelController {
    def springSecurityService

    static allowedMethods = [
        index: "GET"
    ]

    def index() {
        
    }
}
