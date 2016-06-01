package ni.edu.uccleon

class ProtocolInterceptor {
    def springSecurityService

    public ProtocolInterceptor() {
        match controller: "activity", action: ~/(printEvent|printActivity|setActivityToDone)/
    }

    int order = HIGHEST_PRECEDENCE + 300

    boolean before() {
        User currentUser = springSecurityService.currentUser
        Set<Role> currentUserRoles = currentUser.authorities

        if (!("ROLE_PROTOCOL_SUPERVISOR" in currentUserRoles.authority)) {
            log.info "Denied access to ${currentUser}"
            render "No permitido"

            return false
        }

        true
    }
}
