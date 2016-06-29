package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured
import com.craigburke.document.builder.PdfDocumentBuilder

@Secured("ROLE_PROTOCOL_SUPERVISOR")
class VoucherController {
    def springSecurityService
    def employeeService
    def voucherService

    static allowedMethods = [
        index: "GET",
        create: "POST",
        edit: "GET",
        show: "GET",
        update: "POST",
        delete: "GET",
        printIt: "GET",
        printItAll: "GET",
        sendAll: "GET"
    ]

    def index() {
        List<Voucher> vouchers = Voucher.where { status != "approved" }.list()

        [
            data: voucherService.groupVouchersByDate(vouchers),
            foods: grailsApplication.config.ni.edu.uccleon.foods
        ]
    }

    def create() {
        if (params.type == "interval") {
            List<Integer> employees = params.list("employees")*.toInteger()

            if (employees) {
                employees.each { employee ->
                    InternalVoucher internalVoucher = new InternalVoucher(
                        createdBy: springSecurityService.currentUser,
                        date: params.date("date", "yyyy-MM-dd"),
                        employee: employee,
                        activity: params.activity,
                        value: params.double("value"),
                        refreshment: params.boolean("refreshment"),
                        breakfast: params.boolean("breakfast"),
                        lunch: params.boolean("lunch"),
                        dinner: params.boolean("dinner")
                    )

                    if (!internalVoucher.save()) {
                        internalVoucher.errors.allErrors.each { err ->
                            log.error "[field: $err.field, defaultMessage: $err.defaultMessage]"
                        }

                        flash.bag = internalVoucher
                    }

                    flash.message = internalVoucher.hasErrors() ? "Error" : "Accion concluida"
                }
            }
        }

        redirect action: "index"
    }

    @Secured(["ROLE_PROTOCOL_SUPERVISOR", "ROLE_ADMINISTRATIVE_SUPERVISOR"])
    def show(String date) {
        Date nDate = params.date("date", "yyyy-MM-dd").clearTime()

        [
            vouchers: voucherService.getVouchersByDate(nDate),
            foods: grailsApplication.config.ni.edu.uccleon.foods
        ]
    }

    def edit(Long id) {
        Voucher voucher = Voucher.get(id)

        if (!voucher) {
            response.sendError 404
        }

        [voucher: voucher, foods: grailsApplication.config.ni.edu.uccleon.foods]
    }

    @Secured(["ROLE_PROTOCOL_SUPERVISOR", "ROLE_ADMINISTRATIVE_SUPERVISOR"])
    def update(Long id) {
        Voucher voucher = Voucher.get(id)

        if (!voucher) {
            response.sendError 404
        }

        voucher.properties["employee", "activity", "value", "refreshment", "breakfast", "lunch", "dinner"] = params

        if (!voucher.save()) {
            voucher.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = voucher
        }

        flash.message = voucher.hasErrors() ? "A ocurrido un error" : "Tarea concluida"
        redirect action: "edit", id: id
    }

    @Secured(["ROLE_PROTOCOL_SUPERVISOR", "ROLE_ADMINISTRATIVE_SUPERVISOR"])
    def delete(Long id) {
        Voucher voucher = Voucher.get(id)

        if (!voucher) {
            response.sendError 404
        }

        voucher.delete()

        flash.message = "Vale eliminado"
        redirect action: "show", params: [date: voucher.date]
    }

    def printIt(Long id) {
        Voucher voucher = Voucher.get(id)

        if (!voucher) {
            response.sendError 404
        }

        PdfDocumentBuilder pdfBuilder = new PdfDocumentBuilder(response.outputStream)
        def customTemplate = {
            "document" font: [family: "Courier", size: 9.pt], margin: [top: 0.5.inches]
            "cell.label" font: [bold: true]
            "cell.info" font: [size: 8.pt]
        }

        pdfBuilder.create {
            document(
                template: customTemplate,
                header: { info ->
                    table(border: [size: 0]) {
                        row {
                            cell "Universidad de Ciencias Comerciales", align: "center", style: "info"
                        }
                    }
                },
                footer: { info ->
                    table(border: [size: 0]) {
                        row {
                            cell "Impreso ${new Date().format('yyyy-MM-dd HH:mm:ss')}", align: "center", style: "info"
                        }
                    }
                }
            ) {
                paragraph "Vale de alimentacion(Cafetines)", align: "center"

                table(columns: [1, 2], padding: 2.px, border: [size: 1]) {
                    row {
                        cell "Fecha", style: "label"
                        cell voucher.date.format("yyyy-MM-dd")
                    }

                    row {
                        cell "A nombre de", style: "label"
                        cell employeeService.getEmployee(voucher.employee).fullName
                    }

                    row {
                        cell "Coordinacion", style: "label"
                        cell employeeService.getEmployeeCoordination(voucher.employee)
                    }

                    row {
                        cell "Area", style: "label"
                        cell employeeService.getEmployeeLocation(voucher.employee, true)
                    }

                    row {
                        cell "Actividad", style: "label"
                        cell voucher.activity
                    }

                    row {
                        cell "Valor", style: "label"
                        cell voucher.value
                    }

                    row {
                        cell "Alimentos", style: "label"
                        cell {
                            if (voucher.refreshment) {
                                text "Refresco, "
                            }

                            if (voucher.breakfast) {
                                text "Desayuno, "
                            }

                            if (voucher.lunch) {
                                text "Almuerzo, "
                            }

                            if (voucher.dinner) {
                                text "Cena"
                            }
                        }
                    }
                }

                paragraph "${voucher.status == 'approved' ? 'Aprovado por: $voucher.approvedBy.fullName' : 'SIN APROBAR'}", align: "center"
            }
        }

        response.contentType = "application/pdf"
        response.setHeader("Content-disposition", "attachment;filename=test.pdf")
        response.outputStream.flush()
    }

