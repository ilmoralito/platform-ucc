package ni.edu.uccleon

import groovy.transform.ToString

@ToString
class Chair {
    String name

    static constraints = {
        name inList: ["Desk", "Furnished"], maxSize: 255
    }
}
