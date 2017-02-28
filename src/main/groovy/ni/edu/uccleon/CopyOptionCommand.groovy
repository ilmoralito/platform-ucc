package ni.edu.uccleon

import grails.validation.Validateable
import grails.plugin.springsecurity.SpringSecurityService

class CopyOptionCommand implements Validateable {
    EmployeeService employeeService
    SpringSecurityService springSecurityService

    Integer coordination
    String copyType

    static constraints = {
        coordination blank: false, validator: { coordination, obj ->
            coordination in obj.employeeService.getEmployee(obj.springSecurityService.currentUser.employee).coordinations.id
        }
        copyType inList: ['copy', 'extraCopy']
    }
}
