$(function() {
    $('#location').on('change', function() {
        notify()
    });

    $('#numberOfPeople').on('blur', function() {
        notify()
    });

    function notify() {
        var location = $('#location option:selected');
        var capacity = location.data("capacity")
        var numberOfPeople = $('#numberOfPeople').val();
        var helpBlock = $('#helpBlock');
        var recommendedCapactity = $('#recommendedCapactity');

        if (numberOfPeople.length && capacity !== undefined) {
            if (parseInt(numberOfPeople) > capacity) {
                recommendedCapactity.html(capacity);
                helpBlock.addClass("show");
            } else {
                helpBlock.removeClass("show");
            }
        }
    }

    $('#location, #externalCustomer').select2({
        theme: "bootstrap"
    });
});
