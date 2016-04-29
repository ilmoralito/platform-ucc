package ni.edu.uccleon

import groovy.xml.MarkupBuilder
import groovy.json.JsonOutput

class AppTagLib {
    def springSecurityService
    def employeeService
    def classroomService

    static defaultEncodeAs = [taglib: "html"]
    static namespace = "ucc"
    static encodeAsForTags = [
        getEmployee: "raw",
        classrooms: "raw",
        mountingType: "raw"
    ]

    def getEmployee = {
        MarkupBuilder mb = new MarkupBuilder(out)
        List employees = employeeService.getEmployees()

        mb.select(id: "employee", name: "employee", class: "form-control") {
            option(value: '') { mkp.yield "-Selecciona un perfil-" }
            employees.each { employee ->
                option(value: employee.id, "data-data": JsonOutput.toJson(employee)) {
                    mkp.yield employee.fullName
                }
            }
        }
    }

    def classrooms = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List classrooms = classroomService.getClassrooms().sort { it.code }
        Map<String, String> params = [:]
        Integer id = attrs?.id

        mb.div(class: "form-group") {
            delegate.select(name: "location", class: "form-control") {
                option(value: "") { mkp.yield "-Selecciona un lugar-" }

                classrooms.each { classroom ->
                    if (id == classroom.id) {
                        params.selected = true
                    }

                    params.value = classroom.id
                    option(params) { mkp.yield classroom?.name ?: classroom.code }
                }
            }
        }
    }

    def mountingType = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List<String> mountingTypes = ["Forma U", "Auditorium con mesas", "Auditorium sin mesas", "Grupo", "Sala de reunion", "Libre"]
        Map<String, String> params = [type: "radio", name: "mountingType"]

        mb.div {
            mountingTypes.each { m ->
                if (m == attrs?.mountingType) {
                    params.checked = true
                } else {
                    params.remove("checked")
                }

                params.value = m

                div(class: "radio") {
                    label {
                        input(params) {
                            mkp.yield m
                        }
                    }
                }
            }
        }
    }

    def getUserInfo = { attrs ->
        User user = springSecurityService.loadCurrentUser()
        Integer id = user.id
        String field = attrs.field

        Map<String, String> employee = employeeService.getEmployee(id)

        if (employee.containsKey(field)) {
            out << employee[field]
        } else {
            if (employee.coordination[field]) {
                out << employee.coordination[field]
            } else {
                out << "Insititucional"
            }
        }
    }
}
