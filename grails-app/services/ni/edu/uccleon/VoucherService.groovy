package ni.edu.uccleon

import grails.config.Config
import grails.util.Environment
import static java.util.Calendar.*
import grails.gorm.DetachedCriteria
import grails.transaction.Transactional
import grails.core.support.GrailsConfigurationAware

@Transactional
class VoucherService implements GrailsConfigurationAware {
    EmployeeService employeeService
    GuestService guestService
    UserService userService

    private enum MONTHS {
        ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE
    }

    List<Map<String, String>> foods

    List<Map<String, String>> getFoods() {
        foods
    }

    List<String> getVoucherActivities() {
        Voucher.executeQuery('SELECT DISTINCT v.activity FROM Voucher AS v')
    }

    List<String> getFoodInSpanish(List<String> foodList) {
        foods.findAll { it.english in foodList }.spanish
    }

    List<Date> getVouchersApprovalDateByYear(final Integer year = 2017) {
        Date fromDate = new Date().clearTime()
        Date toDate = fromDate.clone()

        fromDate.set(year: year, month: 0, date: 1)
        toDate.set(year: year, date: 31, month: 11, hourOfDay: 23, minute: 59, second: 59)

        List result = Voucher.executeQuery( '''
            SELECT DATE(v.approvalDate) AS approvalDate, count(*) AS count
            FROM Voucher AS v
            WHERE v.status = 'approved'
            AND v.approvalDate BETWEEN :fromDate AND :toDate GROUP BY DATE(v.approvalDate)
            ''',
            [fromDate: fromDate, toDate: toDate]
        )

        result.collect {
            [date: it[0], count: it[1]]
        }.groupBy { it.date[MONTH] }.collect {
            [
                monthNumber: it.key,
                month: MONTHS.values()[it.key],
                count: it.value.count.sum(),
                dates: it.value.sort { a, b ->
                    b.date <=> a.date
                }
            ]
        }.sort { -it.monthNumber }
    }

    List<Voucher> getVouchersByApprovalDate(final Date approvalDate) {
        Date fromDate = approvalDate.clearTime()
        Date toDate = fromDate.clone()

        toDate.set(hourOfDay: 23, minute: 59, second: 59)

        Voucher.executeQuery('''
            FROM Voucher as v
            WHERE v.status = 'approved'
            AND v.approvalDate BETWEEN :fromDate AND :toDate''',
        [fromDate: fromDate, toDate: toDate])
    }

    List getMembersInApprovalDate(Date approvalDate) {
        Date fromDate = approvalDate
        Date toDate = fromDate.clone()

        toDate.set(hourOfDay: 23, minute: 59, second: 59)

        List<User> users = Voucher.executeQuery('''
            SELECT v.user FROM Voucher AS v
            WHERE v.approvalDate BETWEEN :fromDate AND :toDate GROUP BY v.user'''
        , [fromDate: fromDate, toDate: toDate])

        List<Guest> guests = Voucher.executeQuery('''
            SELECT v.guest FROM Voucher AS v
            WHERE v.approvalDate BETWEEN :fromDate AND :toDate GROUP BY v.guest'''
        , [fromDate: fromDate, toDate: toDate])

        [users, guests]
    }

    List<Voucher> getVouchersByMemberInApprovalDate(final Map params) {
        String query = ''
        Date fromDate = params.approvalDate
        Date toDate = fromDate.clone()

        toDate.set(hourOfDay: 23, minute: 59, second: 59)

        if (params.type == 'user') {
            query = '''
                FROM Voucher AS v
                WHERE v.user.id = :id
                AND v.approvalDate BETWEEN :fromDate AND :toDate'''
        } else {
            query = '''
                FROM Voucher AS v
                WHERE v.guest.id = :id
                AND v.approvalDate BETWEEN :fromDate AND :toDate'''
        }

        Voucher.executeQuery(query, [id: params.id, fromDate: fromDate, toDate: toDate])
    }

    Integer updateVouchersStatus(List<Long> vouchers, String status) {
        Date approvalDate = status == 'approved' ? new Date() : null

        Integer total = Voucher.where {
            id in vouchers
        }.updateAll(status: status, approvalDate: approvalDate)

        total
    }

    List<Voucher> getVouchersByStatus(String status) {
        Voucher.findAllByStatus(status)
    }

    List<Voucher> getVouchersByDateAndActivity(Date date, String activity) {
        DetachedCriteria query = Voucher.where {
            date == date && activity == activity
        }

        query.list()
    }

