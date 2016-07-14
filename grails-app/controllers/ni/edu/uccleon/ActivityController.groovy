package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured
import com.craigburke.document.builder.PdfDocumentBuilder

@Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ADMINISTRATIVE_SUPERVISOR", "ROLE_ACADEMIC_SUPERVISOR", "ROLE_PROTOCOL_SUPERVISOR"])
class ActivityController {
    def springSecurityService
    def activityService
    def employeeService
    def eventService
    def classroomService
    def coordinationService

    static allowedMethods = [
        index: "GET",
        init: "GET",
        activityName: ["GET", "POST"],
        events: ["GET", "POST"],
        cloneEvent: "GET",
        removeEvent: "GET",
        save: "POST",
        edit: "GET",
        show: "GET",
        updateActivity: "POST",
        cloneActivityEvent: "GET",
        removeActivityEvent: "GET",
        sendNotification: "POST",
        removeActivity: "POST",
        printActivity: "GET"
    ]

    def index(String calendarType) {
        List<Activity> activities = activityService.getActivities()

        [activities: activities, calendarType: calendarType]
    }

    def init() {
        session.activity = [:]
        session.events = []
        session.coordination = ""

        redirect action: "activityName"
    }

    def activityName(ActivityCommand command) {
        Integer currentUserId = springSecurityService.loadCurrentUser().id
        List coordinations = employeeService.getEmployeeCoordinations(currentUserId)

        if (request.post) {
            if (command.hasErrors()) {
                command.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = command
                flash.message = "Dato necesario"
            } else {
                session.activity.name = command.name
                session.activity.externalCustomer = command.externalCustomer
                session.coordination = command.coordination

                redirect action: "events", params: [index: params.index]
                return
            }
        }

        [coordinations: coordinations]
    }

    def events(EventCommand command) {
        if (request.post) {
            if (command.hasErrors()) {
                command.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bean = command
                flash.message = "Datos Incorrectos"
                return
            }

            Integer index = params.index == "" ? 0 : params.int("index")

            if (session?.events?.size() > 0) {
                Event event = session?.events?.getAt(index)

                event.date = command.date
                event.numberOfPeople = command.numberOfPeople
                event.startTime = command.startTime
                event.endingTime = command.endingTime
                event.location = command.location
                event.audiovisual = command.audiovisual
                event.wifi = command.wifi
                event.sound = command.sound
                event.speaker = command.speaker
                event.microfone = command.microfone
                event.pointer = command.pointer
                event.water = command.water
                event.coffee = command.coffee
                event.cookies = command.cookies
                event.waterBottles = command.waterBottles
                event.mountingType = command.mountingType
                event.presidiumTable = command.presidiumTable
                event.flags = command.flags
                event.podium = command.podium
                event.tableForSpeaker = command.tableForSpeaker
                event.tablecloths = command.tablecloths

                event.tableTypes.clear()
                event.tableTypes = command.tableTypes

                event.chairTypes.clear()
                event.chairTypes = command.chairTypes

                event.tableclothColors.clear()
                event.tableclothColors = command.tableclothColors

                event.refreshment = command.refreshment
                event.breakfast = command.breakfast
                event.lunch = command.lunch
                event.dinner = command.dinner
                event.observation = command.observation
            } else {
                Event event = new Event(
                    date: command.date,
                    startTime: command.startTime,
                    endingTime: command.endingTime,
                    numberOfPeople: command.numberOfPeople,
                    location: command.location,
                    audiovisual: command.audiovisual,
                    wifi: command.wifi,
                    sound: command.sound,
                    speaker: command.speaker,
                    microfone: command.microfone,
                    pointer: command.pointer,
                    water: command.water,
                    coffee: command.coffee,
                    cookies: command.cookies,
                    waterBottles: command.waterBottles,
                    mountingType: command.mountingType,
                    presidiumTable: command.presidiumTable,
                    flags: command.flags,
                    podium: command.podium,
                    tableForSpeaker: command.tableForSpeaker,
                    tablecloths: command.tablecloths,
                    tableTypes: command.tableTypes,
                    chairTypes: command.chairTypes,
                    tableclothColors: command.tableclothColors,
                    refreshment: command.refreshment,
                    breakfast: command.breakfast,
                    lunch: command.lunch,
                    dinner: command.dinner,
                    observation: command.observation
                )

                session?.events << event
            }


            redirect action: "events", params: [index: index]
        }
    }

    def cloneEvent(Integer index) {
        List<Event> events = session?.events
        Event event = events?.getAt(index)?.clone()

        events << event

        redirect action: "events", params: [index: events?.size() - 1]
    }

