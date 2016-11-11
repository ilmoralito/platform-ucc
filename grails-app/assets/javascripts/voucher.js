(function() {
    $('#trigger').on('change', function() {
        var _this = $(this);
        var checkboxes = $('tbody').find(':checkbox');

        if (_this.is(':checked')) {
            checkboxes.prop('checked', true);
        } else {
            checkboxes.prop('checked', false);
        }
    });

    $('#voucherDate').datetimepicker({
        format: 'YYYY-MM-DD',
        useCurrent: false
    });
})();
