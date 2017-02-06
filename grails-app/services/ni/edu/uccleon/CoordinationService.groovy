package ni.edu.uccleon

import grails.plugins.rest.client.RestResponse
import grails.plugins.rest.client.RestBuilder
import org.springframework.http.MediaType
import grails.transaction.Transactional

@Transactional
class CoordinationService {
    String coordinationURL

    Map getCoordination(Integer id) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$coordinationURL/$id").json

        json
    }

    List getCoordinations(Integer max = 100) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$coordinationURL?max=$max").json

        json
    }

    Map getCoordinationsByCoordinationName(final String name) {
        RestBuilder restBuilder = new RestBuilder()
        restBuilder.get("${coordinationURL}/searchByName?name={name}") {
            urlVariables name: name
        }.json
    }

    RestResponse postCoordination(Map params) {
        RestBuilder restBuilder = new RestBuilder()
        Map data = [
            name: params.name,
            colors: params.colors,
            location: params.location,
            printQuota: params.printQuota,
            extensionNumber: params.extensionNumber
        ]

        RestResponse restResponse = restBuilder.post(coordinationURL) {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json data
        }

        restResponse
    }

    RestResponse putCoordination(Map params) {
        RestBuilder restBuilder = new RestBuilder()
        Map data = [
            id: params.id,
            name: params.name,
            colors: params.colors,
            location: params.location,
            printQuota: params.printQuota,
            extensionNumber: params.extensionNumber
        ]

        RestResponse restResponse = restBuilder.put("$coordinationURL/$params.id") {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json data
        }

        restResponse
    }

    Map getCoordinationByName(String coordination) {
        List coordinations = getCoordinations().toList()
        Map data = coordinations.find { it.name == coordination }

        data
    }

    List groupCoordinationsByLocation() {
        List coordinations = this.getCoordinations().groupBy { it.location }.collect {
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

        coordinations
    }
}