    def removeEvent(Integer index) {
        List<Event> events = session?.events

        if (events?.size() == 1) {
            events = []
            session?.activity = [:]

            redirect action: "index"
            return
        } else {
            events?.remove(index)

            if (index == events?.size()) {
                index--
            } else {
                index + 1
            }
        }

        redirect action: "events", params: [index: index]
    }

    def save() {
        User currentUser = springSecurityService.currentUser
        String location = coordinationService.getCoordinationByName(session?.coordination).location

        Activity activity = new Activity(
            name: session?.activity?.name,
            createdBy: currentUser,
            location: location,
            coordination: session.coordination,
            externalCustomer: session?.activity?.externalCustomer
        )

        session?.events?.each { event ->
            activity.addToEvents(event)
        }

        if (!activity.save()) {
            activity.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bean = activity
            redirect action: "events", id: 0
            return
        }

        flash.message = "Actividad creada"
        redirect action: "index"
    }

    def edit(Long id) {
        Activity activity = Activity.get(id)

        if (!activity) {
            response.sendError 404
        }

        [
            activity: activity,
            events: activity.events
        ]
    }

    def show(Long id) {
        Activity activity = Activity.get(id)

        if (!activity) {
            response.sendError 404
        }

        [
            activity: activity,
            events: activity.events,
            activityWidget: createActivityWidget(activity),
            eventWidget: createEventWidget(activity.events),

            // TODO this should go in activity widget?
            coordinations: employeeService.getEmployeeCoordinations(activity.createdBy.id),

            tableTypes: grailsApplication.config.ni.edu.uccleon.tableTypes,
            chairTypes: grailsApplication.config.ni.edu.uccleon.chairTypes,
            elements: [flags: "Banderas", podium: "Podium", tableForSpeaker: "Mesa para expositor", tablecloths: "Manteles"],
            means: [audiovisual: "Datashow", wifi: "Wi-Fi", sound: "Sonido", speaker: "Parlante", microfone: "Microfono", pointer: "Puntero"]
        ]
    }

    def updateActivity(Long id) {
        Activity activity = Activity.get(id)

        if (!activity) {
            response.sendError 404
        }

        activity.properties["name", "coordination", "externalCustomer"] = params

        if (!activity.save()) {
            activity.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bean = activity
        }

        flash.message =  activity.hasErrors() ? "A ocurrido un error" : "Actualizacion correcta"
        redirect action: "show", params: [id: id, tab: "activityInfo"]
    }

    def cloneActivityEvent(Long id, String tab, Long eventId) {
        Activity activity = Activity.get(id)
        Event event = Event.get(eventId)

        if (!activity || !event) {
            response.sendError 404
        }

        Event newEvent = new Event(
            date: event.date,
            startTime: event.startTime,
            endingTime: event.endingTime,
            numberOfPeople: event.numberOfPeople,
            location: event.location,
            audiovisual: event.audiovisual,
            wifi: event.wifi,
            sound: event.sound,
            speaker: event.speaker,
            microfone: event.microfone,
            pointer: event.pointer,
            water: event.water,
            coffee: event.coffee,
            cookies: event.cookies,
            waterBottles: event.waterBottles,
            mountingType: event.mountingType,
            presidiumTable: event.presidiumTable,
            flags: event.flags,
            podium: event.podium,
            tableForSpeaker: event.tableForSpeaker,
            tablecloths: event.tablecloths,
            refreshment: event.refreshment,
            breakfast: event.breakfast,
            lunch: event.lunch,
            dinner: event.dinner,
            observation: event.observation
        )

        event.tableTypes.each { t ->
            newEvent.addToTableTypes(new TableType(t.name))
        }

        event.chairTypes.each { c ->
            newEvent.addToChairTypes(new ChairType(c.name))
        }

        event.tableclothColors.each { tc ->
            newEvent.addToTableclothColors(new TableclothColor(tc.name))
        }

        activity.addToEvents(newEvent)

        if (!activity.save(flush: true)) {
            activity.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }
        }

