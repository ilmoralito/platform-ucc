package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class ExternalCustomerService {

    def getAll() {
        ExternalCustomer.list()
    }

    def get(Long id) {
        ExternalCustomer.get(id)
    }
}
