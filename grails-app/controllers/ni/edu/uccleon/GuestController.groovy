package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_PROTOCOL_SUPERVISOR'])
class GuestController {
    static allowedMethods = [
        index: 'GET',
        create: 'POST',
        show: 'GET',
        update: 'POST'
    ]

    def index() {
        [guests: Guest.list()]
    }

    def create() {
        Guest guest = new Guest(params)

        if (!guest.save()) {
            guest.errors.allErrors.each { error ->
                log.error error.defaultMessage
            }

            flash.bean = guest
        }

        flash.message = guest.hasErrors() ? 'A ocurrido un error' : 'Accion concluida correctamente'
        redirect action: 'index'
    }

    def show(Guest guest) {
        respond guest
    }

    def update(Long id) {
        Guest guest = Guest.get(id)

        if (!guest) {
            response.sendError 404
        }

        guest.properties['fullName', 'enabled'] = params

        if (!guest.save()) {
            guest.errors.allErrors.each { error ->
                log.error error.defaultMessage
            }

            flash.bean = guest
        }

        flash.message = guest.hasErrors() ? 'A ocurrido un error' : 'Accion concluida correctamente'
        redirect action: 'show', id: id
    }
}
