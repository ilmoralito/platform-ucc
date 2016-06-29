package ni.edu.uccleon

class VoucherInterceptor {
    def springSecurityService

    int order = HIGHEST_PRECEDENCE + 300

    public VoucherInterceptor() {
        match controller: "voucher", action: ~/(update|delete)/
    }

    boolean before() {
        User currentUser = springSecurityService.currentUser
        List<String> currentUserAuthorities = currentUser.authorities.authority
        Voucher voucher = Voucher.get(params.id)

        if (!voucher) {
            return false
        }

        if ("ROLE_PROTOCOL_SUPERVISOR" in currentUserAuthorities && voucher.status != "pending") {
            flash.message = "Accion denegada. Vale esta $voucher.status"
            redirect action: "show", params: [date: voucher.date]
            return false
        }

        if ("ROLE_ADMINISTRATIVE_SUPERVISOR" in currentUserAuthorities && voucher.status != "notified") {
            flash.message = "Accion denegada. Vale esta $voucher.status"
            redirect action: "show", params: [date: voucher.date]
            return false
        }

        true
    }
}
