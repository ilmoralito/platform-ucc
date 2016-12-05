package ni.edu.uccleon

import grails.plugins.rest.client.RestResponse
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class CoordinationController {
    CoordinationService coordinationService
    ColorService colorService

    static allowedMethods = [
        index: 'GET',
        save: 'POST'
    ]

    def index() {
        List coordinations = coordinationService.getCoordinations()
        List<Map> colors = colorService.getColors()

        [coordinations: coordinations, colors: colors]
    }

    def save() {
        RestResponse result = coordinationService.postCoordination(
            params?.name,
            params?.extensionNumber,
            params?.location,
            params.int('printQuota'), 
            params.list('colors')*.toInteger()
        )

        flash.message = result.status > 400 ? 'Parametros incorrectos' : 'Tarea concluida'
        redirect action: 'index'
    }
}
