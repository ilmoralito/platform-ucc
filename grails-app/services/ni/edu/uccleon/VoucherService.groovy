package ni.edu.uccleon

import grails.config.Config
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
        Voucher.executeQuery('SELECT DISTINCT v.activity FROM Voucher as v')
    }

    List<String> getFoodInSpanish(List<String> foodList) {
        foods.findAll { it.english in foodList }.spanish
    }

    // TODO: Improve this code avoid using unqiue etc...
    List<Date> getVouchersApprovalDates() {
        DetachedCriteria query = Voucher.where {
            approvalDate != null
        }.projections {
            groupProperty 'approvalDate'
        }

        query.list()*.clearTime().unique().sort().reverse()
    }

    List<Voucher> getVouchersByApprovalDate(Date approvalDate) {
        Date fromDate = approvalDate.clearTime()
        Date toDate = fromDate.clone()

        toDate.set(hourOfDay: 23, minute: 59, second: 59)

        Voucher.findAll('''
            FROM Voucher as v
            WHERE v.approvalDate
            BETWEEN :fromDate AND :toDate''',
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

    List<Voucher> getVouchersByMemberInApprovalDate(final String type, final Long id, final Date approvalDate) {
        String query = ''
        Date fromDate = approvalDate
        Date toDate = fromDate.clone()

        toDate.set(hourOfDay: 23, minute: 59, second: 59)

        if (type == 'user') {
            query = 'FROM Voucher as v WHERE v.user.id = :id AND v.approvalDate BETWEEN :fromDate AND :toDate'
        } else {
            query = 'FROM Voucher as v WHERE v.guest.id = :id AND v.approvalDate BETWEEN :fromDate AND :toDate'
        }

        Voucher.executeQuery(query, [id: id, fromDate: fromDate, toDate: toDate])
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
        def members = type == 'user' ? userService.getAllByEnabled() : guestService.getAllByEnabled()
        List<Voucher> membersInActivity = this.getVouchersByDateAndActivity(date, activity)[type]

        members - membersInActivity
    }

    @Override
    void setConfiguration(Config co) {
        foods = co.getProperty('ni.edu.uccleon.foods', List)
    }
}
