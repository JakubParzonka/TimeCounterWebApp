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


    $("#modeFrom").on('change', function () {
        var mode = $('input[name=mode]:checked', '#modeFrom').val();
        console.log(mode);
        if (mode === 'true') {
            $('#content').replaceWith("paramTi.html");
            console.log("ti");
        } else if (mode === 'false') {
            $('#content').replaceWith();
            console.log("f");
        } else {
            console.log("tif");
        }
    });


});




