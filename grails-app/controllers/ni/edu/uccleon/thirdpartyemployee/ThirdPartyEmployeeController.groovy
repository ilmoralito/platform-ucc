package ni.edu.uccleon.thirdpartyemployee

import grails.plugin.springsecurity.annotation.Secured
import ni.edu.uccleon.thirdparty.ThirdPartyService
import grails.plugins.rest.client.RestResponse

@Secured('ROLE_ADMIN')
class ThirdPartyEmployeeController {
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
        RestResponse restResponse = thirdPartyService.postThirdPartyEmployee(params.subMap(['fullName', 'email', 'thirdParty']))

        flash.message = restResponse.status == 201 ? 'Empleado agregado' : 'Parametros incorrectos'
        redirect action: 'index'
    }

    def show() {
        [thirdPartyEmployee: thirdPartyService.getThirdPartyEmployee(params)]
    }

    def edit() {
        [
            thirdPartyList: thirdPartyService.getAll(),
            thirdPartyEmployee: thirdPartyService.getThirdPartyEmployee(params)
        ]
    }

    def update() {
        RestResponse restResponse = thirdPartyService.putThirdPartyEmployee(params)

        flash.message = restResponse.status == 200 ? 'Empleado actualizado' : 'Parametros incorrectos'
        redirect action: 'edit', params: [thirdPartyId: params.thirdParty, thirdPartyEmployeeId: params.thirdPartyEmployeeId]
    }
}

