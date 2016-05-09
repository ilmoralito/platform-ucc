$(function() {
    // EVENT
    $('#dateOfTheEvent').datetimepicker({
        format: 'YYYY-MM-DD',
        viewMode: 'days',
        minDate: moment().add(3, 'days')
    });

    // BIRTHDAY
    $('#month').datetimepicker({
        viewMode: 'months',
        format: 'MM/YYYY'
    });
});