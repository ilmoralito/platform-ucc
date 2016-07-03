package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rest.client.RestResponse

@Secured(["ROLE_ADMIN"])
class ClassroomController {
    def classroomService

    static allowedMethods = [
        index: ["GET", "POST"],
        create: "POST",
        edit: "GET",
        update: "POST"
    ]

    def index() {
        List classrooms = []

        if (request.post) {
            List<Integer> floorList = params.list("floor")*.toInteger()
            List<String> codeList = params.list("code")
            List<Boolean> airConditionedList = params.list("airConditioned")*.toBoolean()

            classrooms = classroomService.filter(floorList, codeList, airConditionedList)
        }

        [classrooms: classroomService.groupClassroomsByCode(classrooms)]
    }

    def create(ClassroomCommand cmd) {
        if (cmd.hasErrors()) {
            cmd.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = cmd
        } else {
            RestResponse result = classroomService.createClassroom(cmd.code, cmd.name, cmd.capacity, cmd.airConditioned)

            flash.message = result.status >= 400 ? "A ocurrido un error" : "Creado correctamente"
        }

        redirect action: "index"
    }

    def edit(Integer id) {
        [classroom: classroomService.getClassroom(id)]
    }

    def update(Integer id, ClassroomCommand cmd) {
        Map classroom = classroomService.getClassroom(id)

        if (classroom.containsKey("error")) {
            response.sendError 404
        }

        if (cmd.hasErrors()) {
            cmd.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = cmd
        } else {
            RestResponse result = classroomService.updateClassroom(id, cmd.code, cmd.name, cmd.capacity, cmd.airConditioned)

            flash.message = result.status >= 400 ? "A ocurrido un error" : "Creado correctamente"
        }

        redirect action: "edit", id: id
    }
}

class ClassroomCommand {
    String code
    String name
    Integer capacity
    Boolean airConditioned = false

    static constraints = {
        code blank: false, unique: true, validator: { code ->
            List<String> buildingCode = ["B", "C", "D", "E", "K"]
            String letter = code[0].toUpperCase()
            String floor = code[1]

            if (!(letter in buildingCode)) {
                return "not.valid.letter.code"
            }

            if (!(floor in ["1", "2"])) {
                return "not.valid.floor.number"
            }

            if (!(code.size() in [4, 5])) {
                return "not.valid.code.size"
            }
        }
        name nullable: true
        capacity nullable: true, validator: { capacity ->
            if (capacity) {
                capacity > 0
            }
        }
        airConditioned nullable: false
    }
}
