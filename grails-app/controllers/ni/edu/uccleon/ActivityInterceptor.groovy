package ni.edu.uccleon

class ActivityInterceptor {
    def springSecurityService
    def employeeService

    int order = HIGHEST_PRECEDENCE + 100

    public ActivityInterceptor() {
        match controller: "activity", action: ~/(updateActivity|cloneActivityEvent|removeActivityEvent|updateEvent|sendNotification|removeActivity)/
    }

    boolean before() {
        User currentUser = springSecurityService.currentUser
        List<String> currentUserAuthorities = currentUser.authorities.authority
        Activity activity = Activity.get(params.int("id"))
        String activityLocation = employeeService.getEmployeeLocation(activity.createdBy.id)

        if (activity.status == "pending") {
            String currentEmployeeCoordination = employeeService.getEmployeeCoordination(currentUser.id)

            if (currentEmployeeCoordination != activity.coordination) {
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
