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
    RestBuilder restBuilder = new RestBuilder()

    Map getEmployee(Long id) {
        restBuilder.get("$employeeURL/$id").json
    }

    List getEmployees(Integer max = 200) {
        restBuilder.get("$employeeURL?max=$max").json
    }

    Map getEmployeeByInstitutionalMail(final String institutionalMail) {
        restBuilder.get("$employeeURL/getEmployeeByInstitutionalMail?institutionalMail={institutionalMail}") {
            urlVariables institutionalMail: institutionalMail
        }.json
    }

    RestResponse post(Map params) {
        restBuilder.post(employeeURL) {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json params
        }
    }

    RestResponse put(Map params) {
        restBuilder.put("$employeeURL/$params.id") {
            contentType MediaType.APPLICATION_JSON_VALUE
            header('Accept-Language', 'en')
            header('Accept', MediaType.APPLICATION_JSON_VALUE)
            json params
        }
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

    // TODO: improve this code
    Integer countEmployeesByCoordinationLocation(List<Integer> employeeList, String location) {
        this.getEmployees().findAll {
            it.id in employeeList && location in it.coordinations.location
        }.size()
    }

    List<Map> getCoordinationsPrintQuota(final Integer employeeID) {
        restBuilder.get("$employeeURL/coordinationsPrintQuota?employeeID={employeeID}") {
            urlVariables employeeID: employeeID
        }.json
    }
}
