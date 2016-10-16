package ni.edu.uccleon

import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString(includeNames= true)
class Guest {
    Guest(String fullName) {
        this.fullName = fullName
    }

    @BindUsing({ obj, source ->
        source['fullName']?.toLowerCase()?.tokenize(' ')*.capitalize().join(' ')
    })
    String fullName
    Boolean enabled = true

    static constraints = {
        fullName blank: false
    }

    static mapping = {
        sort 'fullName'
    }
}
