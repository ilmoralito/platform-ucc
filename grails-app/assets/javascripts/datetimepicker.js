$(function() {
    // EVENT
    $('#date').datetimepicker({
        format: 'YYYY-MM-DD',
        viewMode: 'days',
        useCurrent: false,
        minDate: moment().add(3, 'days')
    });

    // BIRTHDAY
    $('#month').datetimepicker({
        viewMode: 'months',
        format: 'MM/YYYY'
    });
});