package ni.edu.uccleon

import groovy.transform.ToString

@ToString
class TableclothColor {
    String name

    static constraints = {
        name blank: false, unique: "event"
    }

    TableclothColor(String name) {
        this.name = name
    }

    static belongsTo = [event: Event]
}
