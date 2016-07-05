package ni.edu.uccleon

import grails.plugins.rest.client.RestResponse
import grails.plugins.rest.client.RestBuilder
import org.springframework.http.MediaType
import grails.transaction.Transactional
import grails.util.Environment

@Transactional
class EmployeeService {
    String employeeURL

    def getEmployee(Long id) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$employeeURL/$id").json

        json
    }

    def getEmployees() {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get(employeeURL).json

        json
    }

    def updateEmployee(Long id, String fullName, String institutionalMail, String position, String authority, String identityCard, String inss) {
        RestBuilder restBuilder = new RestBuilder()
        Map jsonMap = [
            fullName: fullName,
            institutionalMail: institutionalMail,
            position: position,
            authority: authority,
            identityCar: identityCard,
            inss: inss
        ]
        RestResponse response = restBuilder.put("$employeeURL/$id") {
            contentType MediaType.APPLICATION_JSON_VALUE
            header("Accept-Language", "en")
            header("Accept", MediaType.APPLICATION_JSON_VALUE)
            json jsonMap
        }

        response
    }

    String getEmployeeInstitutionalMail(Map coordination) {
        List employees = getEmployees()

        if (Environment.current == Environment.DEVELOPMENT) {
            if (coordination.location == "Administrative") {
                "jorge.rojas@ucc.edu.ni"
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

    List getEmployeeCoordinations(Long id) {
        getEmployee(id).coordinations
    }

    String getEmployeeLocation(Long id, Boolean translate = false) {
        String location = getEmployee(id).coordination.location

        if (!translate) {
            location
        } else {
            if (location == "Administrative") {
                "Administrativa"
            } else {
                "Academica"
            }
        }
    }
}
