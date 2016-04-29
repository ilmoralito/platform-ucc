package ni.edu.uccleon

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional

@Transactional
class EmployeeService {
    String employeeURL

    def getEmployees() {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get(employeeURL).json

        json
    }

    def getEmployee(Long id) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$employeeURL/$id").json

        json
    }
}
