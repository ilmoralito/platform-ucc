var origin = window.location.host == 'localhost:8181' ? 'http://localhost:9090' : 'http://192.168.7.254/uapi'
var url = origin + '/copies/getDocumentDescriptionByCoordination';

$('#copyType').on('change', function() {
    var description = $('#description');

    if (this.value === 'extraCopy') {
        description.parent().removeClass('hide');
    } else {
        description.parent().addClass('hide');
    }
});

$('#coordination').on('change', function() {
    var coordinationID = this.value;

    $.ajax({
        url: url,
        method: 'GET',
        dataType: 'json',
        data: {
            coordinationID: coordinationID
        },
        success: function(data) {
            var documentDescriptionList = $('#documentDescriptionList');

            documentDescriptionList.empty();

            $.each(data, function(index, element) {
                $('<option/>', { value: element }).appendTo(documentDescriptionList);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    })
});
