package ni.edu.uccleon

import grails.transaction.Transactional
import org.springframework.http.MediaType
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

@Transactional
class CopyService {
    RestBuilder restBuilder = new RestBuilder()
    String copyURL

    Map get(Integer id) {
        restBuilder.get("$copyURL/$id").json
    }

    List<RestResponse> getAll(Integer max = 100) {
        restBuilder.get("$copyURL?max=$max").json
    }

    RestResponse post(Map params) {
        restBuilder.post(copyURL) {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json params
        }
    }

    RestResponse postExtraCopy(Map params) {
        restBuilder.post("$copyURL/saveExtraCopy") {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json params
        }
    }

    RestResponse put(Map params) {
        restBuilder.put("$copyURL/$params.id") {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json params
        }
    }

    RestResponse delete(final Integer id) {
        restBuilder.delete("$copyURL/$id") {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
        }.json
    }

    List copieStatus(final Integer employeeID) {
        restBuilder.get("$copyURL/copyStatus?employeeID={employeeID}") {
            urlVariables employeeID: employeeID
        }.json
    }

    List copiesToDateByEmployee(final Integer employeeID) {
        restBuilder.get("$copyURL/copiesToDateByEmployee?employeeID={employeeID}") {
            urlVariables employeeID: employeeID
        }.json
    }

    Long totalCopiesByCoordination(final Integer coordinationID) {
        restBuilder.get("$copyURL/totalCopiesByCoordination?coordinationID={coordinationID}") {
            urlVariables coordinationID: coordinationID
        }
    }
}
