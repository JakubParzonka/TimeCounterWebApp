$(document).ready(function () {
    var writeFirst = function () {
        $.ajax({
            type: "POST",
            url: "/writeFirst",
            dataType: 'text',
            timeout: 600000,
            success: function (data) {
                $("#buttonFirst").attr("value", data);
                console.log(data);
            },
            error: function (e) {
                console.log("Error during writeFirst calling => " + e.toString());
            }
        });
    };
    var startAndReset = function () {
        $.ajax({
            type: "POST",
            url: "/startAndReset",
            dataType: 'text',
            timeout: 600000,
            success: function (data) {
                $("#buttonStart").attr("value", data);
                console.log(data);
            },
            error: function (e) {
                console.log("Error during starting => " + e.toString());
            }
        });
    };
    var writeSecond = function () {
        $.ajax({
            type: "POST",
            url: "/writeSecond",
            dataType: 'text',
            timeout: 600000,
            success: function (data) {
                $("#buttonFirst").attr("value", data);
                console.log(data);
            },
            error: function (e) {
                console.log("Error during writeSecond calling => " + e.toString());
            }
        });
    };
    var readData = function () {
        $.ajax({
            type: "POST",
            url: "/readData",
            dataType: 'text',
            timeout: 600000,
            success: function (data) {
                $("#dataFromCounter").attr("value", data);
                console.log(data);
            },
            error: function (e) {
                console.log("Error during readData calling => " + e.toString());
            }
        });
    };


});
