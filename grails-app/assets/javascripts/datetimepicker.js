$(function() {
    // EVENT
    $('#date').datetimepicker({
        format: 'YYYY-MM-DD',
        viewMode: 'days',
        useCurrent: false,
        minDate: moment().add(3, 'days')
    });

    // VOUCHER
    $('#voucherDate').datetimepicker({
        format: 'YYYY-MM-DD',
        useCurrent: false,
        minDate: moment().add(-1, 'days')
    });

    // BIRTHDAY
    $('#month').datetimepicker({
        viewMode: 'months',
        format: 'MM/YYYY'
    });
});