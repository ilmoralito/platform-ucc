package main.app

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        '/' controller: 'birthday', action: 'index'
        '/phonebook' controller: 'phoneBook', action: 'index'
        '/news' controller: 'news', action: 'index'

        // Vouchers
        "/vouchers/$status" {
            controller = 'voucher'
            action = 'index'
            constraints {
                status inList: ['pending', 'notified', 'canceled']
            }
        }

        "/vouchers/approved/$approvalDate" {
            controller = 'voucher'
            action = 'approved'
            constraints {
                approvalDate blank: false // TODO: validate approvalDate is a valid date
            }
        }

        "/vouchers/vouchersApprovedInTheYear/$year" {
            controller = 'voucher'
            action = 'getApprovedVouchersInTheYear'
            constraints {
                year shared: yearConstraint
            }
        }

        "/vouchers/vouchersByYear/$year" {
            controller = 'voucher'
            action = 'vouchersByYear'
            constraints {
                year shared: yearConstraint
            }
        }

        '500' view: '/error'
        '404' view: '/notFound'
        '403' view: '/forbidden'
    }
}
