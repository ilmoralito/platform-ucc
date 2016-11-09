package ni.edu.uccleon

class Guest {
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