    Integer countVouchersByDate(Date date) {
        DetachedCriteria query = Voucher.where {
            date == date
        }

        query.count()
    }

    List getVouchersGroupedByDateAndActivity(List<Voucher> vouchers) {
        List result = vouchers.groupBy { it.date } { it.activity }.collect { a ->
            [
                date: a.key,
                activities: a.value.collect { b ->
                    [
                        name: b.key,
                        vouchers: b.value
                    ]
                }
            ]
        }.sort { it.date }

        result
    }

    List generateSummary() {
        List<Voucher> vouchers = this.getVouchersByStatus('approved')

        List result = vouchers.groupBy { it.date[YEAR] } { it.date[MONTH] }.collect { v ->
            [
                year: v.key,
                summary: v.value.collect { x ->
                    List users = x.value.findAll {
                        it.user != null
                    }

                    List guests = x.value.findAll {
                        it.guest != null
                    }

                    [
                        month: MONTHS.values()[x.key],
                        total: x.value.size(),
                        totalNIO: x.value.value.sum(),
                        totalGuest: guests.size(),
                        totalGuestNIO: guests.value.sum() ?: 0,
                        totalInternal: users.size(),
                        totalInternalNIO: users.value.sum() ?: 0
                        // TODO
                        // totalAcademic: employeeService.countEmployeesByCoordinationLocation(users.user.employee, 'Academic'),
                        // totalAdministrative: employeeService.countEmployeesByCoordinationLocation(users.user.employee, 'Administrative')
                    ]
                }
                // TODO
                // ,
                // results: [
                //     label: 'Totales',
                //     totales: v.value
                // ]
            ]
        }

        result.sort { -it.year }
    }

    Integer batchDelete(List<Long> voucherList) {
        List<Voucher> vouchers = Voucher.getAll(voucherList)

        vouchers*.delete()

        vouchers.size()
    }

    def getValidUsers(final String type, final String activity, final Date date) {
        def members = type == 'user' ? userService.getAllByEnabled() : Guest.findAllByEnabled(true)
        List<Voucher> membersInActivity = this.getVouchersByDateAndActivity(date, activity)[type]

        members - membersInActivity
    }

    void report(Map params) {
        sendMail {
            from params.from
            to Environment.current == Environment.PRODUCTION ? params.to : 'mario.martinez@ucc.edu.ni'
            subject params.subject
            html view: '/emails/voucher/notification', model: [
                total: params.total,
                url: params.url,
                label: params.label
            ]
        }
    }

    List getVouchersSummaryByStatus(final String status = 'canceled') {
        Voucher.executeQuery('''
            SELECT DISTINCT YEAR(v.date) AS year, count(*) AS count
            FROM Voucher AS v
            WHERE v.status = :status
            GROUP BY YEAR(v.date)
            ORDER BY YEAR(v.date) DESC''',
            [status: status]
        )
    }

    List getVouchersSummaryByApprovalDate() {
        Voucher.executeQuery('''
            SELECT DISTINCT YEAR(v.approvalDate) AS year, count(*) AS count
            FROM Voucher AS v
            WHERE v.status = 'approved'
            GROUP BY YEAR(v.approvalDate)
            ORDER BY YEAR(v.approvalDate) DESC'''
        )
    }

    List<Voucher> getByYearAndStatus(final Map params) {
        Voucher.executeQuery('''
            FROM Voucher v
            WHERE year(v.date) = :year AND v.status = :status''',
            [year: params.year, status: params.status]
        )
    }

    List collectList(List list) {
        list.collect {
            [
                year: it[0],
                count: it[1]
            ]
        }
    }

    List groupByMonth(List<Voucher> voucherList) {
        voucherList.groupBy { it.date[MONTH] }.collect { 
            [
                month: MONTHS.values()[it.key],
                count: it.value.size(),
                vouchers: it.value.collect {
                    [
                        id: it.id,
                        member: it.user ? it.user.username : it.guest.fullName,
                        reasonForCancellation: it.reasonForCancellation
                    ]
                }.sort { it.member }
            ]
        }
    }

    List getStats(List<Voucher> voucherList) {
        voucherList.groupBy { it.date[MONTH] }.collect {
            [
                month: MONTHS.values()[it.key],
                count: it.value.size(),
                administrativeAmount: it.value.findAll {
                    it.user
                }.size(),
                academicAmount: it.value.findAll {
                    it.guest
                }.size()
            ]
        }
    }

    @Override
    void setConfiguration(Config co) {
        foods = co.getProperty('ni.edu.uccleon.foods', List)
    }
}
