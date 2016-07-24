package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured("permitAll")
class PhoneBookController {
    def coordinationService
    def phoneBookService

    def allowedMethods = [
        index: "GET",
        printPhoneBook: "GET"
    ]

    def index() {
        List coordinations = coordinationService.getCoordinations()
        List phoneBook = phoneBookService.getPhoneBook(coordinations)

        [phoneBook: phoneBook]
    }

    def printPhoneBook() {

    }
}
