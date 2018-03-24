$(document).ready(function () {

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

});




