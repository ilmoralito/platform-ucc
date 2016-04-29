//= require /jquery/dist/jquery.min.js
//= require /mustache.js/mustache.min.js
//= require /moment/min/moment.min.js
//= require /bootstrap/dist/bootstrap.min.js
//= require /eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js
//= require_tree .
//= require_self

$(function() {
    $('#dateOfTheEvent').datetimepicker({
        format: 'YYYY-MM-DD',
        viewMode: 'days',
        minDate: moment().add(3, 'days')
    });

    $('#employee').on('change', function() {
        var optionSelected = $('option:selected', this),
            data = optionSelected.data().data,
            template = $('#template').html();

        Mustache.parse(template);

        var rendered = Mustache.render(template, data);

        $('#target').html(rendered);
    });

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
})
