package ni.edu.uccleon

class ActivityInterceptor {
    def springSecurityService
    def employeeService

    int order = HIGHEST_PRECEDENCE + 100

    public ActivityInterceptor() {
        match controller: "activity", action: ~/(updateActivity|cloneActivityEvent|removeActivityEvent|updateEvent|sendNotification|removeActivity)/
    }

    boolean before() {
        Activity activity = Activity.get(params.int("id"))
        User currentUser = springSecurityService.currentUser
        List<String> currentUserAuthorities = currentUser.authorities.authority


        if (activity.status == "pending") {
            List employeeCoordinations = employeeService.getEmployeeCoordinations(currentUser.id)

            if (!(activity.coordination in employeeCoordinations.name)) {
                log.debug "Sin acceso porque ${currentUser} no pertenece a la coordinacion $activity.coordination"
                render "No permitido"

                return false
            }
        }

        if (activity.status == "notified" && activity.location == "Academic" && !("ROLE_ACADEMIC_SUPERVISOR" in currentUserAuthorities)) {
            log.debug "Sin acceso porque ${currentUser} no pertenece a ROLE_ACADEMIC_SUPERVISOR"
            render "No permitido"

            return false
        }

        if (activity.status == "notified" && activity.location == "Administrative" && !("ROLE_ADMINISTRATIVE_SUPERVISOR" in currentUserAuthorities)) {
            log.debug "Sin acceso porque ${currentUser} no pertenece a ROLE_ADMINISTRATIVE_SUPERVISOR"
            render "No permitido"

            return false
        }

        if (activity.status == "granted" && !("ROLE_ADMINISTRATIVE_SUPERVISOR" in currentUserAuthorities)) {
            log.debug "Sin acceso porque ${currentUser} no pertenece a ROLE_ADMINISTRATIVE_SUPERVISOR"
            render "No permitido"

            return false
        }

        if (activity.status == "approved" || activity.status == "done") {
            log.debug "Sin acceso para ${currentUser}"
            render "No permitido"

            return false
        }

        true
    }
}
