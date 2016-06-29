package ni.edu.uccleon

class InternalVoucher extends Voucher {
    Integer employee
    String activity

    static constraints = {
        activity blank: false, maxSize: 255
    }
}
