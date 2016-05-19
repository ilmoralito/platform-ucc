package ni.edu.uccleon

class Activity {
    String name
    User createdBy
    User approvedBy
    User grantedBy
    Date dateApproved
    Date dateGranted
    ExternalCustomer externalCustomer
    List<Event> events

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false
        approvedBy nullable: true
        grantedBy nullable: true
        dateApproved nullable: true
        dateGranted nullable: true
        externalCustomer nullable: true
        events nullable: false, minSize: 1
    }

    static mapping = {
        events cascade: "all-delete-orphan"
    }

    static hasMany = [events: Event]

    String toString() { name }
}
