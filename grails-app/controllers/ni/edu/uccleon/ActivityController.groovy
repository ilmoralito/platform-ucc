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
        sendNotification: "GET"
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

                flash.message = "Datos Incorrectos"
                return [bean: command]
            }

            Integer index = params.int("index")

            if (index >= 0 && index <= session?.events?.size() - 1) {
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

            redirect action: "events", params: [index: index ?: session?.events?.size() - 1]
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
     * Show activity instance and its events by activity id
     * @param id Activity instance id
     * @return
     */
    def show(Long id) {
        Activity activity = Activity.get(id)
        User currentUser = springSecurityService.currentUser
        String coordination = employeeService.getEmployeeCoordination(currentUser.id)
        Date eventsMinDate = eventService.getEventMinDate(activity)

        if (!activity) {
            response.sendError 404
        }

        [
            activity: activity,
            daysAllowedToNotify: (eventsMinDate - 2) - new Date(),
            activityWidget: createActivityWidget(activity)
        ]
    }

    /**
     * Update activity properties name and externalCustomer
     * @param id Activity instance id
     * @param tab current tab in action nav
     * @return
     */
    def updateActivity(Long id, String tab) {
        Activity activity = Activity.get(id)

        if (!activity) {
            response.sendError 404
        }

        activity.properties["name", "externalCustomer"] = params

        if (!activity.save()) {
            activity.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error"
            flash.bean = activity
            redirect action: "show", params: [id: id, tab: tab]
            return
        }

        flash.message = "Actualizacion correcta"
        redirect action: "show", params: [id: id, tab: tab]
    }

    def sendNotification(Long id) {
        Activity activity = Activity.get(id)
        User currentUser = springSecurityService.currentUser
        Map employee = employeeService.getEmployee(currentUser.id)
        Map coordination = employee.coordination
        String receiverEmail = employeeService.getEmployeeInstitutionalMail(coordination)

        if (!activity) {
            response.sendError 404
        }

        activity.notified = true
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
