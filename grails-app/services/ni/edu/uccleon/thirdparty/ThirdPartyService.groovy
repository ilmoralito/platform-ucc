package ni.edu.uccleon.thirdparty

import grails.plugins.rest.client.RestResponse
import grails.plugins.rest.client.RestBuilder
import org.springframework.http.MediaType
import grails.transaction.Transactional
import org.grails.web.json.JSONObject

@Transactional
class ThirdPartyService {
    RestBuilder restBuilder = new RestBuilder()
    String thirdPartyURL

    JSONObject get(final Integer id) {
        restBuilder.get("$thirdPartyURL/$id").json
    }

    List<RestResponse> getAll(final Integer max = 100) {
        restBuilder.get("$thirdPartyURL?max=$max").json
    }

    RestResponse post(Map params) {
        restBuilder.post(thirdPartyURL) {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json params
        }
    }

    RestResponse put(Map params) {
        restBuilder.put("$thirdPartyURL/$params.id") {
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            json params
        }
    }

    JSONObject getThirdPartyEmployee(Map params) {
        restBuilder.get("$thirdPartyURL/$params.thirdPartyId/thirdpartyemployees/$params.thirdPartyEmployeeId").json
    }

    RestResponse postThirdPartyEmployee(Map params) {
        restBuilder.post("$thirdPartyURL/$params.thirdParty/thirdpartyemployees") {
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            json params
        }
    }

    RestResponse putThirdPartyEmployee(Map params) {
        restBuilder.put("$thirdPartyURL/$params.thirdPartyEmployeeId/thirdpartyemployees/$params.thirdPartyEmployeeId") {
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            json params
        }
    }
}
