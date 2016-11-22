package ni.edu.uccleon

import com.craigburke.document.builder.PdfDocumentBuilder
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.util.Environment

@Secured('ROLE_PROTOCOL_SUPERVISOR')
class VoucherController {
    SpringSecurityService springSecurityService
    EmployeeService employeeService
    VoucherService voucherService

    static allowedMethods = [
        index: 'GET',
        approvalDates: 'GET',
        approved: 'GET',
        sendNotification: 'POST',
        create: 'GET',
        store: 'POST',
        show: 'GET',
        edit: 'GET',
        update: 'POST',
        delete: 'GET',
        vouchersToApprove: 'GET',
        approve: 'POST',
        archive: 'POST',
        summary: 'GET',
        printVouchers: 'POST',
        filter: 'POST',
        batchDelete: 'POST'
    ]

    def index(String status) {
        List<Voucher> vouchers = voucherService.getVouchersByStatus(status ?: 'pending')

        [
            vouchers: voucherService.getVouchersGroupedByDateAndActivity(vouchers),
            activities: voucherService.getVoucherActivities()
        ]
    }

    def approvalDates() {
        [
            approvalDates: voucherService.getVouchersApprovalDates(),
            activities: voucherService.getVoucherActivities()
        ]
    }

    def approved(String approvalDate) {
        Date date = params.date('approvalDate', 'yyyy-MM-dd')
        List<Voucher> vouchers = voucherService.getVouchersByApprovalDate(date)
        def(users, guests) = voucherService.getMembersInApprovalDate(date)

        render view: 'approvedList', model: [
            vouchers: voucherService.getVouchersGroupedByDateAndActivity(vouchers),
            activities: voucherService.getVoucherActivities(),
            guests: guests,
            users: users
        ]
    }

    def sendNotification() {
        List<Long> voucherList = params.list('vouchers')

        if (voucherList) {
            Integer total = voucherService.updateVouchersStatus(voucherList*.toLong(), 'notified')

            if (Environment.current == Environment.PRODUCTION) {
                sendMail {
                    from 'orlando.gaitan@ucc.edu.ni'
                    to 'jorge.rojas@ucc.edu.ni'
                    subject 'Notificacion de vales pendientes de aprobacion'
                    html view: '/emails/voucher/notification', model: [total: total, url: createLink(controller: 'voucher', action: 'vouchersToApprove', absolute: true), label: 'notificado']
                }
            }

            flash.message = "${total} vales notificados"
        } else {
            flash.message = 'Parametros incorrectos'
        }

        redirect action: 'index', params: [status: 'pending']
    }

    def create(CreateCommand command) {
        if (command.hasErrors()) {
            command.errors.allErrors.each { error ->
                log.error "$error.field: $error.defaultMessage"
            }

            flash.message = 'Parametros incorrectos'
            redirect uri: request.getHeader('referer')
            return
        }

        List<Voucher> vouchers = voucherService.getVouchersByDateAndActivity(command.date, command.activity)

        [
            users: voucherService.getValidUsers(command.type, command.activity, command.date),
            foods: voucherService.getFoods(),
            activity: command.activity,
            type: command.type,
            date: command.date,
            vouchers: vouchers
        ]
    }

    def store() {
        Integer id = params.int('user')
        def member = params.type == 'user' ? User.get(id) : Guest.get(id)

        if (!member) {
            response.sendError 404
        }

        Voucher voucher = new Voucher(
            user: member instanceof User ? member : null,
            guest: member instanceof Guest ? member : null,
            date: params.date('date', 'yyyy-MM-dd'),
            activity: params.activity,
            value: params.double('value')
        )

        params.list('foods').each { food ->
            voucher.addToFoods(new Food(name: food))
        }

        if (!voucher.save()) {
            voucher.errors.allErrors.each { error ->
                log.error "$error.field: $error.defaultMessage"
            }

            flash.message = 'Parametros incorrectos'
        } else {
            flash.message = 'Vale creado'
        }

        redirect action: 'create', params: params
    }

    @Secured(['ROLE_PROTOCOL_SUPERVISOR', 'ROLE_ADMINISTRATIVE_SUPERVISOR'])
    def show(Voucher voucher) {
        respond voucher
    }

