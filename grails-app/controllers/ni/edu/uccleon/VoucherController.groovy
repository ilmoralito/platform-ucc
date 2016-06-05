package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_PROTOCOL_SUPERVISOR"])
class VoucherController {

    static allowedMethods = [
        index: ["GET", "POST"]
    ]

    def index() {

    }
}
