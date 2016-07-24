package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class PhoneBookService {

    List getPhoneBook(List coordinations) {
        List phoneBook = coordinations.collect { a ->
            [
                extensionNumber: a.extensionNumber,
                coordinationName: a.name,
                manager: a.employees.find { it.authority == "Manager" }.fullName,
                assistants: a.employees.findAll { it.authority == "Assistant" }.fullName.join(", ")
            ]
        }

        phoneBook
    }
}
