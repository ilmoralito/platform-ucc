package ni.edu.uccleon

class VocuherInterceptor {
    def springSecurityService

    VocuherInterceptor() {
        match(controller: 'voucher', action: ~/(edit|update|delete)/)
    }

    boolean before() {
        List<String> currentUserAuthorities = springSecurityService.currentUser.authorities.authority
        Voucher voucher = Voucher.get(params.int('id'))

        if (!voucher) {
            response.sendError 404
        }

        /*
        Constraints
        +--------------+----------------------+----------------------+----------+
        | Users        |       Pending        |       Notified       | Approved |
        +--------------+----------------------+----------------------+----------+
        | Adminitrator | x                    | edit, update, delete | x        |
        | Protocol     | edit, update, delete | x                    | x        |
        +--------------+----------------------+----------------------+----------+
        */

        if ((voucher.status == 'pending' || voucher.status == 'approved') && currentUserAuthorities.contains('ROLE_ADMINISTRATIVE_SUPERVISOR')) {
            response.sendError 403
            return false
        }

        if ((voucher.status == 'notified' || voucher.status == 'approved') && currentUserAuthorities.contains('ROLE_PROTOCOL_SUPERVISOR')) {
            response.sendError 403
            return false
        }

        true
    }
}
