package ni.edu.uccleon

import grails.transaction.Transactional
import grails.plugins.rest.client.RestBuilder

@Transactional
class CoordinationService {
    String coordinationURL

    def getCoordinations(Integer id, Integer max = 25) {
        RestBuilder restBuilder = new RestBuilder()

        def json = restBuilder.get("$coordinationURL/$id?max=$max").json

        json
    }

    def getCoordination() {
        RestBuilder restBuilder = new RestBuilder()

        def json = restBuilder.get(coordinationURL).json

        json
    }
}
