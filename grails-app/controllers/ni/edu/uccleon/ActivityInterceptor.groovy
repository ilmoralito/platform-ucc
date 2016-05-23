package ni.edu.uccleon

class ActivityInterceptor {
    def springSecurityService
    def employeeService

    ActivityInterceptor() {
        match(action: "updateActivity")
    }

    boolean before() {
        User currentUser = springSecurityService.currentUser
        Activity activity = Activity.get(params.int("id"))
        Map employee = employeeService.getEmployee(currentUser.id)
        String employeeCoordination = employee.coordination.name
        String employeeLocation = employee.coordination.location
        String activityStatus = activity.status
        String activityCoordination = activity.coordination

        /*
        if (employeeCoordination != activityCoordination && activityStatus != "pending") {
            return false
        }
        */

        true
    }
}
