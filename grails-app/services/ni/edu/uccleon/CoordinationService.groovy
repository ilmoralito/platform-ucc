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

    RestResponse postCoordination(String name, String extensionNumber, String location, Integer printQuota, List<Integer> colors) {
        RestBuilder restBuilder = new RestBuilder()
        Map data = [
            name: name,
            colors: colors,
            location: location,
            printQuota: printQuota,
            extensionNumber: extensionNumber
        ]

        RestResponse response = restBuilder.post(coordinationURL) {
            contentType MediaType.APPLICATION_JSON_VALUE
            header('Accept-Language', 'en')
            header('Accept', MediaType.APPLICATION_JSON_VALUE)
            json data
        }

        response
    }

    Map getCoordinationByName(String coordination) {
        List coordinations = getCoordinations().toList()
        Map data = coordinations.find { it.name == coordination }

        data
    }
}
