package ni.edu.uccleon

import org.grails.databinding.BindingFormat
import groovy.transform.AutoClone

@AutoClone
class Event {
    @BindingFormat("yyyy-MM-dd")
    Date date
    Integer startTime
    Integer endingTime
    Integer numberOfPeople
    String location

    Boolean audiovisual = false
    Boolean wifi = false
    Boolean sound = false
    Boolean speaker = false
    Boolean microfone = false
    Boolean pointer = false

    Boolean water = false
    Boolean coffee = false
    Integer cookies
    Integer waterBottles

    String mountingType
    Integer presidiumTable
    Boolean flags = false
    Boolean podium = false
    Boolean tableForSpeaker = true
    Boolean tablecloths = true

    Integer refreshment
    Integer breakfast
    Integer lunch
    Integer dinner

    String observation

    List<TableType> tableTypes
    List<ChairType> chairTypes
    List<TableclothColor> tableclothColors

    static constraints = {
        date validator: { date, obj ->
            Date today = new Date()

            date >= today + 2
        }
        startTime blank: false, range: 8..16
        endingTime blank: false, range: 9..17, validator: { endingTime, obj ->
            endingTime > obj.startTime
        }
        numberOfPeople min: 1
        cookies nullable: true, min: 1
        waterBottles nullable: true, min: 1
        mountingType inList: [
            "Libre",
            "Forma U",
            "Auditorium con mesas",
            "Auditorium sin mesas",
            "Sala de reunion",
            "Grupo"
        ], maxSize: 255
        presidiumTable nullable: true, min: 1
        refreshment nullable: true, min: 1
        breakfast nullable: true, min: 1
        lunch nullable: true, min: 1
        dinner nullable: true, min: 1
        observation nullable: true, maxSize: 255
    }

    static belongsTo = [activity: Activity]

    static mapping = {
        tableTypes cascade: "all-delete-orphan"
        chairTypes cascade: "all-delete-orphan"
        tableclothColors cascade: "all-delete-orphan"
    }

    static hasMany = [tableTypes: TableType, chairTypes: ChairType, tableclothColors: TableclothColor]

    String toString() { date }
}
