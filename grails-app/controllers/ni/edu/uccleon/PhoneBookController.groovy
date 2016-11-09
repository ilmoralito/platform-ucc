package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured
import com.craigburke.document.builder.PdfDocumentBuilder

@Secured('permitAll')
class PhoneBookController {
    PhoneBookService phoneBookService

    def allowedMethods = [
        index: 'GET',
        printPhoneBook: 'GET'
    ]

    def index() {
        List phoneBook = phoneBookService.getPhoneBook()

        [phoneBook: phoneBook]
    }

    def printPhoneBook() {
        PdfDocumentBuilder pdfBuilder = new PdfDocumentBuilder(response.outputStream)
        List phoneBook = phoneBookService.getPhoneBook()

        pdfBuilder.create {
            document(
                font: [family: 'Helvetica', size: 8.pt],
                margin: [top: 0.2.inch, right: 0.5.inch, bottom: 0.5.inch, left: 0.5.inch],
                footer: { info ->
                    table(padding: 0, border: [size: 0]) {
                        row {
                            cell "Impreso ${info.dateGenerated.format('yyyy-MM-dd HH:mm')}", align: 'center'
                        }
                    }
                }
            ) {
                paragraph 'Directorio telefonico', align: 'center'

                table(columns: [1, 2, 2, 4], padding: 5, margin: [top: 0]) {
                    row {
                        cell '#', align: 'center'
                        cell 'Coordinacion'
                        cell 'Director'
                        cell 'Asistentes'
                    }

                    phoneBook.each { pb ->
                        if (pb.coordinations.size() == 1) {
                            row {
                                cell pb.extensionNumber, align: 'center'
                                cell pb.coordinations[0].name
                                cell pb.coordinations[0].manager
                                cell pb.coordinations[0].assistants
                            }
                        } else {
                            pb.coordinations.eachWithIndex { coordination, index ->
                                row {
                                    if (index == 0) {
                                        cell pb.extensionNumber, rowspan: pb.coordinations.size(), align: 'center'
                                    }
                                    cell coordination.name
                                    cell coordination.manager
                                    cell coordination.assistants
                                }
                            }
                        }
                    }
                }
            }
        }

        response.contentType = 'application/pdf'
        response.setHeader('Content-disposition', 'attachment;filename=phoneBook.pdf')
        response.outputStream.flush()
    }
}
