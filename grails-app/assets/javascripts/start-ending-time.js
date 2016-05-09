$('#startTime').on('change', function() {
    var opt = $("option:selected", this);
    var next = opt.next().text();
    var endingTime = $('#endingTime');

    endingTime.find('option').each(function() {
        var $this = $(this);

        if ($this.text() == next) {
            $this.attr('selected', 'selected');
        } else {
            $this.removeAttr('selected');
        }
    })
});
