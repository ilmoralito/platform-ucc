package ni.edu.uccleon

import grails.plugins.rest.client.RestResponse
import grails.plugins.rest.client.RestBuilder
import org.springframework.http.MediaType
import grails.transaction.Transactional
import grails.gorm.DetachedCriteria
import grails.util.Environment

@Transactional
class EmployeeService {
    String employeeURL

    Map getEmployee(Long id) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$employeeURL/$id").json

        json
    }

    List getEmployees(Integer max = 200) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$employeeURL?max=$max").json

        json
    }

    Map getEmployeeByInstitutionalMail(final String institutionalMail) {
        RestBuilder restBuilder = new RestBuilder()
        def response = restBuilder.get("${employeeURL}/getEmployeeByInstitutionalMail?institutionalMail={institutionalMail}") {
            urlVariables institutionalMail: institutionalMail
        }.json

        response
    }

    RestResponse postEmployee(final String fullName, final String institutionalMail, final String authority, final String identityCard, final String inss, final List<String> coordinations) {
        RestBuilder restBuilder = new RestBuilder()
        final Map data = [
            fullName: fullName,
            institutionalMail: institutionalMail,
            authority: authority,
            identityCard: identityCard,
            inss: inss,
            coordinations: coordinations
        ]

        RestResponse response = restBuilder.post(employeeURL) {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json data
        }

        response
    }

    RestResponse putEmployee(final Integer id, final String fullName, final String institutionalMail, final String authority, final String identityCard, final String inss, final List<String> coordinations) {
        RestBuilder restBuilder = new RestBuilder()
        Map jsonMap = [
            id: id,
            fullName: fullName,
            institutionalMail: institutionalMail,
            authority: authority,
            identityCard: identityCard,
            inss: inss,
            coordinations: coordinations
        ]
        RestResponse response = restBuilder.put("$employeeURL/$id") {
            contentType MediaType.APPLICATION_JSON_VALUE
            header('Accept-Language', 'en')
            header('Accept', MediaType.APPLICATION_JSON_VALUE)
            json jsonMap
        }

        response
    }

    String getManagerMail(String location) {
        String email = ''

        if (Environment.current == Environment.DEVELOPMENT) {
            email = location == 'Administrative' ? 'mario.martinez@ucc.edu.ni' : 'amakenadog@gmail.com'
        } else {
            List employees = getEmployees()
            String coordination = location == 'Administrative' ? 'Administracion' : 'Direccion academica'

            email = employees.find {
                it.authority == "Manager" && coordination in it.coordinations.name
            }.institutionalMail
        }

        email
    }

    List getEmployeeCoordinations(Long id) {
        getEmployee(id).coordinations
    }

    Integer countEmployeesByCoordinationLocation(List<Integer> employeeList, String location) {
        this.getEmployees().findAll {
            it.id in employeeList && location in it.coordinations.location
        }.size()
    }

    List<Map> getEmployeeCoordinationsPrintQuota(Long id) {
        List coordinations = this.getEmployeeCoordinations(id).collect {
            [
                coordination: it.name,
                printQuota: it.printQuota
            ]
        }
    }
}
