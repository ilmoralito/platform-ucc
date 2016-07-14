package ni.edu.uccleon

class ActivityInterceptor {
    def springSecurityService
    def employeeService

    int order = HIGHEST_PRECEDENCE + 100

    public ActivityInterceptor() {
        match controller: "activity", action: ~/(edit|updateActivity|cloneActivityEvent|removeActivityEvent|updateEvent|sendNotification|removeActivity)/
    }

    boolean before() {
        Activity activity = Activity.get(params.int("id"))
        User currentUser = springSecurityService.currentUser
        List<String> currentUserAuthorities = currentUser.authorities.authority

        if (activity.status == "pending") {
            List employeeCoordinations = employeeService.getEmployeeCoordinations(currentUser.id)

            if (!(activity.coordination in employeeCoordinations.name)) {
                log.debug "Sin acceso para ${currentUser.username} no pertenece a la coordinacion $activity.coordination"
                render view: "/notAllowed"

                return false
            }
        }

        if (activity.status == "notified" && activity.location == "Academic" && !("ROLE_ACADEMIC_SUPERVISOR" in currentUserAuthorities)) {
            log.debug "Sin acceso para ${currentUser.username}"
            render view: "/notAllowed"

            return false
        }

        if (activity.status == "notified" && activity.location == "Administrative" && !("ROLE_ADMINISTRATIVE_SUPERVISOR" in currentUserAuthorities)) {
            log.debug "Sin acceso para ${currentUser.username}"
            render view: "/notAllowed"

            return false
        }

        if (activity.status == "granted" && !("ROLE_ADMINISTRATIVE_SUPERVISOR" in currentUserAuthorities)) {
            log.debug "Sin acceso para ${currentUser.username}"
            render view: "/notAllowed"

            return false
        }

        if (actionName != "sendNotification" && activity.status == "approved" && !("ROLE_PROTOCOL_SUPERVISOR" in currentUserAuthorities)) {
            log.debug "Sin acceso para ${currentUser.username}"
            render view: "/notAllowed"

            return false
        }

        if (activity.status == "done") {
            log.debug "Sin acceso para ${currentUser.username}"
            render view: "/notAllowed"

            return false
        }

        true
    }
}
