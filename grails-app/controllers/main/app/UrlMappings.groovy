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

        

        '500' view: '/error'
        '404' view: '/notFound'
        '403' view: '/forbidden'
    }
}
