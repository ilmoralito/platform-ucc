package ni.edu.uccleon

import grails.transaction.Transactional
import org.springframework.http.MediaType
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.json.JsonOutput

@Transactional
class ClassroomService {
    String classroomURL

    Map getClassroom(Integer id) {
        RestBuilder restBuilder = new RestBuilder()
        def json = restBuilder.get("$classroomURL/$id").json

        json
    }

    List getClassrooms(Integer max = 100) {
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

    RestResponse updateClassroom(Integer id, String code, String name, Integer capacity, Boolean airConditioned) {
        RestBuilder restBuilder = new RestBuilder()
        Map data = [code: code, name: name, capacity: capacity, airConditioned: airConditioned]

        RestResponse response = restBuilder.put("$classroomURL/$id") {
            contentType MediaType.APPLICATION_JSON_VALUE
            header("Accept-Language", "en")
            header("Accept", MediaType.APPLICATION_JSON_VALUE)
            json data
        }

        response
    }

    List groupClassroomsByCode(List classrooms) {
        List classroomList = classrooms ?: getClassrooms()

        classroomList.groupBy { it.code[0] }.sort { it.key }.collect { c ->
            [
                name: c.key,
                classrooms: c.value.collect { classroom ->
                    [
                        id: classroom.id,
                        code: classroom.code,
                        name: classroom.name
                    ]
                }
            ]
        }
    }

    List filter(List<Integer> floorList, List<String> codeList, List<Boolean> airConditionedList) {
        List classrooms = getClassrooms()

        (floorList, codeList, airConditionedList) = [floor: floorList, code: codeList, airConditioned: airConditionedList].collect { prop, list ->
            list ?: classrooms[prop].unique()
        }

        classrooms
            .findAll { c -> ((c.code[1].toInteger()) in floorList) }
            .findAll { c -> c.code[0] in codeList }
            .findAll { c -> c.airConditioned in airConditionedList }
    }
}
