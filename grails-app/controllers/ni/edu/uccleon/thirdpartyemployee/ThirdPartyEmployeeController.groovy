package ni.edu.uccleon.thirdpartyemployee

import grails.plugin.springsecurity.annotation.Secured
import ni.edu.uccleon.thirdparty.ThirdPartyService
import grails.plugins.rest.client.RestResponse
import org.grails.web.json.JSONObject
import ni.edu.uccleon.UserRole
import ni.edu.uccleon.Role
import ni.edu.uccleon.User

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

        if (restResponse.status == 201) {
            JSONObject thirdPartyEmployee = restResponse.json

            User user = new User(
                username: thirdPartyEmployee.fullName,
                email: thirdPartyEmployee.email,
                thirdPartyEmployee: thirdPartyEmployee.id
            )

            if (!user.save()) {
                user.errors.allErrors.each { error ->
                    log "[field: $error.field, message: $error.defaultMessage]"
                }
            } else {
                UserRole.create user, Role.findByAuthority('ROLE_COPY_MANAGER'), true
            }

            flash.message = 'Tercero agregado'
        } else {
            flash.message = 'Parametros incorrectos'
        }

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

        if (restResponse.status == 200) {
            JSONObject thirdPartyEmployee = restResponse.json

            User user = User.findByThirdPartyEmployee(thirdPartyEmployee.id)
            println user

            user.username = thirdPartyEmployee.fullName
            user.email = thirdPartyEmployee.email

            if (!user.save()) {
                user.errors.allErrors.each { error ->
                    log "[field: $error.field, message: $error.defaultMessage]"
                }
            }

            flash.message = 'Empleado actualizado'
        } else {
            flash.message = 'Parametros incorrectos'
        }

        redirect action: 'edit', params: [thirdPartyId: params.thirdParty, thirdPartyEmployeeId: params.thirdPartyEmployeeId]
    }
}

