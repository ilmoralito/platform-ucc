package ni.edu.uccleon

import grails.transaction.Transactional
import org.springframework.http.MediaType
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

@Transactional
class ClassroomService {
    String classroomURL

    RestBuilder restBuilder = new RestBuilder()

    Map get(Integer id) {
        restBuilder.get("$classroomURL/$id").json
    }

    List getAll(Integer max = 100) {
        restBuilder.get("$classroomURL?max=$max").json
    }

    RestResponse post(Map params) {
        restBuilder.post(classroomURL) {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json params
        }
    }

    RestResponse put(Map params) {
        restBuilder.put("$classroomURL/$params.id") {
            contentType MediaType.APPLICATION_JSON_VALUE
            header 'Accept-Language', 'en'
            header 'Accept', MediaType.APPLICATION_JSON_VALUE
            json params
        }
    }

    List groupedByCode() {
        this.getAll().groupBy { it.code[0] }.sort { it.key }.collect { c ->
            [
                name: c.key,
                classrooms: c.value.collect { classroom ->
                    [
                        id: classroom.id,
                        code: classroom.code,
                        name: classroom.name
                    ]
                }.sort { a, b ->
                    a.code <=> b.code
                }
            ]
        }
    }
}

