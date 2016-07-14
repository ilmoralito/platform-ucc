cpackage ni.edu.uccleon

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

    String getManagerMail(String location) {
        String email = ""

        if (Environment.current == Environment.DEVELOPMENT) {
            email = location == "Administrative" ? "mario.martinez@ucc.edu.ni" : "amakenadog@gmail.com"
        } else {
            List employees = getEmployees()
            String coordination = location == "Administrative" ? "Administracion" : "Direccion academica"

            email = employees.find {
                it.authority == "Manager" && coordination in it.coordinations.name
            }.institutionalMail
        }

        email
    }

    List getEmployeeCoordinations(Long id) {
        getEmployee(id).coordinations
    }
}
