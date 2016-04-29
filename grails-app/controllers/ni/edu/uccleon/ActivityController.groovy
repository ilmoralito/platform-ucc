package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class ActivityController {
    def springSecurityService
    def coordinationService
    def helperService

    static allowedMethods = [
        index: "GET",
        create: ["GET", "POST"]
    ]

    def index(String calendarType) {
        Closure activities = {
            User user = springSecurityService.currentUser
            String userLocation = coordinationService.getCoordinations(user.id.toInteger()).location

            // Si es jorge listar todas las actividades pendientes de aprovar y las autorizadas por director
            // de academia
            // Si es rosa listar todas las actividades pendientes de autorizar que sean creadas por
            // un usuario del area de academia
            // Si es un usuario con rol user listar sus propias solicitudes

            

            Activity.list()
        }

        List<Date> interval = helperService.getInterval(calendarType)

        [activities: activities(), dayName: helperService.getDayName(), interval: interval]
    }

    def create() {
    }

    def addToEvent() {
        Event event = new Event(
            dateOfTheEvent: params.date("dateOfTheEvent", "yyyy-MM-dd"),
            startTime: params?.startTime,
            endingTime: params?.endingTime,
            numberOfPeople: params?.numberOfPeople,
            location: params?.location,
            audiovisual: params?.audiovisual ?: false,
            wifi: params?.wifi ?: false,
            sound: params?.sound ?: false,
            speaker: params?.speaker ?: false,
            microfone: params?.microfone ?: false,
            water: params?.water ?: false,
            coffee: params?.coffee,
            tea: params?.tes,
            cakeShop: params?.cakeShop,
            cookies: params?.cookies,
            waterBottles: params?.waterBottles,
            mountingType: params?.mountingType,
            flags: params?.flags ?: false,
            podium: params?.podium ?: false,
            tableForSpeaker: params?.tableForSpeaker ?: false,
            tablecloths: params?.tablecloths ?: false,
            refreshment: params?.refreshment,
            breakfast: params?.breakfast,
            lunch: params?.lunch,
            dinner: params?.dinner,
            observation: params?.observation
        )

        session.events << event
    }

    def save() {
        Activity activity = new Activity(
            name: params?.name,
            createdBy: springSecurityService.currentUser
        )

        

        activity.addToEvents(event)

        if (!activity.save()) {
            activity.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }
        }

        redirect action: "create"
    }
}
