package ni.edu.uccleon

import groovy.xml.MarkupBuilder
import groovy.json.JsonOutput

class AppTagLib {
    def springSecurityService
    def employeeService
    def classroomService
    def externalCustomerService

    static defaultEncodeAs = [taglib: "html"]
    static namespace = "ucc"
    static encodeAsForTags = [
        profile: "raw",
        getEmployee: "raw",
        classrooms: "raw",
        mountingType: "raw",
        tableType: "raw",
        chairs: "raw",
        tableclothColor: "raw",
        externalCustomers: "raw",
        activityWidget: "raw"
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
        Map<String, String> params = [name: "tableclothColors", type: "checkbox"]

        if (coordinationColors.size() == 1) {
            mb.input(type: "hidden", value: coordinationColors[0])
        } else {
            mb.div {
                label "Colores de manteles"

                coordinationColors.each { tableclothColor ->

                    div(class: "checkbox") {
                        label {
                            params.value = tableclothColor.name

                            if (tableclothColor.name in attrs.list("tableclothColorList")) {
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
        String status = attrs.status

        if (status == "pending") {
            out << "Pendiente"
        } else if (status == "granted") {
            out << "Aceptado"
        } else if (status == "approved") {
            out << "Aprobado"
        } else {
            out << "Atendido"
        }
    }

    def activityWidget = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        ActivityWidget a = attrs.activityWidget

        mb.div {
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

            label "Dias permitidos para notificar"
            p a.daysAllowedToNotify > 0 ? a.daysAllowedToNotify : "Dias para enviar notificacion agotados"

            if (a.notified) {
                label "Notificado por"
                p a.notifiedBy.username

                label "Fecha y hora de notificacion"
                p g.formatDate(format: "yyyy-MM-dd HH:mm", date: a.notificationDate)
            }
        }
    }
}
