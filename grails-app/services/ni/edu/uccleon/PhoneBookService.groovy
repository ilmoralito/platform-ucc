package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class PhoneBookService {
    CoordinationService coordinationService

    List getPhoneBook() {
        List coordinations = coordinationService.getCoordinations()
        List phoneBook = coordinations.groupBy { it.extensionNumber }.collect { a ->
            [
                extensionNumber: a.key,
                coordinations: a.value.collect { b ->
                    [
                        name: b.name,
                        manager: b.employees.find { it.authority == 'Manager' }?.fullName,
                        assistants: b.employees.findAll { it.authority == 'Assistant' }?.fullName?.join(", ")
                    ]
                }
            ]
        }.sort { it.extensionNumber }

        phoneBook
    }
}