    @Secured(['ROLE_PROTOCOL_SUPERVISOR', 'ROLE_ADMINISTRATIVE_SUPERVISOR'])
    def edit(Voucher voucher) {
        def member = voucher?.user ?: voucher.guest
        String type = member.class.name.tokenize('.')[-1].toLowerCase()

        respond voucher, model: [
            users: voucherService.getValidUsers(type, voucher.activity, voucher.date) + member,
            activities: voucherService.getVoucherActivities(),
            foods: voucherService.getFoods(),
            type: type
        ]
    }

    @Secured(['ROLE_PROTOCOL_SUPERVISOR', 'ROLE_ADMINISTRATIVE_SUPERVISOR'])
    def update() {
        Voucher voucher = Voucher.get(params.int('id'))
        List<String> foodList = params.list('foods')
        String type = params.type

        if (!voucher) {
            response.sendError 404
        }

        voucher[type] = type == 'user' ? User.get(params.int('user')) : Guest.get(params.int('user'))
        voucher.date = params.date('voucherDate', 'yyyy-MM-dd')
        voucher.value = params.double('value')
        voucher.activity = params.activity

        if (!voucher.save() || !foodList) {
            voucher.errors.allErrors.each { error ->
                log.error "$error.field: $error.defaultMessage"
            }

            flash.message = 'Parametros incorrectos'
            redirect action: 'edit', id: voucher.id
            return
        }

        voucher.foods.clear()

        foodList.each { food ->
            Food foodInstance = new Food(name: food)
            voucher.addToFoods(foodInstance)
        }

        flash.message = 'Vale actualizado'
        redirect action: 'show', id: voucher.id
    }

    @Secured(['ROLE_PROTOCOL_SUPERVISOR', 'ROLE_ADMINISTRATIVE_SUPERVISOR'])
    def delete(Voucher voucher) {
        User currentUser = springSecurityService.getCurrentUser()
        List<String> currentUserAuthorities = currentUser.authorities.authority

        voucher.delete()
        flash.message = 'Vale eliminado'

        if ('ROLE_PROTOCOL_SUPERVISOR' in currentUserAuthorities) {
            redirect action: 'index', params: [status: 'pending']
        } else {
            redirect action: 'vouchersToApprove'
        }
    }

    @Secured('ROLE_ADMINISTRATIVE_SUPERVISOR')
    def vouchersToApprove() {
        List<Voucher> vouchers = voucherService.getVouchersByStatus('notified')

        [vouchers: voucherService.getVouchersGroupedByDateAndActivity(vouchers)]
    }

    @Secured('ROLE_ADMINISTRATIVE_SUPERVISOR')
    def approve() {
        List<Long> voucherList = params.list('vouchers')

        if (voucherList) {
            Integer total = voucherService.updateVouchersStatus(voucherList*.toLong(), 'approved')

            if (Environment.current == Environment.PRODUCTION) {
                sendMail {
                    from 'jorge.rojas@ucc.edu.ni'
                    to 'orlando.gaitan@ucc.edu.ni'
                    subject 'Notificacion de vales aprobados'
                    html view: '/emails/voucher/notification', model: [
                        total: total,
                        url: createLink(controller: 'voucher', action: 'approved', params: [approvalDate: new Date().format('yyyy-MM-dd')], absolute: true),
                        label: 'aprobado'
                    ]
                }
            }

            flash.message = "${total} vales aprobados"
        } else {
            flash.message = 'Parametros incorrectos'
        }

        redirect action: 'vouchersToApprove'
    }

    @Secured('ROLE_ADMINISTRATIVE_SUPERVISOR')
    def summary() {
        [vouchers: voucherService.generateSummary()]
    }

    @Secured('ROLE_ADMINISTRATIVE_SUPERVISOR')
    def printSummary() {
        List<Voucher> vouchers = voucherService.generateSummary()

        PdfDocumentBuilder builder = new PdfDocumentBuilder(response.outputStream)

        builder.create {
            document(
                font: [family: 'Courier', size: 9.pt],
                margin: [top: 0.5.inches, right: 0.5.inches, bottom: 0.5.inches, left: 0.5.inches],
                header: { info ->
                    paragraph 'Resumen de vales'
                },
                footer: { info ->
                    paragraph "Fecha y hora de impresion ${info.dateGenerated.format('yyyy-MM-dd hh:mm a')}"
                }
            ) {
                vouchers.each { voucher ->
                    paragraph voucher.year, margin: [bottom: 0]

                    table {
                        row {
                            cell colspan: 3
                            cell 'Visita', colspan: 2
                            cell 'Interno', colspan: 2
                        }
                        row {
                            cell 'Mes'
                            cell 'Total'
                            cell 'Total NIO'
                            cell 'Total'
                            cell 'Total NIO'
                            cell 'Total'
                            cell 'Total NIO'
                        }

                        voucher.summary.each { summary ->
                            row {
                                cell summary.month
                                cell summary.total
                                cell summary.totalNIO
                                cell summary.totalGuest.toString()
                                cell summary.totalGuestNIO.toString()
                                cell summary.totalInternal.toString()
                                cell summary.totalInternalNIO.toString()
                            }
                        }
                    }

                    pageBreak()
                }
            }
        }

        response.contentType = "application/pdf"
        response.setHeader("Content-disposition", "attachment;filename=summary.pdf")
        response.outputStream.flush()
    }

