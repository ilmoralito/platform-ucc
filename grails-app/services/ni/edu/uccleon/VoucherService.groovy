package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class VoucherService {

    List getVouchersByDate(Date date) {
        List<Voucher> vouchers = Voucher.where {
            date >= date && date <= date
        }.list()

        vouchers
    }

    List groupVouchersByDate(List<Voucher> vouchers) {
        vouchers.groupBy { it.date }.collect { a ->
            [date: a.key, size: a.value.size(), status: a.value.status]
        }.sort { it.date }
    }

    List<Voucher> getVouchersByStatus(String status = "pending") {
        Voucher.where {
            status == status
        }.list()
    }
}
