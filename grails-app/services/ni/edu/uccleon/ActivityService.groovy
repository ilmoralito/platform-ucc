package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class ActivityService {
    def springSecurityService
    def employeeService

    def getActivities() {
        User currentUser = springSecurityService.currentUser

        Activity.where {
            coordination == employeeService.getEmployeeCoordination(currentUser.id) &&
            status != "done"
        }.list()
    }
}
