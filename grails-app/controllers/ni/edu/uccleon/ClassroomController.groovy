package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestResponse

@Secured(['ROLE_ADMIN'])
class ClassroomController {
    ClassroomService classroomService

    static allowedMethods = [
        index: 'GET',
        create: 'POST',
        edit: 'GET',
        update: 'POST'
    ]

    def index() {
        [classrooms: classroomService.groupedByCode()]
    }

    def create() {
        RestResponse restResponse = classroomService.post([
            code: params.code,
            name: params.name,
            capacity: params.capacity,
            airConditioned: params.airConditioned ? true : false
        ])

        flash.message = restResponse.status >= 400 ? 'Parametros incorrectos' : 'Creado correctamente'
        redirect action: 'index'
    }

    def edit(Integer id) {
        Map classroom = classroomService.get(id)

        if (classroom.error) {
            response.sendError 404
        }

        [classroom: classroom]
    }

    def update(Integer id) {
        Map classroom = classroomService.get(id)

        if (classroom.error) {
            response.sendError 404
        }

        RestResponse restResponse = classroomService.put([
            id: id,
            code: params.code,
            name: params.name,
            capacity: params.capacity,
            airConditioned: params.airConditioned ? true : false
        ])

        flash.message = restResponse.status >= 400 ? 'A ocurrido un error' : 'Actualizado correctamente'
        redirect action: 'edit', id: id
    }
}

