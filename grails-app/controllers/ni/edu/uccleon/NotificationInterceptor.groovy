package ni.edu.uccleon

class NotificationInterceptor {
    def springSecurityService
    def activityService
    def voucherService

    int order = HIGHEST_PRECEDENCE + 200

    public NotificationInterceptor() {
        matchAll()
            .excludes(controller: "login")
            .excludes(controller: "logout")
            .excludes(controller: "birthday")
            .excludes(controller: "phoneBook")
            .excludes(controller: "news")
    }

    boolean before() {
        // List<String> currentUserAuthorities = springSecurityService.loadCurrentUser().authorities.authority

        // if ("ROLE_ADMINISTRATIVE_SUPERVISOR" in currentUserAuthorities) {
        //     grailsApplication.config.ni.edu.uccleon.activityList = activityService.getActivitiesBySupervisor("ROLE_ADMINISTRATIVE_SUPERVISOR")

        //     List<Voucher> vouchers = voucherService.getVouchersByStatus("notified")
        //     grailsApplication.config.ni.edu.uccleon.voucherList = voucherService.groupVouchersByDate(vouchers)
        // }

        // if ("ROLE_ACADEMIC_SUPERVISOR" in currentUserAuthorities) {
        //     grailsApplication.config.ni.edu.uccleon.activityList = activityService.getActivitiesBySupervisor("ROLE_ACADEMIC_SUPERVISOR")
        // }

        // if ("ROLE_PROTOCOL_SUPERVISOR" in currentUserAuthorities) {
        //     grailsApplication.config.ni.edu.uccleon.activityList = activityService.getActivitiesBySupervisor("ROLE_PROTOCOL_SUPERVISOR")

        //     List<Voucher> vouchers = voucherService.getVouchersByStatus("approved")
        //     grailsApplication.config.ni.edu.uccleon.voucherList = voucherService.groupVouchersByDate(vouchers)
        // }

        true
    }
}
