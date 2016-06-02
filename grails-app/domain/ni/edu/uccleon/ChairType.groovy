package ni.edu.uccleon

import groovy.transform.ToString

@ToString
class ChairType {
    String name

    ChairType(String name) {
        this.name = name
    }

    static constraints = {
        name inList: ["Pupitre", "Amueblada"], maxSize: 255
    }

    static belongsTo = [event: Event]
}
