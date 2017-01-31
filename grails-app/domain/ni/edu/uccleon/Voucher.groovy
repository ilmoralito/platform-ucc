package ni.edu.uccleon

class Voucher {
    User user
    Guest guest
    Date date
    String activity
    BigDecimal value
    String status = 'pending'
    Date approvalDate
    User createdBy
    User lastUpdatedBy
    String reasonForCancellation

    List<Food> foods

    Date dateCreated
    Date lastUpdated

    static constraints = {
        user nullable: true
        guest nullable: true, validator: { guest, obj ->
            obj.user || guest
        }
        date blank: false
        activity blank: false
        value min: 1.0
        status inList: ['pending', 'notified', 'approved', 'canceled'], maxSize: 100, validator: { status, obj ->
            if (status == 'canceled' && new Date() > obj.date) {
                return 'invalid.cancellation.date'
            }
        }
        approvalDate nullable: true, validator: { approvalDate, obj ->
            if (obj.status == 'approved') {
                approvalDate != null
            }
        }
        lastUpdatedBy nullable: true
        reasonForCancellation nullable: true, maxSize: 1500, validator: { reasonForCancellation, obj ->
            if (!reasonForCancellation && obj.status == 'canceled') {
                return 'necessary.data'
            }
        }
        foods nullable: false, minSize: 1
    }

    static hasMany = [foods: Food]

    static mapping = {
        version false
        foods cascade: "all-delete-orphan", lazy: false
        reasonForCancellation sqlType: 'text'
    }
}
