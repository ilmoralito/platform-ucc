package ni.edu.uccleon

import groovy.transform.ToString
import org.grails.databinding.BindUsing

@ToString
class Activity {
    @BindUsing({
        obj, source -> source["name"]?.capitalize()
    })
    String name
    Boolean enabled = false
    User createdBy
    User approvedBy
    User grantedBy
    Date dateApproved
    Date dateGranted
    ExternalCustomer externalCustomer

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false
        createdBy()
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

    List events
    static hasMany = [events: Event]
}
