package ni.edu.uccleon

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='fullName')
@ToString(includes='fullName', includeNames=true, includePackage=false)
class Guest implements Serializable {
    String fullName
    Boolean enabled = true

    Date dateCreated
    Date lastUpdated

    static constraints = {
        fullName blank: false
    }

    static mapping = {
        sort 'fullName'
    }
}
