package ni.edu.uccleon

class CongratulateBirthdaysJob {
    def employeeService
    def birthdayService

    static triggers = {
        // Fire everyday at 8:30am
        cron name: "toCongratulate", cronExpression: "0 10 11 ? * MON-SUN"

        // simple name: 'mySimpleTrigger', startDelay: 10000, repeatInterval: 1000
    }

    def execute() {
        // Get today birthdays
        List employees = employeeService.getEmployees()
        println birthdayService.getBirthdaysOfTheDay(employees)

        // Send to each one an congratulation email
    }
}