    def printItAll(String date) {
        Date nDate = params.date("date", "yyyy-MM-dd").clearTime()
        List<Voucher> vouchers = Voucher.where {
            date >= nDate && date <= nDate
        }.list(params)

        PdfDocumentBuilder pdfBuilder = new PdfDocumentBuilder(response.outputStream)
        def customTemplate = {
            "document" font: [family: "Courier", size: 9.pt], margin: [top: 0.5.inches]
            "cell.label" font: [bold: true]
            "cell.info" font: [size: 8.pt]
        }

        pdfBuilder.create {
            document(
                template: customTemplate,
                footer: { info ->
                    table(border: [size: 0]) {
                        row {
                            cell "Impreso ${new Date().format('yyyy-MM-dd HH:mm:ss')}", align: "center", style: "info"
                        }
                    }
                }
            ) {
                vouchers.eachWithIndex { voucher, index ->
                    if (index == 3) {
                        pageBreak()
                    }

                    paragraph "Universidad de Ciencias Comerciales", align: "center", font: [bold: true, size: 7.pt], margin: [top: 0, bottom: 0]
                    paragraph "Vale de alimentacion(Cafetines)", align: "center", margin: [top: 1.px]

                    table(columns: [1, 2], padding: 2.px, border: [size: 1], margin: [bottom: 5.px]) {
                        row {
                            cell "Fecha", style: "label"
                            cell voucher.date.format("yyyy-MM-dd")
                        }

                        row {
                            cell "A nombre de", style: "label"
                            cell employeeService.getEmployee(voucher.employee).fullName
                        }

                        row {
                            cell "Coordinacion", style: "label"
                            cell employeeService.getEmployeeCoordination(voucher.employee)
                        }

                        row {
                            cell "Area", style: "label"
                            cell employeeService.getEmployeeLocation(voucher.employee, true)
                        }

                        row {
                            cell "Actividad", style: "label"
                            cell voucher.activity
                        }

                        row {
                            cell "Valor", style: "label"
                            cell voucher.value
                        }

                        row {
                            cell "Alimentos", style: "label"
                            cell {
                                if (voucher.refreshment) {
                                    text "Refresco, "
                                }

                                if (voucher.breakfast) {
                                    text "Desayuno, "
                                }

                                if (voucher.lunch) {
                                    text "Almuerzo, "
                                }

                                if (voucher.dinner) {
                                    text "Cena"
                                }
                            }
                        }
                    }

                    String message = voucher.status == "approved" ? "Aprovado por: ${voucher.approvedBy.fullName}" : "SIN APROBAR"

                    paragraph message, align: "center", margin: [top: 1.px]
                }
            }
        }

        response.contentType = "application/pdf"
        response.setHeader("Content-disposition", "attachment;filename=test.pdf")
        //response.outputStream << out.toByteArray()
        response.outputStream.flush()
    }

    @Secured(["ROLE_PROTOCOL_SUPERVISOR", "ROLE_ADMINISTRATIVE_SUPERVISOR"])
    def sendAll(String date) {
        Date nDate = params.date("date", "yyyy-MM-dd").clearTime()
        User currentUser = springSecurityService.currentUser
        List<String> currentUserAuthorities = currentUser.authorities.authority
        Map params = [:]

        if ("ROLE_ADMINISTRATIVE_SUPERVISOR" in currentUserAuthorities) {
            params.status = "approved"
            params.approvedBy = currentUser
            params.approvalDate = new Date()
        } else {
            params.status = "notified"
            params.dateNotification = new Date()
        }

        Integer vouchers = Voucher.where {
            date >= nDate && date <= nDate
        }.updateAll(*:params)

        if ("ROLE_ADMINISTRATIVE_SUPERVISOR" in currentUserAuthorities) {
            flash.message = "$vouchers vales autorizados"
            redirect controller: "panel"
        } else {
            flash.message = "$vouchers vales notificados"
            redirect action: "show", params: [date: date]
        }
    }
}
