$(document).ready(function () {

    $('#content').load("paramTI.html");

    //TODO zaznaczony domyślnie radiobutton dla Ti
    $("#modeFrom").on('change', function () {
        var mode = $('input[name=mode]:checked', '#modeFrom').val();
        var contentDiv = $('#content');
        if (mode === 'true') {
            contentDiv.load("paramTI.html");
        } else if (mode === 'false') {
            contentDiv.load("paramF.html");
        } else {
            console.log("tif");
        }
    });
});




