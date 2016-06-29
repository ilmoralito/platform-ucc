package ni.edu.uccleon

import grails.plugins.rest.client.RestResponse
import grails.plugins.rest.client.RestBuilder
import org.springframework.http.MediaType
import grails.transaction.Transactional

@Transactional
class ColorService {
    String colorURL

    def getColor(Integer id) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$colorURL/$id").json

        json
    }

    def getColors() {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get(colorURL).json

        json
    }
}
