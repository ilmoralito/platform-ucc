package ni.edu.uccleon

class Food {
    String name

    static constraints = {
        name inList: ['refreshment', 'breakfast', 'lunch', 'dinner'], maxSize: 255
    }

    static belongsTo = [voucher: Voucher]

    static mapping = {
        version: false
    }
}
