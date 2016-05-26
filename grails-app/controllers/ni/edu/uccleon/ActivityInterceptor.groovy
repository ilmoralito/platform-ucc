package ni.edu.uccleon

class ActivityInterceptor {
    def springSecurityService
    def employeeService

    ActivityInterceptor() {
        match controller: "activity", action: ~/(updateActivity|cloneActivityEvent|removeActivityEvent|updateEvent|sendNotification|removeActivity)/
    }

    boolean before() {
        User currentUser = springSecurityService.currentUser
        Activity activity = Activity.get(params.int("id"))
        List<String> authorities = currentUser.authorities.authority

        true
    }
}
