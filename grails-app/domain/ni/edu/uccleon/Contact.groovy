package ni.edu.uccleon

import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString
class Contact {
    @BindUsing({ obj, source ->
        source["fullName"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String fullName
    String identityCard
    String address
    String email
    String telephoneNumber

    static constraints = {
        email email: true, unique: true, blank: false
        telephoneNumber blank: false, unique: true, size: 8..8
    }
}
