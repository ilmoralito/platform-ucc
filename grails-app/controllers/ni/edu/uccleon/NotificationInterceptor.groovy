package ni.edu.uccleon

class NotificationInterceptor {
    def springSecurityService
    def voucherService

    int order = HIGHEST_PRECEDENCE + 200

    public NotificationInterceptor() {
        matchAll()
            .excludes(controller: "login")
            .excludes(controller: "logout")
            .excludes(controller: "birthday")
            .excludes(controller: "phoneBook")
    }

    boolean before() {
        // User currentUser = springSecurityService.currentUser
        // Set<Role> authorities = currentUser.authorities

        // if (authorities.authority.contains("ROLE_ADMINISTRATIVE_SUPERVISOR")) {
        //     List<Activity> activityList = Activity.where {
        //         (status == "notified" && location == "Administrative") || status == "granted"
        //     }.list()

        //     grailsApplication.config.ni.edu.uccleon.activityList = activityList

        //     List<Voucher> vouchers = Voucher.where {
        //         status == "notified"
        //     }.list()

        //     grailsApplication.config.ni.edu.uccleon.voucherList = voucherService.groupVouchersByDate(vouchers)
        // }

        // if (authorities.authority.contains("ROLE_ACADEMIC_SUPERVISOR")) {
        //     List<Activity> activityList = Activity.where {
        //         status == "notified" && location == "Academic"
        //     }.list()

        //     grailsApplication.config.ni.edu.uccleon.activityList = activityList
        // }

        // if (authorities.authority.contains("ROLE_PROTOCOL_SUPERVISOR")) {
        //     List<Activity> activityList = Activity.where {
        //         status == "approved"
        //     }.list()

        //     grailsApplication.config.ni.edu.uccleon.activityList = activityList

        //     List<Voucher> vouchers = Voucher.where {
        //         status == "approved"
        //     }.list()

        //     grailsApplication.config.ni.edu.uccleon.voucherList = voucherService.groupVouchersByDate(vouchers)
        // }

        true
    }
}