        redirect action: "edit", params: [id: id, tab: tab, eventId: newEvent.id]
    }

    def removeActivityEvent(Long id, String tab, Long eventId) {
        Activity activity = Activity.get(id)
        Event event = Event.get(eventId)

        if (!activity || !event) {
            response.sendError 404
        }

        List<Event> events = activity.events
        Integer index = events.findIndexOf { it == event }
        Boolean last = index == (events.size() - 1)

        if (events.size() == 1) {
            activity.delete(flush: true)

            redirect action: "index"
            return
        } else {
            activity.removeFromEvents(event)

            if (last) {
                index--
            } else {
                index++
            }
        }

        redirect action: "edit", params: [id: id, tab: tab, eventId: events[index]?.id ?: 0]
    }

    def updateEvent(EventCommand command, Long id, String tab, Long eventId) {
        Activity activity = Activity.get(id)
        Event event = Event.get(eventId)

        if (!activity || !event) {
            response.sendError 404
        }

        event.date = command.date
        event.numberOfPeople = command.numberOfPeople
        event.startTime = command.startTime
        event.endingTime = command.endingTime
        event.location = command.location
        event.audiovisual = command.audiovisual
        event.wifi = command.wifi
        event.sound = command.sound
        event.speaker = command.speaker
        event.microfone = command.microfone
        event.pointer = command.pointer
        event.water = command.water
        event.coffee = command.coffee
        event.cookies = command.cookies
        event.waterBottles = command.waterBottles
        event.mountingType = command.mountingType
        event.presidiumTable = command.presidiumTable
        event.flags = command.flags
        event.podium = command.podium
        event.tableForSpeaker = command.tableForSpeaker
        event.tablecloths = command.tablecloths
        event.refreshment = command.refreshment
        event.breakfast = command.breakfast
        event.lunch = command.lunch
        event.dinner = command.dinner
        event.observation = command.observation

        event.tableTypes.clear()
        command.tableTypes.each { t ->
            event.addToTableTypes(t)
        }

        event.chairTypes.clear()
        command.chairTypes.each { c ->
            event.addToChairTypes(c)
        }

        event.tableclothColors.clear()
        command.tableclothColors.each { tc ->
            event.addToTableclothColors(tc)
        }

        if (!event.save()) {
            event.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }
        }

        flash.message = event.hasErrors() ? "A ocurrido un error" : "Actualizacion correcta"
        redirect action: "edit", params: [id: id, tab: tab, eventId: eventId]
    }

    def sendNotification(Long id) {
        Activity activity = Activity.get(id)
        User currentUser = springSecurityService.currentUser
        String managerEmail = ""

        if (!activity) {
            response.sendError 404
        }

        switch(activity.status) {
            case "pending":
                activity.status = "notified"
                activity.notifiedBy = currentUser
                activity.notificationDate = new Date()

                managerEmail = employeeService.getManagerMail(activity.location)
            break
            case { activity.status == "notified" && activity.location == "Academic" }:
                activity.status = "granted"
                activity.grantedBy = currentUser
                activity.dateGranted = new Date()

                managerEmail = "jorge.rojas@ucc.edu.ni"
            break
            case { activity.status == "notified" && activity.location == "Administrative" }:
                activity.status = "approved"
                activity.approvedBy = currentUser
                activity.dateApproved = new Date()

                managerEmail = "orlando.gaitan@ucc.edu.ni"
            break
            case "granted":
                activity.status = "approved"
                activity.approvedBy = currentUser
                activity.dateApproved = new Date()

                managerEmail = "orlando.gaitan@ucc.edu.ni"
            break
            case "approved":
                activity.status = "done"
            break
        }

        if (!activity.save()) {
            activity.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bean = activity
            flash.message = "A ocurrido un error"
            redirect action: "show", params: [id: id, tab: "notification"]
            return
        }

        // if (activity.status != "approved")) {
        //     String managerEmail = employeeService.getManagerMail(activity.location)

        //     sendMail {
        //         from currentUser.email
        //         to managerEmail
        //         subject "Protocolo - $activity.name"
        //         html g.render(template: "email", model: [
        //                 name: activity.name,
        //                 coordination: activity.coordination,
        //                 client: activity?.externalCustomer?.name ?: null,
        //                 host: "http://${grailsApplication.config.ni.edu.uccleon.serverUrl}/activity/show/${activity.id}"
        //             ]
        //         )
        //     }
        // }

        flash.message = activity.status != "done" ? "Actividad notificada" : "Actividad archivada"
        redirect action: "index"
    }

    def removeActivity(Long id) {
        Activity activity = Activity.get(id)

        if (!activity) {
            response.sendError 404
        }

        activity.delete()
        flash.message = "Actividad eliminada"

        redirect action: "index"
    }

    def printActivity(Long id) {
        Activity activity = Activity.get(id)

        if (!activity) {
            response.sendError 404
        }

        PdfDocumentBuilder pdfBuilder = new PdfDocumentBuilder(response.outputStream)
        def customTemplate = {
            "document" font: [family: "Courier", size: 9.pt], margin: [top: 0.5.inches]
            "cell.label" font: [bold: true]
            "cell.info" font: [size: 8.pt]
        }

        pdfBuilder.create {
            document(
                template: customTemplate,
                header: { info ->
                    table(border: [size: 0]) {
                        row {
                            cell "Universidad de Ciencias Comerciales", align: "center", style: "info"
                        }
                    }
                },
                footer: { info ->
                    table(border: [size: 0]) {
                        row {
                            cell "Impreso ${new Date().format('yyyy-MM-dd HH:mm:ss')}", align: "center", style: "info"
                        }
                    }
                }
            ) {
                activity.events.eachWithIndex { event, index ->
                    Map classroom = classroomService.getClassroom(event.location.toInteger())
                    String location = classroom.name ?: classroom.code

                    paragraph "$event.activity.name", align: "center"

                    paragraph(margin: [top: 0.inches, bottom: 0.inches]) {
                        text "Datos"
                    }

                    table(columns: [1, 2], padding: 1.px, border: [size: 0]) {
                        row {
                            cell "Fecha", style: "label"
                            cell event.date.format("yyyy-MM-dd")
                        }

                        row {
                            cell "Lugar", style: "label"
                            cell location
                        }

                        row {
                            cell "Asistentes", style: "label"
                            cell event.numberOfPeople
                        }

                        row {
                            cell "Inicio", style: "label"
                            cell HOURS.find { it.time == event.startTime }.value
                        }

                        row {
                            cell "Finaliza", style: "label"
                            cell HOURS.find { it.time == event.endingTime }.value
                        }
                    }

                    if (event.observation) {
                        paragraph(margin: [bottom: 0.inches]) {
                            text "Observación"
                        }

                        paragraph event.observation
                    }

                    paragraph(margin: [bottom: 0.inches]) {
                        text "Requerimientos"
                    }

                    table(padding: 0.px, border: [size: 0]) {
                        row {
                            cell {
                                table(padding: 1.px, border: [size: 0]) {
                                    row {
                                        cell "Datashow", style: "label"
                                        cell event.audiovisual ? "Si" : "-"
                                    }

                                    row {
                                        cell "WIFI", style: "label"
                                        cell event.wifi ? "Si" : "-"
                                    }

                                    row {
                                        cell "Audio", style: "label"
                                        cell event.sound ? "Si" : "-"
                                    }

                                    row {
                                        cell "Parlantes", style: "label"
                                        cell event.speaker ? "Si" : "-"
                                    }

                                    row {
                                        cell "Microfono", style: "label"
                                        cell event.microfone ? "Si" : "-"
                                    }

                                    row {
                                        cell "Puntero", style: "label"
                                        cell event.pointer ? "Si" : "-"
                                    }
                                }
                            }

                            cell {
                                table(padding: 1.px, border: [size: 0]) {
                                    row {
                                        cell "Agua", style: "label"
                                        cell event.water ? "Si" : "-"
                                    }

                                    row {
                                        cell "Café", style: "label"
                                        cell event.coffee ? "Si" : "-"
                                    }

                                    row {
                                        cell "Galletas", style: "label"
                                        cell event.cookies > 0 ? event.cookies : "-"
                                    }

                                    row {
                                        cell "Botella A", style: "label"
                                        cell event.waterBottles > 0 ? event.waterBottles : "-"
                                    }
                                }
                            }

                            cell {
                                table(padding: 1.px, border: [size: 0]) {
                                    row {
                                        cell "Montaje", style: "label"
                                        cell event.mountingType
                                    }

                                    row {
                                        cell "Banderas", style: "label"
                                        cell event.flags ? "Si" : "-"
                                    }

                                    row {
                                        cell "Podium", style: "label"
                                        cell event.podium ? "Si" : "-"
                                    }

                                    row {
                                        cell "Mesa E", style: "label"
                                        cell event.tableForSpeaker ? "Si" : "-"
                                    }

                                    row {
                                        cell "Manteles", style: "label"
                                        cell event.tablecloths ? "Si" : "-"
                                    }

                                    if (event.tableclothColors) {
                                        row {
                                            cell "Colores M", style: "label"
                                            cell {
                                                event.tableclothColors.each { tc ->
                                                    text tc.name
                                                    lineBreak()
                                                }
                                            }
                                        }
                                    }

                                    if (event.tableTypes) {
                                        row {
                                            cell "Tipo meza", style: "label"
                                            cell {
                                                event.tableTypes.each { t ->
                                                    text t.name
                                                    lineBreak()
                                                }
                                            }
                                        }
                                    }

                                    if (event.chairTypes) {
                                        row {
                                            cell "Tipo silla", style: "label"
                                            cell {
                                                event.chairTypes.each { c ->
                                                    text c.name
                                                    lineBreak()
                                                }
                                            }
                                        }
                                    }

                                    row {
                                        cell "Presidium", style: "label"
                                        cell event.presidiumTable > 0 ? event.presidiumTable : "-"
                                    }
                                }
                            }

                            cell {
                                table(padding: 1.px, border: [size: 0]) {
                                    row {
                                        cell "Refrescos", style: "label"
                                        cell event.refreshment > 0 ? event.refreshment : "-"
                                    }

                                    row {
                                        cell "Desayuno", style: "label"
                                        cell event.breakfast > 0 ? event.breakfast : "-"
                                    }

                                    row {
                                        cell "Almuerzo", style: "label"
                                        cell event.lunch > 0 ? event.lunch : "-"
                                    }

                                    row {
                                        cell "Cena", style: "label"
                                        cell event.dinner > 0 ? event.dinner : "-"
                                    }
                                }
                            }
                        }
                    }

                    paragraph ""
                    paragraph ""
                    paragraph ""

                    table(border: [size: 0], padding: 0.px) {
                        row {
                            cell event.activity.createdBy.username, align: "center"
                            if (event.activity.location == "Academic") {
                                cell event.activity.grantedBy.username, align: "center"
                            }
                            cell event.activity.approvedBy.username, align: "center"
                        }

                        row {
                            cell "Creado por", align: "center"
                            if (event.activity.location == "Academic") {
                                cell "Aprovado por", align: "center"
                            }
                            cell "Autorizado por", align: "center"
                        }
                    }

                    if (index < activity.events.size() - 1) {
                        pageBreak()
                    }
                }
            }
        }

        response.contentType = "application/pdf"
        response.setHeader("Content-disposition", "attachment;filename=test.pdf")
        //response.outputStream << out.toByteArray()
        response.outputStream.flush()
    }

    private ActivityWidget createActivityWidget(Activity activity) {
        new ActivityWidget(
            name: activity.name,
            createdBy: activity.createdBy,
            dateCreated: activity.dateCreated,
            status: activity.status,
            coordination: activity.coordination,
            notifiedBy: activity.notifiedBy,
            notificationDate: activity.notificationDate,
            daysAllowedToNotify: (eventService.getEventMinDate(activity) - 2) - new Date()
        )
    }

    private EventWidget createEventWidget(List<Event> events) {
        new EventWidget(
            events: events,
            tableTypes: grailsApplication.config.ni.edu.uccleon.tableTypes,
            chairTypes: grailsApplication.config.ni.edu.uccleon.chairTypes,
            elements: [flags: "Banderas", podium: "Podium", tableForSpeaker: "Mesa para expositor", tablecloths: "Manteles"],
            means: [audiovisual: "Datashow", wifi: "Wi-Fi", sound: "Sonido", speaker: "Parlante", microfone: "Microfono", pointer: "Puntero"]
        )
    }

    private static final HOURS = [
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
}

class EventWidget {
    List<Event> events
    List<String> tableTypes
    List<String> chairTypes
    Map<String, String> elements
    Map<String, String> means
}

class ActivityWidget {
    String name
    User createdBy
    Date dateCreated
    String status
    String coordination
    Boolean notified
    User notifiedBy
    Date notificationDate
    Integer daysAllowedToNotify
}

class ActivityCommand {
    String name
    ExternalCustomer externalCustomer
    String coordination

    static constraints = {
        importFrom Activity
    }
}

class EventCommand {
    Date date
    Integer startTime
    Integer endingTime
    Integer numberOfPeople
    String location
    Boolean audiovisual
    Boolean wifi
    Boolean sound
    Boolean speaker
    Boolean microfone
    Boolean pointer
    Boolean water
    Boolean coffee
    Integer cookies
    Integer waterBottles
    String mountingType
    Integer presidiumTable
    Boolean flags
    Boolean podium
    Boolean tableForSpeaker
    Boolean tablecloths
    Integer refreshment
    Integer breakfast
    Integer lunch
    Integer dinner
    String observation
    List<TableType> tableTypes = []
    List<ChairType> chairTypes = []
    List<TableclothColor> tableclothColors = []

    static constraints = {
        importFrom Event
    }
}
