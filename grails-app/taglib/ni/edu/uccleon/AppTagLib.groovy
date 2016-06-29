package ni.edu.uccleon

import groovy.xml.MarkupBuilder
import groovy.json.JsonOutput

class AppTagLib {
    def springSecurityService
    def employeeService
    def classroomService
    def externalCustomerService
    def guestService
    def voucherService

    static defaultEncodeAs = [taglib: "html"]
    static namespace = "ucc"
    static encodeAsForTags = [
        profile: "raw",
        classrooms: "raw",
        mountingType: "raw",
        tableType: "raw",
        chairs: "raw",
        tableclothColor: "raw",
        externalCustomers: "raw",
        activityStatus: "raw",
        activityWidget: "raw",
        voucherStatus: "raw",
        getEmployeeList: "raw",
        getEmployees: "raw",
        getGuests: "raw",
        getColors: "raw"
    ]

    def profile = {
        MarkupBuilder mb = new MarkupBuilder(out)
        User currentUser = springSecurityService.currentUser
        Map<String, String> employee = employeeService.getEmployee(currentUser.id)

        mb.div {
            label "Nombre y apellido"
            p employee.fullName

            label "Correo institucional"
            p employee.institutionalMail

            label "Cargo"
            p employee.position

            label "Rol"
            p employee.authority

            label "Cedula"
            p employee.identityCard

            label "INSS"
            p employee.inss

            label "Coordinacion"
            p employee.coordination.name

            label "Numero de extension"
            p employee.coordination.extensionNumber

            label "Area"
            p employee.coordination.location

            label "Colores de coordinacion"
            employee.coordination.colors.each { color ->
                p color.name
            }
        }
    }

    def classrooms = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List classrooms = classroomService.getClassrooms().sort { it.code }
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

    def tableclothColor = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        User currentUser = springSecurityService.currentUser
        List coordinationColors = employeeService.getEmployee(currentUser.id).coordination.colors
        List<String> tableclothColorList = attrs.list("tableclothColorList")
        Map<String, String> params = [name: "tableclothColors", type: "checkbox"]

        mb.div {
            label "Colores de manteles"

            if (coordinationColors.size() == 1) {
                String color = coordinationColors[0].name

                params.value = color

                div(class: "checkbox") {
                    label {
                        if (color in tableclothColorList) {
                            params.checked = true
                        } else {
                            params.remove("checked")
                        }

                        input(params)
                        mkp.yield coordinationColors[0].name
                    }
                }
            } else {
                coordinationColors.each { tableclothColor ->
                    params.value = tableclothColor.name

                    div(class: "checkbox") {
                        label {
                            if (tableclothColor.name in tableclothColorList) {
                                params.checked = true
                            } else {
                                params.remove("checked")
                            }

                            input(params)
                            mkp.yield tableclothColor.name
                        }
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
            noSelection: ['': '-Selecciona cliente cliente-'],
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

            label "Notificado"
            p a.notified ? "Si" : "No"

            if (!a.notified) {
                label "Dias permitidos para notificar"
                p a.daysAllowedToNotify > 0 ? a.daysAllowedToNotify : "Dias para enviar notificacion agotados"
            }

            if (a.notified) {
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

    def voucherStatus = { attrs ->
        String status = attrs.status

        if (status == "pending") {
            out << "Pendiente"
        } else if (status == "notified") {
            out << "Notificado"
        } else {
            out << "Aprovado"
        }
    }

    def getEmployeeList = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List employees = attrs.employees
        Map<String, String> params = [:]

        mb.div(class: "form-group") {
            delegate.select(name: "employee", id: "employee", class: "form-control") {
                employees.each { e ->
                    params.value = e.id
                    params["data-information"] = JsonOutput.toJson(e)

                    option(params) {
                        mkp.yield e.fullName
                    }
                }
            }
        }
    }

    def getEmployees = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List employees = employeeService.getEmployees()
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

    def getGuests = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List<Guest> guests = guestService.getGuests()
        Integer guestId = attrs.guestId ?: 1
        Map<String, String> params = [:]

        mb.div {
            label(for: "guests") {
                mkp.yield "Invitados"
            }

            delegate.select(name: "guests", id: "guests", class: "form-control") {
                guests.each { guest ->
                    if (guest.id == guestId) {
                        params.selected = true
                    } else {
                        params.remove("selected")
                    }

                    option(value: guest.id) {
                        mkp.yield guest.fullName
                    }
                }
            }

        }
    }

    def getColors = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        List colorList = attrs.colorList
        List colorParamList = attrs.colorParamList
        Map params = [type: "checkbox", name: "colors"]

        mb {
            label "Colores"

            colorList.each { color ->
                params.value = color.name

                if (color.name in colorParamList) {
                    params.selected = true
                } else {
                    params.remove("selected")
                }

                div(class: "checkbox") {
                    label {
                        input(params)
                        mkp color.name
                    }
                }
            }
        }
    }
}
