$(function() {
    $('#numberOfPeople').on('blur', function() {
        var self = $(this);
        var value = self.val();
        var location = $('#location option:selected');
        var capacity = location.data("capacity")
        var alert = $('#numberOfPeopleAlert');

        if (value.length && capacity !== undefined) {
            if (parseInt(value) > capacity) {
                alert.addClass("show");
            } else {
                alert.removeClass("show");
            }
        }
    });
});

