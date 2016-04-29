package ni.edu.uccleon

import grails.transaction.Transactional
import grails.plugins.rest.client.RestBuilder

@Transactional
class ClassroomService {
    String classroomURL

    def getClassrooms() {
        RestBuilder restBuilder = new RestBuilder()

        //log.info = "Retrieving classrooms"
        def json = restBuilder.get(classroomURL).json

        json
    }

    def getClassroom(Integer id, Integer max = 25) {
        RestBuilder restBuilder = new RestBuilder()

        //log.info = "Retrieving classroom with id [$id]"
        def json = restBuilder.get("$classroomURL/$id?max=$max").json

        json
    }
}
