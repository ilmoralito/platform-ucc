package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ADMINISTRATIVE_SUPERVISOR", "ROLE_ACADEMIC_SUPERVISOR"])
class ActivityController {
    def springSecurityService
    def activityService
    def employeeService
    def eventService

    static allowedMethods = [
        index: "GET",
        init: "GET",
        activityName: ["GET", "POST"],
        events: ["GET", "POST"],
        cloneEvent: "GET",
        removeEvent: "GET",
        save: "POST",
        show: "GET",
        updateActivity: "POST",
        cloneActivityEvent: "GET",
        removeActivityEvent: "GET",
        sendNotification: "POST",
        removeActivity: "POST"
    ]

    def index(String calendarType) {
        List<Activity> activities = activityService.getActivities()

        [activities: activities, calendarType: calendarType]
    }

    def init() {
        session.activity = [:]
        session.events = []

        redirect action: "activityName"
    }

    def activityName(ActivityCommand command) {
        if (request.post) {
            if (command.hasErrors()) {
                command.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.message = "Dato necesario"
                return [bean: command]
            }

            session.activity.name = command.name
            session.activity.externalCustomer = command.externalCustomer

            redirect action: "events", params: [index: params.index]
        }
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
        String coordination = employeeService.getEmployeeCoordination(currentUser.id)

        Activity activity = new Activity(
            name: session?.activity?.name,
            createdBy: currentUser,
            coordination: coordination,
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

    /**
     * @param id Activity instance id
     * @return
     */
    def show(Long id) {
        User currentUser = springSecurityService.currentUser
        Activity activity = Activity.get(id)
        String coordination = employeeService.getEmployeeCoordination(currentUser.id)
        Date eventsMinDate = eventService.getEventMinDate(activity)

        if (!activity) {
            response.sendError 404
        }

        [
            activity: activity,
            daysAllowedToNotify: (eventsMinDate - 2) - new Date(),
            activityWidget: createActivityWidget(activity),
            status: activity.status,
            currentUserRoles: currentUser.authorities.authority
        ]
    }

    /**
     * @param id Activity instance id
     * @param tab current tab in action nav
     * @return
     */
    def updateActivity(Long id, String tab, Long eventId) {
        Activity activity = Activity.get(id)

        if (!activity) {
            response.sendError 404
        }

        activity.properties["name", "externalCustomer"] = params

        if (!activity.save()) {
            activity.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bean = activity
        }

        flash.message =  activity.hasErrors() ? "A ocurrido un error" : "Actualizacion correcta"
        redirect action: "show", params: [id: id, tab: tab, eventId: eventId]
    }

    /**
     * @param id Activity id
     * @param tab current selected tab
     * @param eventId selected event id
     * @return
     */
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

        redirect action: "show", params: [id: id, tab: tab, eventId: newEvent.id]
    }

    /**
     * @param id Activity id
     * @param tab current selected tab
     * @param eventId selected event id
     * @return
     */
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

        redirect action: "show", params: [id: id, tab: tab, eventId: events[index].id]
    }

    /**
     * @param command event command object
     * @param id Activity id
     * @param tab current selected tab
     * @param eventId selected event id
     * @return
     */
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

        redirect action: "show", params: [id: id, tab: tab, eventId: eventId]
    }

    /**
     * @param id Activity id
     * @return
     */
    def sendNotification(Long id) {
        Activity activity = Activity.get(id)
        User currentUser = springSecurityService.currentUser
        Map employee = employeeService.getEmployee(currentUser.id)
        Map coordination = employee.coordination

        if (!activity) {
            response.sendError 404
        }

        activity.notified = true
        activity.status = "notified"
        activity.notifiedBy = currentUser
        activity.notificationDate = new Date()

        if (!activity.save()) {
            activity.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bean = activity
            redirect action: "show", id: id

            return
        }

        String receiverEmail = employeeService.getEmployeeInstitutionalMail(coordination)

        sendMail {
            from currentUser.email
            to receiverEmail
            subject "Protocolo - Nueva actividad $activity.name"
            html g.render(template: "email", model: [
                    name: activity.name,
                    username: currentUser.username,
                    client: activity?.externalCustomer?.name ?: coordination.name,
                    host: "http://${grailsApplication.config.ni.edu.uccleon.serverUrl}/activity/show/${activity.id}"
                ]
            )
        }

        flash.message = "Actividad notificada"
        redirect action: "index"
    }

    def removeActivity(Long id) {
        Activity activity = Activity.get(id)

        if (!activity) {
            response.sendError 404
        }

        activity.delete(flush: true)

        flash.message = "Actividad eliminada"
        redirect action: "index"
    }

    private ActivityWidget createActivityWidget(Activity activity) {
        return new ActivityWidget(
            createdBy: activity.createdBy,
            dateCreated: activity.dateCreated,
            status: activity.status,
            coordination: activity.coordination,
            notified: activity.notified,
            notifiedBy: activity.notifiedBy,
            notificationDate: activity.notificationDate,
            daysAllowedToNotify: (eventService.getEventMinDate(activity) - 2) - new Date()
        )
    }
}

class ActivityWidget {
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
