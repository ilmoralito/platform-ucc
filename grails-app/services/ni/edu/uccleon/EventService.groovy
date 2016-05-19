package ni.edu.uccleon

import grails.transaction.Transactional
import static java.util.Calendar.*

@Transactional
class EventService {
    def springSecurityService
    def activityService

    def getEvents() {
        User currentUser = springSecurityService.currentUser

        if (currentUser.authorities.authority.contains("ROLE_USER")) {
            Event.where {
                activity.createdBy == currentUser
            }.list()
        } else if (currentUser.authorities.authority.contains("ROLE_ADMINISTRATIVE_SUPERVISOR")) {
            Activity.where {
                grantedBy != null && approvedBy == null
            }.list()
        } else if (currentUser.authorities.authority.contains("ROLE_ACADEMIC_SUPERVISOR")) {
            Activity.where {
                grantedBy == null && approvedBy == null
            }.list()
        }
    }

    List presentation(List<Event> events, String calendarType = "schedule") {
        List result = []

        switch(calendarType) {
            case "schedule":
                result = events.groupBy { it.date }.collect { a ->
                    [
                        date: a.key,
                        activities: a.value.collect { b ->
                            [
                                id: b.activity.id,
                                name: b.activity,
                                externalCustomer: b.activity.externalCustomer,
                                status: activityService.getStatus(b.activity)
                            ]
                        }
                    ]
                }.sort { a, b -> b.date <=> a.date }
            break
            case "year":
                
            break
            case "month":
                
            break
            case "week":
                
            break
            case "day":
                
            break
        }

        result
    }
}
