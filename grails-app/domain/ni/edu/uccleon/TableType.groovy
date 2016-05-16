package ni.edu.uccleon

import groovy.transform.ToString

@ToString
class TableType {
    def grailsApplication

    String name

    TableType(String name) {
        this()
        this.name = name
    }

    static constraints = { ctx ->
        name inList: ctx?.grailsApplication?.config?.ni?.edu?.uccleon?.tableTypes, maxSize: 255
    }
}
