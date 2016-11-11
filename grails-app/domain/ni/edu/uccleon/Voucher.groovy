package ni.edu.uccleon

class Voucher {
    User user
    Guest guest
    Date date
    String activity
    BigDecimal value
    String status = 'pending'
    Date approvalDate

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
        status inList: ['pending', 'notified', 'approved'], maxSize: 100
        approvalDate nullable: true, validator: { approvalDate, obj ->
            if (obj.status == 'approved') {
                approvalDate != null
            }
        }
        foods nullable: false, minSize: 1
    }

    static hasMany = [foods: Food]

    static mapping = {
        version false
        foods lazy: false
        foods cascade: "all-delete-orphan"
    }
}
