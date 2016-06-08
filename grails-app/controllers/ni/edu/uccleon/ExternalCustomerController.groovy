package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_PROTOCOL_SUPERVISOR"])
class ExternalCustomerController {
    def externalCustomerService

    def allowedMethods = [
        index: ["GET", "POST"],
        create: ["GET","POST"],
        edit: "GET",
        update: "POST"
    ]

    def index() {
        List<ExternalCustomer> externalCustomers = []

        if (request.post) {
            externalCustomers = ExternalCustomer.where {
                name =~ "%params?.name%" ||
                city in params.list("cities")
            }.list(params)
        } else {
            externalCustomers = ExternalCustomer.list(params)
        }

        [externalCustomers: externalCustomers]
    }

    def create() {
        ExternalCustomer externalCustomer = new ExternalCustomer(params)

        if (request.post) {
            if (!externalCustomer.save()) {
                externalCustomer.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = externalCustomer
            }

            flash.message = externalCustomer.hasErrors() ? "Error" : "Tarea correcta"
            return
        }

        [externalCustomer: externalCustomer]
    }

    def edit(Long id) {
        ExternalCustomer externalCustomer = externalCustomerService.get(id)

        if (!externalCustomer) {
            response.sendError 404
        }

        [externalCustomer: externalCustomer]
    }

    def update(Long id) {
        ExternalCustomer externalCustomer = externalCustomerService.get(id)

        if (!externalCustomer) {
            response.sendError 404
        }

        externalCustomer.properties = params

        if (!externalCustomer.save()) {
            externalCustomer.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = externalCustomer
        }

        flash.message = externalCustomer.hasErrors() ? "Error" : "Cliente externo actualizado"
        redirect action: "edit", id: id
    }
}
