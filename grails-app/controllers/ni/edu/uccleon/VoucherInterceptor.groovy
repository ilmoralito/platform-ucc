package ni.edu.uccleon

class VoucherInterceptor {
    def springSecurityService

    int order = HIGHEST_PRECEDENCE + 300

    public VoucherInterceptor() {
        match controller: "voucher", action: ~/(update|delete|edit)/
    }

    boolean before() {
        List<String> currentUserAuthorities = springSecurityService.loadCurrentUser().authorities.authority
        Voucher voucher = Voucher.get(params.id)

        if (!voucher) {
            return false
        }

        if ("ROLE_ADMINISTRATIVE_SUPERVISOR" in currentUserAuthorities && voucher.status != "notified") {
            render view: "/notAllowed"
            return false
        }

        if ("ROLE_PROTOCOL_SUPERVISOR" in currentUserAuthorities && voucher.status != "pending") {
            render view: "/notAllowed"
            return false
        }

        true
    }
}
