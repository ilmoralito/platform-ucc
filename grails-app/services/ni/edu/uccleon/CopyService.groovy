package ni.edu.uccleon

import static java.util.Calendar.*
import org.grails.web.json.JSONObject
import grails.transaction.Transactional
import org.springframework.http.MediaType
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

@Transactional
class CopyService {
    RestBuilder restBuilder = new RestBuilder()
    String copyURL

    JSONObject get(Integer id) {
        restBuilder.get("$copyURL/$id").json
    }

    List<JSONObject> getAll(Integer max = 100) {
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
        }
    }

    RestResponse copieStatus(final Integer employeeID) {
        restBuilder.get("$copyURL/copyStatus?employeeID={employeeID}") {
            urlVariables employeeID: employeeID
        }
    }

    RestResponse generalCopyStatus() {
        restBuilder.get("$copyURL/generalCopyStatus")
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

    List<JSONObject> getAllNotifiedOrAuthorized() {
        restBuilder.get("$copyURL/getAllNotifiedOrAuthorized").json
    }

    List<JSONObject> getAllRequestingAuthorization() {
        restBuilder.get("$copyURL/getAllRequestingAuthorization").json
    }

    List<JSONObject> filterNotifiedOrAuthorized(final List<Integer> coordinationList = [], final List<Integer> employeeList = []) {
        restBuilder.get("$copyURL/filterNotifiedOrAuthorized?coordinationList={coordinationList}&employeeList={employeeList}") {
            urlVariables coordinationList: coordinationList, employeeList: employeeList
        }.json
    }

    RestResponse filter(List<Integer> coordinationList, List<Integer> employeeList) {
        restBuilder.post("$copyURL/filterNotifiedOrAuthorized") {
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json {
                delegate.coordinationList = coordinationList
                delegate.employeeList = employeeList
            }
        }
    }

    RestResponse filter(List<Integer> coordinationList, List<Integer> employeeList, List<String> copyStatusList) {
        restBuilder.post("$copyURL/filter") {
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json {
                delegate.coordinationList = coordinationList
                delegate.employeeList = employeeList
                delegate.copyStatusList = copyStatusList
            }
        }
    }

    List yearList() {
        restBuilder.get("$copyURL/yearList").json
    }

    List<JSONObject> report(final Integer year, final Integer month) {
        restBuilder.get("$copyURL/report?year={year}&month={month}") {
            urlVariables year: year ?: new Date()[YEAR], month: month ?: new Date()[MONTH]
        }.json
    }

    RestResponse reportCopiesOutOfRange() {
        restBuilder.post("$copyURL/getAllOutOfRange") {
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json {
                statusList = ['NOTIFIED', 'AUTHORIZED', 'REQUEST_AUTHORIZATION']
            }
        }
    }

    List<String> getDocumentDescriptionByCoordination(final Integer coordinationID) {
        restBuilder.get("$copyURL/getDocumentDescriptionByCoordination?coordinationID={coordinationID}") {
            urlVariables coordinationID: coordinationID
        }.json
    }

    RestResponse summaryByEmployee(final Integer employeeID) {
        restBuilder.get("$copyURL/summaryByEmployee?employeeID={employeeID}") {
            urlVariables employeeID: employeeID
        }
    }

    RestResponse summaryByCoordinationAndYear(final Integer coordinationID, final Integer year) {
        restBuilder.get("$copyURL/summaryByCoordinationAndYear?coordinationID={coordinationID}&year={year}") {
            urlVariables coordinationID: coordinationID, year: year
        }
    }

    RestResponse summaryByCoordinationAndYearAndMonth(final Integer coordinationID, final Integer year, final Integer month) {
        restBuilder.get("$copyURL/summaryByCoordinationAndYearAndMonth?coordinationID={coordinationID}&year={year}&month={month}") {
            urlVariables coordinationID: coordinationID, year: year, month: month
        }
    }
}
