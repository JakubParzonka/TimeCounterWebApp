$(document).ready(function () {

    $("#confirmSconfig").click(function () {
        console.log("startInput => " + $('input[name=startInput]:checked', '#startInputForm').val());
        console.log("startSlote => " + $('input[name=startSlote]:checked', '#startSloteForm').val());
        console.log("stopInput => " + $('input[name=stopInput]:checked', '#stopInputForm').val());
        console.log("stopSlote => " + $('input[name=stopSlote]:checked', '#stopSloteForm').val());
        console.log("clockInteral => " + $('input[name=clockInteral]:checked', '#clockInteralForm').val());
        console.log("calibrated => " + $('input[name=calibrated]:checked', '#calibratedForm').val());
        console.log("enEnd => " + $('input[name=enEnd]:checked', '#enEndForm').val());
    });

    // clearing modes page on start
    $('#content').empty();

    $("#modeFrom").on('change', function () {
        var mode = $('input[name=mode]:checked', '#modeFrom').val();
        var contentDiv = $('#content');
        if (mode === 'true') {
            contentDiv.load("paramTi.html");
        } else if (mode === 'false') {
            contentDiv.load("paramF.html");
        } else {
            console.log("tif");
        }
    });

    $("p").click(function () {
        var htmlString = $(this).html();
        $(this).text(htmlString);
    });
});




