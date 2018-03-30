$(document).ready(function () {

    $('#parameters').load("paramTI.html");

    //TODO zaznaczony domyślnie radiobutton dla Ti
    $("#modeFrom").on('change', function () {
        var mode = $('input[name=mode]:checked', '#modeFrom').val();
        var contentDiv = $('#parameters');
        if (mode === 'true') {
            contentDiv.load("paramTI.html");
        } else if (mode === 'false') {
            contentDiv.load("paramF.html");
        } else {
            console.log("tif");
        }
    });

    $("#startMeasButton").click(function () {
        $.ajax({
            url: "/startMeasurement",
            type: "POST",
            dataType: 'text',
            contentType: 'application/json',
            cache: false,    //This will force requested pages not to be cached by the browser
            processData: false, //To avoid making query String instead of JSON
            // data: JSON.stringify(params),
            success: function (data) {
                $("#resultValue").text(data);
                console.log("startMeasurement: " + data);
            },
            error: function (e) {
                $("#resultValue").text("Wrong value: " + e);
            }
        })
    });

});




