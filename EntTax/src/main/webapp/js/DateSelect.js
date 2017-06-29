$(function () {
    var dayTime = 0;
    years();
    months();
    days();

    $('#year').val("--");
    $('#month').val("--");
    $('#day').val("--");

    $('#year').change(function () {
        months();
        days();
    })
    $('#month').change(function () {
        days();
    });
    function years() {
        var dates = new Date();
        var nowYear = dates.getFullYear();
        for (i = nowYear; i >= 1968; i--) {
            var str = "<option value=\"" + i + "\">" + i + "</option>";
            $('#year').append(str);
        }
    }

    function months() {
        $('#month').empty();
        for (i = 1; i <= 12; i ++) {
            var str = "<option value=\"" + i + "\">" + i + "</option>";
            $('#month').append(str);
        }
    }
    function days() {
        $('#day').empty();
        if(parseInt($('#month').val())===1||parseInt($('#month').val())===3||parseInt($('#month').val())===5||parseInt($('#month').val())===7||parseInt($('#month').val())===8||parseInt($('#month').val())===10||parseInt($('#month').val())===12){
            dayTime = 31;
        }
        else if (parseInt($('#month').val())===4||parseInt($('#month').val())===6||parseInt($('#month').val())===9||parseInt($('#month').val())===11){
            dayTime = 30;
        }
        else {
            if (parseInt($("#year").val()) % 400 === 0 || (parseInt($("#year").val()) % 4 === 0 && parseInt($("#year").val()) % 100 !== 0)) {
                dayTime = 29;
            }
            else {
                dayTime = 28;
            }
        }
        for(i = 1; i<=dayTime;i++){
            var str = "<option value=\"" + i + "\">" + i + "</option>";
            $('#day').append(str);
        }
    }
})