package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_PROTOCOL_SUPERVISOR'])
class ExternalCustomerController {
    def externalCustomerService

    def allowedMethods = [
        index: 'GET',
        create: ['GET','POST'],
        show: 'GET',
        edit: 'GET',
        update: 'POST'
    ]

    def index() {
        List<ExternalCustomer> externalCustomers = ExternalCustomer.list(params)

        [externalCustomers: externalCustomers]
    }

    def create() {
        ExternalCustomer externalCustomer = new ExternalCustomer(params)

        if (request.post) {
            if (!externalCustomer.save()) {
                externalCustomer.errors.allErrors.each { error ->
                    log.error "$error.field: $error.defaultMessage"
                }

                flash.message = 'Paramteros incorrectos'
                return [externalCustomer: externalCustomer]
            }

            flash.message = 'Tarea concluida correctamente'
        }

        [externalCustomer: externalCustomer]
    }

    def show(ExternalCustomer externalCustomer) {
        respond externalCustomer
    }

    def edit(ExternalCustomer externalCustomer) {
        respond externalCustomer
    }

    def update(ExternalCustomer externalCustomer) {
        externalCustomer.properties = params

        if (!externalCustomer.save()) {
            externalCustomer.errors.allErrors.each { error ->
                log.error "$error.field: $error.defaultMessage"
            }

            flash.bag = externalCustomer
        }

        flash.message = externalCustomer.hasErrors() ? 'Paramteros incorrectos' : 'Tarea concluida correctamente'
        redirect action: 'edit', id: externalCustomer.id
    }
}
