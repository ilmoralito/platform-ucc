package ni.edu.uccleon

import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString
class ExternalCustomer {
    @BindUsing({
        obj, source -> source['name']?.capitalize()
    })
    String name
    String city
    String email
    String telephoneNumber

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
        city inList: ['Leon', 'Chinandega'], maxSize: 255
        email email: true, unique: true, blank: false
        telephoneNumber blank: false, unique: true, size: 8..8
        contact unique: true
    }

    static hasOne = [contact: Contact]
}
