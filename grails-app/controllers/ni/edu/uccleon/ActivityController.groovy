package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_SUPERVISOR"])
class ActivityController {
    def springSecurityService
    def eventService
    def coordinationService
    def helperService

    static allowedMethods = [
        index: "GET",
        init: "GET",
        activityName: ["GET", "POST"],
        events: ["GET", "POST"],
        cloneEvent: "GET",
        removeEvent: "GET",
        save: "POST"
    ]

    def index(String calendarType) {
        List<Event> events = eventService.getEvents()
        List data = eventService.presentation(events)

        [data: data, calendarType: calendarType]
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

            redirect action: "events", params: [index: params?.index]
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

            Integer position = params.int("index")

            if (position >= 0 && position <= session?.events?.size() - 1) {
                Event event = session?.events?.getAt(position)

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

            redirect action: "events", params: [index: position ?: session?.events?.size() - 1]
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
        Activity activity = new Activity(
            name: session?.activity.name,
            createdBy: springSecurityService.currentUser
        )

        session?.events?.each { event ->
            activity.addToEvents(event)
        }

        flash.message = "Actividad guardada. Pendiente de ser aprobada"
        activity.save()

        redirect action: "index"
    }
}

class ActivityCommand {
    String name

    static constraints = {
        name blank: false
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
