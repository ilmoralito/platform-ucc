$(function() {
    $('#startTime').on('change', function() {
        setTime()
    });

    setTime();

    function setTime() {
        var offset = parseInt($('#startTime').find('option:selected').val(), 10),
            endingTime = $('#endingTime'),
            range = [
                {time: 9, value: "9:00"},
                {time: 10, value: "10:00"},
                {time: 11, value: "11:00"},
                {time: 12, value: "12:00"},
                {time: 13, value: "1:00"},
                {time: 14, value: "2:00"},
                {time: 15, value: "3:00"},
                {time: 16, value: "4:00"},
                {time: 17, value: "5:00"}
            ];

        endingTime.find('option').remove();

        for (var i = 0; i < range.length; i++) {
            if (range[i].time > offset) {
                var option = $('<option>', { value: range[i].time, text: range[i].value });

                endingTime.append(option);
            }
        }
    }
});
