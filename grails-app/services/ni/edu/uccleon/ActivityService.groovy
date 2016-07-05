package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class ActivityService {
    def springSecurityService
    def employeeService

    def getActivities() {
        User currentUser = springSecurityService.currentUser
        List coordinations = employeeService.getEmployeeCoordinations(currentUser.id)

        Activity.where {
            coordination in coordinations.name && status != "done"
        }.list()
    }
}
