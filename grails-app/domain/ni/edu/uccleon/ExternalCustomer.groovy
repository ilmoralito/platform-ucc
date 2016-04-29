package ni.edu.uccleon

import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString
class ExternalCustomer {
    @BindUsing({
        obj, source -> source["name"]?.capitalize()
    })
    String name
    String email
    String telephoneNumber
    Contact contact

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
        email email: true, unique: true, blank: false
        telephoneNumber blank: false, unique: true, size: 8..8
    }
}
