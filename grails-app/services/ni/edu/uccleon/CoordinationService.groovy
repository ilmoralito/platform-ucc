package ni.edu.uccleon

import grails.plugins.rest.client.RestResponse
import grails.plugins.rest.client.RestBuilder
import org.springframework.http.MediaType
import grails.transaction.Transactional

@Transactional
class CoordinationService {
    String coordinationURL
    RestBuilder restBuilder = new RestBuilder()

    Map getCoordination(Integer id) {
        restBuilder.get("$coordinationURL/$id").json
    }

    List getCoordinations(Integer max = 100) {
        restBuilder.get("$coordinationURL?max=$max").json
    }

    Map getCoordinationsByCoordinationName(final String name) {
        restBuilder.get("${coordinationURL}/searchByName?name={name}") {
            urlVariables name: name
        }.json
    }

    RestResponse postCoordination(Map params) {
        restBuilder.post(coordinationURL) {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json params
        }
    }

    RestResponse putCoordination(Map params) {
        restBuilder.put("$coordinationURL/$params.id") {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json params
        }
    }

    Map getCoordinationByName(String coordination) {
        List coordinations = getCoordinations().toList()

        coordinations.find { it.name == coordination }
    }

    List groupCoordinationsByLocation() {
        this.getCoordinations().groupBy { it.location }.collect {
            [
                location: it.key,
                coordinations: it.value.collect {
                    [ name: it.name ]
                }.sort { a, b ->
                    a.name <=> b.name
                }
            ]
        }.sort { a, b ->
            a.location <=> b.location
        }
    }

    List employeesGroupedByCoordination() {
        restBuilder.get("$coordinationURL/employeesGroupedByCoordination").json
    }
}
