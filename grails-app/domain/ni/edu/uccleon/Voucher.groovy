package ni.edu.uccleon

class Voucher {
    Date date
    BigDecimal value
    Boolean refreshment
    Boolean breakfast
    Boolean lunch
    Boolean dinner

    static constraints = {
        date validator: { date ->
            date >= new Date()
        }
        value min: 1.0
    }
}
