package ni.edu.uccleon

import groovy.transform.ToString

@ToString
class Table {
    String name

    static constraints = {
        name inList: ["Blue", "White", "Presidium", "Folding"], maxSize: 255
    }
}
