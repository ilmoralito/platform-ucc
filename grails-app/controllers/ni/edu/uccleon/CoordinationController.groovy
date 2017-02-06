package ni.edu.uccleon

import grails.plugins.rest.client.RestResponse
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class CoordinationController {
    CoordinationService coordinationService
    ColorService colorService

    static allowedMethods = [
        index: 'GET',
        save: 'POST',
        show: 'GET',
        edit: 'GET',
        update: 'POST'
    ]

    def index() {
        List coordinations = coordinationService.getCoordinations()
        List colors = colorService.getColors()

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

        flash.message = result.status >= 400 ? 'Parametros incorrectos' : 'Tarea concluida'
        redirect action: 'index'
    }

    def show() {
        [coordination: coordinationService.getCoordinationsByCoordinationName(params.name)]
    }

    def edit(Integer id) {
        [
            coordination: coordinationService.getCoordination(id),
            colors: colorService.getColors()
        ]
    }

    def update(Integer id) {
        RestResponse result = coordinationService.putCoordination([
            id: id,
            name: params.name,
            location: params.location,
            colors: params.list('colors'),
            printQuota: params.printQuota,
            extensionNumber: params.extensionNumber
        ])

        flash.message = result.status >= 400 ? 'Parametros incorrectos' : 'Tarea concluida'
        redirect action: 'edit', id: id
    }
}
