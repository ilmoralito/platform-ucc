package ni.edu.uccleon

import groovy.transform.ToString
import org.grails.databinding.BindingFormat

@ToString
class Event {
    Date dateOfTheEvent
    @BindingFormat("hh:mm")
    Date startTime
    @BindingFormat("hh:mm")
    Date endingTime
    Integer numberOfPeople
    String location

    Boolean audiovisual = false
    Boolean wifi = false
    Boolean sound = false
    Boolean speaker = false
    Boolean microfone = false

    Boolean water = false
    Integer coffee
    Integer tea
    Integer cakeShop
    Integer cookies
    Integer waterBottles

    String mountingType
    Boolean flags = false
    Boolean podium = false
    Boolean tableForSpeaker = true
    Boolean tablecloths = true

    Integer refreshment
    Integer breakfast
    Integer lunch
    Integer dinner

    String observation

    static constraints = {
        dateOfTheEvent validator: { dateOfTheEvent, obj ->
            Date today = new Date()

            dateOfTheEvent >= today + 3
        }
        startTime blank: false
        endingTime validator: { endingTime, obj ->
            endingTime > obj.startTime
        }
        numberOfPeople min: 1
        coffee nullable: true, min: 1
        tea nullable: true, min: 1
        cakeShop nullable: true, min: 1
        cookies nullable: true, min: 1
        waterBottles nullable: true, min: 1
        mountingType inList: ["Forma U", "Forma auditorium con mesas", "Forma auditorium sin mesas", "Grupo", "Sala de reunion", "Libre"], maxSize: 255
        refreshment nullable: true, min: 1
        breakfast nullable: true, min: 1
        lunch nullable: true, min: 1
        dinner nullable: true, min: 1
        observation nullable: true
    }

    static belongsTo = [activity: Activity]

    List tables
    List chairs
    static hasMany = [tables: Table, chairs: Chair]
}
