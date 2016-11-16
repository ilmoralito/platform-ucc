//= require /jquery/dist/jquery.min.js
//= require /bootstrap/dist/js/bootstrap.min.js
//= require /mustache.js/mustache.min.js
//= require /moment/min/moment.min.js
//= require /eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js
//= require /autosize/dist/autosize.min.js
//= require /select2/dist/js/select2.min.js
//= require_tree .
//= require_self

$(function() {
    $('#employee, #employees').select2({
        theme: "bootstrap"
    });

    autosize($('#observation, #activity'));

    $('#employee').on('change', function() {
        var optionSelected = $('option:selected', this),
            data = optionSelected.data("information"),
            template = $('#template').html();

            console.log(data);

        Mustache.parse(template);
        var rendered = Mustache.render(template, data);

        $('#target').html(rendered);
    });
});
