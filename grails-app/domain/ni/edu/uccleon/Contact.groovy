package ni.edu.uccleon

import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString
class Contact {
    @BindUsing({ obj, source ->
        source['fullName']?.toLowerCase()?.tokenize(' ')*.capitalize().join(' ')
    })
    String fullName
    String identityCard
    String address
    String email
    String telephoneNumber
    ExternalCustomer externalCustomer

    static constraints = {
        fullName blank: false
        identityCard blank: false
        address blank: false
        email email: true, unique: true, blank: false
        telephoneNumber blank: false, unique: true, size: 8..8
    }

    static belongsTo = ExternalCustomer
}