    def printVouchers() {
        List<Voucher> voucherList = Voucher.getAll(params.list('vouchers')).collate(5)
        PdfDocumentBuilder builder = new PdfDocumentBuilder(response.outputStream)

        builder.create {
            document(
                font: [family: 'Courier', size: 10.pt],
                margin: [top: 0.1.inches, right: 0.2.inches, bottom: 0.1.inches, left: 0.2.inches]
            ) {
                voucherList.each { setOfVouchers ->
                    setOfVouchers.each { voucher ->
                        table(columns: [1,2], padding: 3.px, margin: [top: 0.inch, bottom: 0.inch]) {
                            row {
                                cell 'Fecha'
                                cell voucher.date.format('yyyy-MM-dd')
                            }

                            if (voucher.user) {
                                    row {
                                        cell 'A nombre de'
                                        cell "${voucher.user.username}, ${employeeService.getEmployeeCoordinations(voucher.user.employee).name.join(', ')}"
                                    }
                            } else {
                                row {
                                    cell 'A nombre de'
                                    cell voucher.guest.fullName
                                }
                            }

                            row {
                                cell 'Actividad'
                                cell voucher.activity
                            }

                            row {
                                cell 'Valor'
                                cell voucher.value
                            }

                            row {
                                cell 'Alimentos'
                                cell voucherService.getFoodInSpanish(voucher.foods.name).join(', ')
                            }
                        }

                        paragraph(font: [size: 8.pt], margin: [top: 0.inches, bottom: 0.1.inches], align: 'center') {
                            text "VALE DE ALIMENTACION [ #${voucher.id} ]", font: [bold: true]
                            text " - Creado por Orlando Gaitan ${voucher.dateCreated.format('yyyy-MM-dd HH:mm')}, ", font: [size: 7.pt]
                            text "Autorizado por Jorge Rojas ${voucher.approvalDate.format('yyyy-MM-dd HH:mm')}", font: [size: 7.pt]
                        }

                    }

                    pageBreak()
                }
            }
        }

        response.contentType = "application/pdf"
        response.setHeader("Content-disposition", "attachment;filename=test.pdf")
        response.outputStream.flush()
    }

    def filter() {
        Date date = params.date('approvalDate', 'yyyy-MM-dd')
        List<Voucher> vouchers = voucherService.getVouchersByMemberInApprovalDate(params.type, params.long('id'), date)
        def(users, guests) = voucherService.getMembersInApprovalDate(date)

        render view: 'approvedList', model: [
            vouchers: voucherService.getVouchersGroupedByDateAndActivity(vouchers),
            activities: voucherService.getVoucherActivities(),
            guests: guests,
            users: users
        ]
    }

    @Secured(['ROLE_PROTOCOL_SUPERVISOR', 'ROLE_ADMINISTRATIVE_SUPERVISOR'])
    def batchDelete() {
        Integer totalAffected = 0
        String returnPlace = params?.returnPlace
        List<Long> voucherList = params.list('vouchers')*.toLong()

        if (voucherList) {
            totalAffected = voucherService.batchDelete(voucherList)
        }

        flash.message = totalAffected ? "${totalAffected} vales eliminados" : 'Parametros incorrectos'

        if (returnPlace) {
            redirect action: returnPlace, params: [status: 'pending']
        } else {
            redirect action: 'vouchersToApprove'
        }
    }
}

class CreateCommand {
    Date date
    String activity
    String type

    static constraints = {
        date blank: false
        activity blank: false
        type inList: ['user', 'guest']
    }
}
