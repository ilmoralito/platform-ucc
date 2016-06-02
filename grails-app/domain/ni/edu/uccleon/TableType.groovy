package ni.edu.uccleon

import groovy.transform.ToString

@ToString
class TableType {
    String name

    TableType(String name) {
        this.name = name
    }

    static constraints = {
        name inList: ["Azules", "Blancas", "Plegable", "Madera"], maxSize: 255
    }

    static belongsTo = [event: Event]
}
