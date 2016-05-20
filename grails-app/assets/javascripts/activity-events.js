$(function() {
    $('#numberOfPeople').on('blur', function() {
        var self = $(this);
        var value = self.val();
        var location = $('#location option:selected');
        var capacity = location.data("capacity")
        var helpBlock = $('#helpBlock');
        var recommendedCapactity = $('#recommendedCapactity');

        if (value.length && capacity !== undefined) {
            if (parseInt(value) > capacity) {
                recommendedCapactity.html(capacity);
                helpBlock.addClass("show");
            } else {
                helpBlock.removeClass("show");
            }
        }
    });
});
