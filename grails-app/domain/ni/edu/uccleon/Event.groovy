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
    Integer cookies = 0
    Integer waterBottles = 0

    String mountingType
    Integer presidiumTable = 0
    Boolean flags = false
    Boolean podium = false
    Boolean tableForSpeaker = true
    Boolean tablecloths = true

    Integer refreshment = 0
    Integer breakfast = 0
    Integer lunch = 0
    Integer dinner = 0

    String observation

    List<TableType> tableTypes
    List<ChairType> chairTypes
    List<TableclothColor> tableclothColors

    static constraints = {
        date validator: { date ->
            Date today = new Date()

            date > today + 2
        }
        startTime blank: false, range: 8..16
        endingTime blank: false, range: 9..17, validator: { endingTime, obj ->
            endingTime > obj.startTime
        }
        numberOfPeople min: 1
        cookies min: 0
        waterBottles min: 0
        mountingType inList: [
            "Libre",
            "Forma U",
            "Auditorium con mesas",
            "Auditorium sin mesas",
            "Sala de reunion",
            "Grupo"
        ], maxSize: 255
        presidiumTable min: 0, max: 7
        tablecloths validator: { tablecloths, obj ->
            if (tablecloths) {
                obj.tableclothColors.size() >= 1
            }
        }
        refreshment min: 0
        breakfast min: 0
        lunch min: 0
        dinner min: 0
        observation nullable: true, maxSize: 255

        tableclothColors validator: { tableclothColors, obj ->
            if (tableclothColors.size()) {
                return obj.tablecloths == true
            }
        }
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
