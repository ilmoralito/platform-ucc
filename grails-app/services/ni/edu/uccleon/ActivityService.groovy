package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class ActivityService {
    def springSecurityService
    def employeeService

    def getActivities() {
        User currentUser = springSecurityService.currentUser

        if (currentUser.authorities.authority.contains("ROLE_USER")) {
            Activity.where {
                coordination == employeeService.getEmployeeCoordination(currentUser.id) &&
                status != "done"
            }.list()
        }
    }
}
