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
                status inList: ['pending', 'notified']
            }
        }

        "/vouchers/approved/$approvalDate" {
            controller = 'voucher'
            action = 'approved'
            constraints {
                approvalDate blank: false // TODO: validate approvalDate is a valid date
            }
        }

        // "vouchers/create/$date/$activity/$type" {
        //     controller = 'voucher'
        //     action = 'create'
        //     constraints {
        //         date blank: false // TODO: validate date is a valid date
        //         activity blank: false
        //         type inList: ['user', 'guest']
        //     }
        // }

        '500' view: '/error'
        '404' view: '/notFound'
    }
}
