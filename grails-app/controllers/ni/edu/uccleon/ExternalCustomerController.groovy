package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_PROTOCOL_COORDINATOR"])
class ExternalCustomerController {

    def allowedMethods = [
        index: ["GET", "POST"],
        edit: "GET"
    ]

    def index() {
        Closure externalCustomers = {
            ExternalCustomer.list()
        }

        if (request.post) {
            ExternalCustomer externalCustomer = new ExternalCustomer(params)

            if (!externalCustomer.save()) {
                externalCustomer.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                return [externalCustomer: externalCustomer, externalCustomers: externalCustomers()]
            }

            flash.message = "Cliente externo creado"
        }

        [externalCustomers: externalCustomers()]
    }

    def edit(Long id) {
        ExternalCustomer externalCustomer = ExternalCustomer.get(id)

        if (!externalCustomer) {
            response.sendError 404
        }

        [externalCustomer: externalCustomer]
    }

    def update(Long id) {
        ExternalCustomer externalCustomer = ExternalCustomer.get(id)

        if (!externalCustomer) {
            response.sendError 404
        }

        externalCustomer.properties = params

        if (!externalCustomer.save()) {
            externalCustomer.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.message = "Error"
            chain action: "edit", id: id, model: [externalCustomer: externalCustomer]
            return
        }

        flash.message = "Cliente externo actualizado"
        redirect action: "edit", id: id
    }
}
