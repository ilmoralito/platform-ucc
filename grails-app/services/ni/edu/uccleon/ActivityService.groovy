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
}
