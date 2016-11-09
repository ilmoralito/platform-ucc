package ni.edu.uccleon

import grails.transaction.Transactional
import static java.util.Calendar.*

@Transactional
class BirthdayService {
    EmployeeService employeeService
    HelperService helperService

    def getBirthdaysMonth() {
        List birthdays = []
        Date today = new Date()
        Integer month = helperService.getMonth()
        List employees = employeeService.getEmployees()

        // Get current month birthdays
        employees.each { employee ->
            String birthdate = employee.identityCard.tokenize("-")[1]
            Integer birthdateMonth = birthdate[2..3].toInteger()

            if (month == birthdateMonth) {
                birthdays << employee.subMap(['fullName', 'identityCard'])
            }
        }

        // Get birtdays day
        List data = birthdays.collect { a ->
            [
                day: a.identityCard.tokenize('-')[1][0..1],
                birthdayBoy: a.fullName
            ]
        }

        // Group birthdays by day
        List birthdaysByDay = data.groupBy { it.day }.collect { a ->
            [
                day: a.key,
                birthdays: a.value.birthdayBoy.join(', ')
            ]
        }

        birthdaysByDay
    }

    List getBirthdaysOfTheDay(List employees, Date date = new Date()) {
        Integer dayOfMonth = helperService.getDayOfMonth()
        Integer month = helperService.getMonth()
        String target = dayOfMonth
        List birthdays = []

        employees.each { employee ->
            String birthdate = employee.identityCard.tokenize('-')[1][0..3]

            if (target == birthdate) {
                birthdays << employee.subMap(['fullName', 'identityCard'])
            }
        }

        birthdays
    }
}
