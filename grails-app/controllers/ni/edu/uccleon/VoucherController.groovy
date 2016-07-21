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
        print: "GET",
        printSetOfVouchers: "GET",
        send: "GET"
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
            Date date = params.date("date", "yyyy-MM-dd")
            List<Integer> employees = params.list("employees")*.toInteger()
            List<Voucher> currentDateVouchers = voucherService.getVouchersByDate(date)
            Integer internalVouchersAdded = 0
            Integer internalVouchersUpdated = 0

            employees.each { employee ->
                InternalVoucher currentInternalVoucher = currentDateVouchers.find {
                    it.employee == employee
                }

                if (currentInternalVoucher && currentInternalVoucher.status == "pending") {
                    currentInternalVoucher.activity = params.activity
                    currentInternalVoucher.value = params.double("value")
                    currentInternalVoucher.refreshment = params.boolean("refreshment")
                    currentInternalVoucher.breakfast = params.boolean("breakfast")
                    currentInternalVoucher.lunch = params.boolean("lunch")
                    currentInternalVoucher.dinner = params.boolean("dinner")

                    if (!currentInternalVoucher.save()) {
                        internalVoucher.errors.allErrors.each { err ->
                            log.error "[field: $err.field, defaultMessage: $err.defaultMessage]"
                        }
                    }

                    internalVouchersUpdated++
                }

                if (!currentInternalVoucher) {
                    InternalVoucher internalVoucher = new InternalVoucher(
                        createdBy: springSecurityService.currentUser,
                        date: date,
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
                    }

                    internalVouchersAdded++
                }
            }

            flash.message = "Actualizados: $internalVouchersUpdated, creados: $internalVouchersAdded"
        }

        redirect action: "index"
    }

    @Secured(["ROLE_PROTOCOL_SUPERVISOR", "ROLE_ADMINISTRATIVE_SUPERVISOR"])
    def show(String date) {
        Date nDate = params.date("date", "yyyy-MM-dd").clearTime()
        List<Voucher> vouchers = voucherService.getVouchersByDate(nDate)

        [
            vouchers: vouchers,
            voucherViewModel: createVoucherViewModel(
                nDate,
                vouchers.status.groupBy { it }.collect { [status: it.key, size: it.value.size()] }
            )
        ]
    }

    @Secured(["ROLE_PROTOCOL_SUPERVISOR", "ROLE_ADMINISTRATIVE_SUPERVISOR"])
    def edit(Long id) {
        Voucher voucher = Voucher.get(id)

        if (!voucher) {
            response.sendError 404
        }

        List employees = employeeService.getEmployees()
        List<Integer> intervalVoucherIds = voucherService.getVouchersByDate(voucher.date).employee - voucher.employee
        List validEmployees = employees.findAll { employee ->
            !(employee.id in intervalVoucherIds)
        }

        [
            voucher: voucher,
            employees: validEmployees,
            foods: grailsApplication.config.ni.edu.uccleon.foods
        ]
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

        flash.message = "Vale eliminado correctamente"
        redirect action: "show", params: [date: voucher.date]
    }

    def print(Long id) {
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
                template: customTemplate
            ) {
                paragraph "Vale de alimentacion(Cafetines)", align: "center", margin: [top: 1.px]

                table(columns: [1, 2], padding: 2.px, border: [size: 1], margin: [top : 0, bottom: 5.px]) {
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
                        cell employeeService.getEmployeeCoordinations(voucher.employee).name.join(", ")
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

                String message = voucher.status == "approved" ? "Aprovado por: ${voucher.approvedBy.username}" : "SIN APROBAR"
                paragraph message, align: "center", margin: [top: 1.px, bottom: 0]
            }
        }

        response.contentType = "application/pdf"
        response.setHeader("Content-disposition", "attachment;filename=test.pdf")
        response.outputStream.flush()
    }

    def printSetOfVouchers(String date) {
        Date nDate = params.date("date", "yyyy-MM-dd")
        List<Voucher> vouchers = Voucher.where {
            date >= nDate && date <= nDate
        }.list(params)

        PdfDocumentBuilder pdfBuilder = new PdfDocumentBuilder(response.outputStream)
        def customTemplate = {
            "document" font: [family: "Courier", size: 9.pt], margin: [top: 0.5.inches, right: 0.5.inches, bottom: 0.5.inches, left: 0.5.inches]
            "cell.label" font: [bold: true]
            "cell.info" font: [size: 8.pt]
        }

        pdfBuilder.create {
            document(
                template: customTemplate,
            ) {
                vouchers.eachWithIndex { voucher, index ->
                    if (index == 4) {
                        pageBreak()
                    }

                    paragraph "Vale de alimentacion(Cafetines)", align: "center", margin: [top: 1.px]

                    table(columns: [1, 2], padding: 2.px, border: [size: 1], margin: [top : 0, bottom: 5.px]) {
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
                            cell employeeService.getEmployeeCoordinations(voucher.employee).name.join(", ")
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

                    String message = voucher.status == "approved" ? "Aprovado por: ${voucher.approvedBy.username}" : "SIN APROBAR"
                    paragraph message, align: "center", margin: [top: 1.px, bottom: 0]
                }
            }
        }

        response.contentType = "application/pdf"
        response.setHeader("Content-disposition", "attachment;filename=test.pdf")
        response.outputStream.flush()
    }

    @Secured(["ROLE_PROTOCOL_SUPERVISOR", "ROLE_ADMINISTRATIVE_SUPERVISOR"])
    def send(String date) {
        Map parameters = [:]
        String target = "pending"
        Date nDate = params.date("date", "yyyy-MM-dd")
        User currentUser = springSecurityService.loadCurrentUser()
        List<String> currentUserAuthorities = currentUser.authorities.authority

        if ("ROLE_ADMINISTRATIVE_SUPERVISOR" in currentUserAuthorities) {
            parameters.status = "approved"
            parameters.approvedBy = currentUser
            parameters.approvalDate = new Date()

            target = "notified"
        } else {
            parameters.status = "notified"
            parameters.dateNotification = new Date()
        }

        Integer vouchers = Voucher.where {
            date >= nDate && date <= nDate && status == target
        }.updateAll(*:parameters)

        flash.message = "$vouchers vales notificados"
        redirect action: "show", params: [date: date, tab: "notify"]
    }

    private createVoucherViewModel(Date date, List status) {
        new VoucherViewModel(
            date: date,
            status: status
        )
    }
}

class VoucherViewModel {
    Date date
    List status
}
