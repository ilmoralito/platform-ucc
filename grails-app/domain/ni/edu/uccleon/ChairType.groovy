package ni.edu.uccleon

import groovy.transform.ToString

@ToString
class ChairType {
    String name

    ChairType(String name) {
        this.name = name
    }

    static constraints = {
        name inList: ["Pupitre", "Amueblado"], maxSize: 255
    }

    static belongsTo = [event: Event]
}
