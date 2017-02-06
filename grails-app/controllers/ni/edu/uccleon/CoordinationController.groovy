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
        [
            coordinationsByLocation: coordinationService.groupCoordinationsByLocation(),
            colors: colorService.getColors()
        ]
    }

    def save() {
        RestResponse result = coordinationService.postCoordination([
            name: params.name,
            location: params.location,
            printQuota: params.printQuota, 
            extensionNumber: params.extensionNumber,
            colors: params.list('colors')*.toInteger()
        ])

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
