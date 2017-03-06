package ni.edu.uccleon.thirdparty

import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestResponse

@Secured('ROLE_ADMIN')
class ThirdPartyController {
    ThirdPartyService thirdPartyService

    static allowedMethods = [
        index: 'GET',
        save: 'POST',
        show: 'GET',
        edit: 'GET',
        update: 'PUT'
    ]

    def index() {
        [thirdPartyList: thirdPartyService.getAll()]
    }

    def save() {
        RestResponse restResponse = thirdPartyService.post(params.subMap(['name']))

        flash.message = restResponse.status == 201 ? 'Tercero agregado' : 'Parametros incorrectos'
        redirect action: 'index'
    }

    def show(Integer id) {
        [thirdParty: thirdPartyService.get(id)]
    }

    def edit(Integer id) {
        [thirdParty: thirdPartyService.get(id)]
    }

    def update() {
        // TODO: Not updating enabled property
        RestResponse restResponse = thirdPartyService.put(params)

        flash.message = restResponse.status == 200 ? 'Tercero actualizado' : 'Parametros incorrectos'
        redirect action: 'edit', id: params.id
    }
}
