package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class CoordinationController {
    def coordinationService
    def colorService

    static allowedMethods = [
        index: ["GET", "POST"],
        create: "POST"
    ]

    def index() {
        List coordinations = coordinationService.getCoordinations()
        List colorList = colorService.getColors()

        [coordinations: coordinations, colorList: colorList]
    }

    def create() {
        def result = coordinationService.postCoordination(
            params?.name,
            params?.extensionNumber,
            params?.location,
            params.list("colors")
        )

        redirect action: "index"
    }
}
