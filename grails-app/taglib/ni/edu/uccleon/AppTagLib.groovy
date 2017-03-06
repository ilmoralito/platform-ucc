package ni.edu.uccleon

import groovy.xml.MarkupBuilder
import groovy.json.JsonOutput

class AppTagLib {
    def externalCustomerService
    def springSecurityService
    def coordinationService
    def classroomService
    def employeeService
    def voucherService
    def guestService

    static defaultEncodeAs = [taglib: 'html']
    static namespace = 'ucc'
    static encodeAsForTags = [
        classrooms: 'raw',
        mountingType: 'raw',
        tableType: 'raw',
        chairs: 'raw',
        tableclothColor: 'raw',
        externalCustomers: 'raw',
        activityStatus: 'raw',
        activityWidget: 'raw',
        getEmployees: 'raw',
        getGuests: 'raw',
        eventWidget: 'raw',
        activityDatalist: 'raw'
    ]

    def notificationMessage = { attrs ->
        String message = ""
        String activityStatus = attrs.status
        String activityLocation = attrs.location
        String activityCoordination = attrs.coordination
        Integer currentUserId = springSecurityService.loadCurrentUser().id

        switch(activityStatus) {
            case "pending":
                message = "Notificar"
            break
            // case {activityStatus != "pending" && employeeService.getEmployeeCoordinations(currentUserId).any { it.name == activityCoordination }}:
            //     message = "En proceso"
            // break
            case {activityStatus == "notified" && activityLocation == "Academic"}:
                message = "Aprobar"
            break
            case {activityStatus == "notified" && activityLocation == "Administrative"}:
                message = "Autorizar"
            break
            case "granted":
                message = "Autorizar"
            break
            case "approved":
                message = "Archivar solicitud"
            break
            case "done":
                message = "Archivado"
            break
        }

        out << message
    }

    def classrooms = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List classrooms = classroomService.getAll().sort { it.code }
        Map<String, String> params = [:]
        Integer id = attrs.int("id")

