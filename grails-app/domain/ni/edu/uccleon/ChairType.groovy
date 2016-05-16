package ni.edu.uccleon

import groovy.transform.ToString

@ToString
class ChairType {
    def grailsApplication

    String name

    ChairType(String name) {
        this()
        this.name = name
    }

    static constraints = { ctx ->
        name inList: ctx?.grailsApplication?.config?.ni?.edu?.uccleon?.chairTypes, maxSize: 255
    }
}
