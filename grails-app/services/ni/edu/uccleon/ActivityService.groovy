package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class ActivityService {
    def springSecurityService
    def employeeService

    List<Activity> getActivities() {
        Long currentUserId = springSecurityService.loadCurrentUser().id
        List coordinations = employeeService.getEmployee(currentUserId).coordinations

        Activity.where {
            coordination in coordinations.name && status != "done"
        }.list()
    }

    List<Activity> getActivitiesBySupervisor(String supervisorRole) {
        List<Activity> activities = []

        switch(supervisorRole) {
            case "ROLE_ADMINISTRATIVE_SUPERVISOR":
                activities = Activity.where {
                    (status == "notified" && location == "Administrative") || status == "granted"
                }.list()
            break
            case "ROLE_ACADEMIC_SUPERVISOR":
                activities = Activity.where {
                    status == "notified" && location == "Academic"
                }.list()
            break
            case "ROLE_PROTOCOL_SUPERVISOR":
                activities = Activity.where {
                    status == "approved"
                }.list()
            break
        }

        activities
    }
}
