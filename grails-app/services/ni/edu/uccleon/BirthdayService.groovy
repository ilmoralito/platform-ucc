package ni.edu.uccleon

import grails.transaction.Transactional
import static java.util.Calendar.*

@Transactional
class BirthdayService {

    def getBirthdaysMonth(List employees) {
        List birthdays = []
        Date today = new Date()
        Integer currentMonth = today[Calendar.MONTH] + 1

        // Get current month birthdays
        employees.each { employee ->
            String birthdate = employee.identityCard.tokenize("-")[1]
            Integer birthdateMonth = birthdate[2..3].toInteger()

            if (currentMonth == birthdateMonth) {
                birthdays << employee.subMap(['fullName', 'identityCard'])
            }
        }

        // Get birtdays day
        List data = birthdays.collect { a ->
            [
                day: a.identityCard.tokenize("-")[1][0..1],
                birthdayBoy: a.fullName
            ]
        }

        // Group birthdays by day
        List birthdaysByDay = data.groupBy { it.day }.collect { a ->
            [
                day: a.key,
                birthdays: a.value.birthdayBoy.join(", ")
            ]
        }

        birthdaysByDay
    }
}