        mb.div(class: "form-group") {
            label(for: "location") {mkp.yield "Lugar"}

            delegate.select(name: "location", id: "location", class: "form-control") {
                option(value: "") {
                    mkp.yield "-Selecciona un lugar-"
                }

                classrooms.each { classroom ->
                    if (id == classroom.id) {
                        params.selected = true
                    } else {
                        params.remove("selected")
                    }

                    if (classroom.capacity) {
                        params["data-capacity"] = classroom.capacity
                    } else {
                        params.remove("data-capacity")
                    }

                    params.value = classroom.id
                    option(params) {
                        mkp.yield classroom?.name ?: classroom.code
                    }
                }
            }
        }
    }

    def mountingType = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        String mountingTypeInstance = attrs.mountingTypeInstance
        Map<String, String> params = [name: "mountingType", type: "radio"]
        List<String> mountingTypes = [
            "Libre",
            "Forma U",
            "Auditorium con mesas",
            "Auditorium sin mesas",
            "Sala de reunion",
            "Grupo"
        ]

        mb.div {
            label "Tipo de montaje"

            mountingTypes.each { mountingType ->
                params.value = mountingType

                if (mountingType == mountingTypeInstance) {
                    params.checked = true
                } else {
                    params.remove("checked")
                }

                div(class: "radio") {
                    label {
                        input(params) {
                            mkp.yield mountingType
                        }
                    }
                }
            }
        }
    }

    def tableType = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List<String> tableTypes = grailsApplication.config.ni.edu.uccleon.tableTypes
        List<String> tableTypeList = attrs.list("tableTypeList")
        Map<String, String> params = [name: "tableTypes", type: "checkbox"]

        mb.div {
            label "Tipo de mesas"

            tableTypes.each { tableType ->
                if (tableType in tableTypeList) {
                    params.checked = true
                } else {
                    params.remove("checked")
                }

                params.value = tableType

                div(class: "checkbox") {
                    label {
                        input(params)
                        mkp.yield tableType
                    }
                }
            }
        }
    }

    def chairs = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List<String> chairTypes = grailsApplication.config.ni.edu.uccleon.chairTypes
        List<String> chairTypeList = attrs.list("chairTypeList")
        Map<String, String> params = [name: "chairTypes", type: "checkbox"]

        mb.div {
            label "Tipo de sillas"

            chairTypes.each { chairType ->
                if (chairType in chairTypeList) {
                    params.checked = true
                } else {
                    params.remove("checked")
                }

                params.value = chairType

                div(class: "checkbox") {
                    label {
                        input(params)
                        mkp.yield chairType
                    }
                }
            }
        }
    }

    def externalCustomers = { attrs ->
        List<ExternalCustomer> externalCustomers = externalCustomerService.getAll()
        Integer externalCustomer = attrs.externalCustomer

        out << g.select(
            name: "externalCustomer",
            noSelection: [null: '-Selecciona cliente cliente-'],
            from: externalCustomers,
            optionKey: "id",
            optionValue: "name",
            value: externalCustomer,
            class: "form-control"
        )
    }

    def activityStatus = { attrs ->
        String status = attrs.status, message

        if (status == "pending") {
            message = "Pendiente"
        } else if (status == "notified"){
            message = "Notificado"
        } else if (status == "granted") {
            message = "Aprobado"
        } else if (status == "approved") {
            message = "Autorizado"
        } else {
            message = "Atendido"
        }

        out << message
    }

    def activityWidget = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        ActivityWidget a = attrs.activityWidget

        mb.div {
            label "Nombre de la actividad"
            p a.name

            label "Creado por"
            p a.createdBy.username

            label "Fecha y hora de creacion"
            p g.formatDate(format: "yyyy-MM-dd HH:mm", date: a.dateCreated)

            label "Estado"
            p ucc.activityStatus(status: a.status)

            label "Coordinacion"
            p a.coordination

            if (a.status == "pending") {
                label "Dias hábiles para notificar"
                p a.daysAllowedToNotify > 0 ? a.daysAllowedToNotify : "Dias para enviar notificacion agotados"
            }

            if (a.status != "pending") {
                label "Notificado por"
                p a.notifiedBy.username

                label "Fecha y hora de notificacion"
                p g.formatDate(format: "yyyy-MM-dd HH:mm", date: a.notificationDate)
            }
        }
    }

    def requestName = { attrs ->
        User currentUser = springSecurityService.currentUser
        List<String> authorities = currentUser.authorities.authority

        if (authorities.contains("ROLE_ADMINISTRATIVE_SUPERVISOR")) {
            out << "de autorizacion"
        } else if (authorities.contains("ROLE_ACADEMIC_SUPERVISOR")) {
            out << "de aprobacion"
        } else if (authorities.contains("ROLE_PROTOCOL_SUPERVISOR")) {
            out << "autorizadas"
        }
    }

    def getEmployees = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List employees = attrs.employees ?: employeeService.getEmployees()
        String name = attrs.name ?: "employee"
        Integer currentEmployee = attrs?.currentEmployee
        Map<String, String> params = [id: name, name: name, class: "form-control"]
        Map<String, String> optionParams = [:]

        if (attrs.multiple) {
            params.multiple = "multiple"
        }

        mb.select(params) {
            employees.each { employee ->
                optionParams.value = employee.id
                optionParams["data-data"] = JsonOutput.toJson(employee)

                if (currentEmployee) {
                    if (currentEmployee == employee.id) {
                        optionParams.selected = true
                    } else {
                        optionParams.remove("selected")
                    }
                }

                option(optionParams) {
                    mkp.yield employee.fullName
                }
            }
        }
    }

    def employee = { attrs ->
        String employeeFullName = employeeService.getEmployee(attrs.id).fullName

        out << employeeFullName
    }

    def getClassroom = { attrs ->
        Integer id = attrs.int("id")
        println attrs.id
        Map classroom = classroomService.get(id)

        out << classroom?.name ?: classroom.code
    }

    def eventWidget = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        EventWidget e = attrs.eventWidget
        Integer position = attrs.int("position")

        mb.div {
            p "Requerimientos"

            div(class: "row") {
                div(class: "col-md-3") {
                    p "Medios"

                    e.means.each { m ->
                        p {
                            if (e.events[position][m.key]) {
                                mkp.yieldUnescaped "<i class='fa fa-check-square-o'></i>"
                            } else {
                                mkp.yieldUnescaped "<i class='fa fa-square-o'></i>"
                            }

                            mkp.yield m.value
                        }
                    }
                }
            }
        }
    }

    def getHour = { attrs ->
        Integer hour = attrs.int("hour")

        List hours = [
            [time: 8, value: "8:00"],
            [time: 9, value: "9:00"],
            [time: 10, value: "10:00"],
            [time: 11, value: "11:00"],
            [time: 12, value: "12:00"],
            [time: 13, value: "1:00"],
            [time: 14, value: "2:00"],
            [time: 15, value: "3:00"],
            [time: 16, value: "4:00"],
            [time: 17, value: "5:00"]
        ]

        out << hours.find { it.time == hour }.value
    }

    def foodInSpanish = { attrs ->
        out << voucherService.getFoodInSpanish(attrs.list('foods')).join(', ')
    }

    def activityDatalist = { attrs ->
        MarkupBuilder builder = new MarkupBuilder(out)

        builder.div(class: 'form-group') {
            label(for: 'activity') {
                mkp.yield 'Actividad'
            }

            input(list: 'activities', name: 'activity', id: 'activity', value: attrs.activity, class: 'form-control')
            datalist(id: 'activities') {
                attrs.activities.each { activity ->
                    option(value: activity) {
                        mkp.yield activity
                    }
                }
            }
        }
    }

    def voucherStatusInSpanish = { attrs ->
        if (attrs.status == 'pending') {
            out << 'Pendiente'
        } else if (attrs.status == 'notified') {
            out << 'Notificado'
        } else if (attrs.status == 'approved') {
            out << 'Aprobado'
        } else if (attrs.status == 'canceled') {
            out << 'Cancelado'
        }
    }

    def slug = { attrs ->
        String text = attrs.text
        Integer size = attrs.int('size') ?: 65

        if (text) {
            out << text.take(size) + '...'
        }
    }

    def copyStatus = { attrs ->
        String status = attrs.status

        switch(status) {
            case 'PENDING':
                out << 'PENDIENTE'
            break
            case 'NOTIFIED':
                out << 'NOTIFICADO'
            break
            case 'CANCELED':
                out << 'CANCELADO'
            break
            case 'APPROVED':
                out << 'APROVADO'
            break
            case 'AUTHORIZED':
                out << 'AUTORIZADO'
            break
            case 'ATTENDED':
                out << 'ATENDIDO'
            break
        }
    }

    def coordinationLocation = { attrs ->
        if (attrs.location == 'Administrative') {
            out << 'Administrativo'
        } else {
            out << 'Academico'
        }
    }

    def enabled = { attrs ->
        if (attrs.enabled == true) {
            out << 'Habilitado'
        } else {
            out << 'No habilitado'
        }
    }
}

