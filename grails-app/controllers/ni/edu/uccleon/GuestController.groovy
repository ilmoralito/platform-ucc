package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_PROTOCOL_SUPERVISOR', 'ROLE_ASSISTANT_ADMINISTRATIVE_SUPERVISOR'])
class GuestController {
    GuestService guestService

    static allowedMethods = [
        index: ['GET', 'POST'],
        create: 'POST',
        show: 'GET',
        update: 'POST'
    ]

    def index() {
        List<Boolean> enabled = request.method == 'POST' && params.enabled ? params.list('enabled')*.toBoolean() : [true]

        [
            guests: guestService.getByEnabled(enabled),
            enabled: enabled
        ]
    }

    def create() {
        Guest guest = new Guest(params)

        if (!guest.save()) {
            guest.errors.allErrors.each { error ->
                log.error error.defaultMessage
            }

            flash.bean = guest
        }

        flash.message = guest.hasErrors() ? 'Parametros incorrectos' : 'Tarea concluida'
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

        flash.message = guest.hasErrors() ? 'Parametros incorrectos' : 'Tarea concluida'
        redirect action: 'show', id: id
    }
}
