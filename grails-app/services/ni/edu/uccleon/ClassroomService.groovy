package ni.edu.uccleon

import grails.transaction.Transactional
import grails.plugins.rest.client.RestBuilder

@Transactional
class ClassroomService {
    String classroomURL

    def getClassroom(Integer id, Integer max = 25) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$classroomURL/$id?max=$max").json

        json
    }

    def getClassrooms() {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get(classroomURL).json

        json
    }
}
