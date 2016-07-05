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

    RestResponse postCoordination(String name, String extensionNumber, String location, List<String> colors) {
        RestBuilder restBuilder = new RestBuilder()
        Map data = [name: name, extensionNumber: extensionNumber, location: location, colors: colors]

        RestResponse response = restBuilder.post(coordinationURL) {
            contentType MediaType.APPLICATION_JSON_VALUE
            header("Accept-Language", "en")
            header("Accept", MediaType.APPLICATION_JSON_VALUE)
            json data
        }

        response
    }

    List getCoordinationColors(String coordination) {
        List coordinations = getCoordinations().toList()
        def colors = coordinations.find { it.name == coordination }.colors

        println colors

        colors
    }
}
