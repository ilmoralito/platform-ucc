package ni.edu.uccleon

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import grails.util.Environment

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

    def getEmployeeInstitutionalMail(Map coordination) {
        List employees = getEmployees()

        if (Environment.current == Environment.DEVELOPMENT) {
            if (coordination.location == "Administrative") {
                "mario.martinez@ucc.edu.ni"
            } else {
                "amakenadog@gmail.com"
            }
        } else {
            if (coordination.location == "Administrative") {
                employees.find {
                    it.authority == "Manager" && it.coordination.name == "Administracion"
                }.institutionalMail
            } else {
                employees.find {
                    it.authority == "Manager" && it.coordination.name == "Direccion academica"
                }.institutionalMail
            }
        }
    }
}
