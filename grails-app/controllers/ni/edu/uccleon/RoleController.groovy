package ni.edu.uccleon

import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class RoleController {

    static allowedMethods = [
        index: 'GET',
        save: 'POST',
        edit: 'GET',
        update: 'POST'
    ]

    def index() {
        [roles: Role.list()]
    }

    def save() {
        Role role = new Role(params)

        role.save()

        flash.message = role.hasErrors() ? 'Parametros incorrectos' : 'Accion concluida'
        redirect action: 'index'
    }

    def edit(Role role) {
        respond role
    }

    @Transactional
    def update(Role role) {
        if (!role) {
            render status: NOT_FOUND
        }

        if (role.hasErrors()) {
            flash.message = 'Parametros incorrectos'
            flash.errors = role.errors
        } else {
            flash.message = 'Tarea concluida'
            role.save()
        }

        redirect action: 'edit', id: role.id
    }
}
