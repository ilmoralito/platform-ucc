package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured
import com.craigburke.document.builder.PdfDocumentBuilder

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
        List coordinations = coordinationService.getCoordinations()
        List phoneBook = phoneBookService.getPhoneBook(coordinations)
        PdfDocumentBuilder pdfBuilder = new PdfDocumentBuilder(response.outputStream)

        pdfBuilder.create {
            document(
                font: [family: "Helvetica", size: 9.pt],
                margin: [top: 0.2.inch, right: 0.5.inch, bottom: 0.5.inch, left: 0.5.inch],
                footer: { info ->
                    table(padding: 0, border: [size: 0]) {
                        row {
                            cell "Impreso ${info.dateGenerated.format('yyyy-MM-dd HH:mm')}", align: "center"
                        }
                    }
                }
            ) {
                paragraph "Directorio telefonico", align: "center"

                table(columns: [1, 2, 2, 4], padding: 5, margin: [top: 0]) {
                    row {
                        cell "#", align: "center"
                        cell "Coordinacion"
                        cell "Director"
                        cell "Asistentes"
                    }

                    phoneBook.each { pb ->
                        row {
                            cell pb.extensionNumber, align: "center"
                            cell pb.coordinationName
                            cell pb.manager
                            cell pb.assistants
                        }
                    }
                }
            }
        }

        response.contentType = "application/pdf"
        response.setHeader("Content-disposition", "attachment;filename=phoneBook.pdf")
        response.outputStream.flush()
    }
}
