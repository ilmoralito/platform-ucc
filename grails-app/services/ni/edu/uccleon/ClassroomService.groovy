package ni.edu.uccleon

import grails.transaction.Transactional
import org.springframework.http.MediaType
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

@Transactional
class ClassroomService {
    String classroomURL

    def getClassroom(Integer id, Integer max = 25) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$classroomURL/$id?max=$max").json

        json
    }

    def getClassrooms(Integer max = 100) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$classroomURL?max=$max").json

        json
    }

    RestResponse createClassroom(String code, String name, Integer capacity, Boolean airConditioned) {
        RestBuilder restBuilder = new RestBuilder()
        Map data = [code: code, name: name, capacity: capacity, airConditioned: airConditioned]

        RestResponse response = restBuilder.post(classroomURL) {
            contentType MediaType.APPLICATION_JSON_VALUE
            header("Accept-Language", "en")
            header("Accept", MediaType.APPLICATION_JSON_VALUE)
            json data
        }

        response
    }

    List groupClassroomsByCode() {
        List classroomList = getClassrooms().toList()

        classroomList.groupBy { it.code[0] }.sort { it.key }.collect { c ->
            [
                name: c.key,
                classrooms: c.value.collect { classroom ->
                    [
                        code: classroom.code,
                        name: classroom.name
                    ]
                }
            ]
        }
    }
}
