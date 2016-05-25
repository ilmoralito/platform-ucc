package ni.edu.uccleon

class Activity {
    def eventService

    String name
    User createdBy
    User approvedBy
    Date dateApproved
    User grantedBy
    Date dateGranted
    Boolean notified = false
    User notifiedBy
    Date notificationDate
    String coordination
    ExternalCustomer externalCustomer
    String status = "pending"

    List<Event> events

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false
        approvedBy nullable: true
        dateApproved nullable: true
        grantedBy nullable: true
        dateGranted nullable: true
        notifiedBy nullable: true
        notificationDate nullable: true, validator: { notificationDate, obj ->
            if (notificationDate) {
                Date date = obj.eventService.getEventMinDate(obj)
                Integer diff = date - notificationDate

                diff >= 3
            }
        }
        coordination blank: false
        externalCustomer nullable: true
        status inList: ["pending", "notified", "granted", "approved", "done"], maxSize: 255
        events nullable: false, minSize: 1
    }

    static mapping = {
        events cascade: "all-delete-orphan"
        sort dateCreated: "desc"
    }

    static hasMany = [events: Event]

    String toString() { name }
}
