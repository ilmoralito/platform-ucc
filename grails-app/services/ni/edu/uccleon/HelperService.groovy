package ni.edu.uccleon

import grails.transaction.Transactional
import static java.util.Calendar.*
import java.util.Locale

@Transactional
class HelperService {

    def getDayName() {
        Locale locale = new Locale("es_NI")
        Calendar calendar = Calendar.instance

        calendar.format("EEEE")
    }

    def getInterval(String type) {
        Calendar calendar = Calendar.instance
        Calendar from = calendar.clone()
        Calendar to = calendar.clone()

        if (type == "year") {
            Integer year = calendar[YEAR]

            from.set(Calendar.YEAR, year)
            from.set(Calendar.MONTH, 0)
            from.set(Calendar.DAY_OF_MONTH, 1)

            to.set(Calendar.YEAR, year)
            to.set(Calendar.MONTH, 11)
            to.set(Calendar.DAY_OF_MONTH, 31)
        } else if (type == "month") {
            from.set(Calendar.DAY_OF_MONTH, from.getActualMinimum(Calendar.DAY_OF_MONTH))
            to.set(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH))
        } else if (type == "week") {
            from.set(from.DAY_OF_WEEK, calendar.SUNDAY)
            to.set(to.DAY_OF_WEEK, 7)
        } else if (type == "day") {

        } else {

        }

        from.getTime()..to.getTime()
    }

    Integer getDayOfWeek(Date date) {
        Calendar calendar = Calendar.instance

        calendar.setTime(date)

        calendar.get(Calendar.DAY_OF_WEEK)
    }

    Integer getMonthFirstDayOfWeek() {
        Calendar calendar = Calendar.instance

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH))

        getDayOfWeek(calendar.getTime())
    }

    Integer getDayOfMonth(Date date = new Date()) {
        date[Calendar.DAY_OF_MONTH]
    }

    Integer getMonth(Date date = new Date()) {
        date[Calendar.MONTH] + 1
    }
}
