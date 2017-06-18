/**
 * Created by lcyanxi on 17-6-18.
 */


years();
/*用于实现年的选择*/
function years() {
    var y = document.getElementById('year');
    var dates = new Date();
    var nowYear = dates.getFullYear();
    for (i = nowYear; i >= 1995; i--) {
        //用于打印输出年的范围，以下类同
        var str = "<option value=\"" + i + "\">" + i + "年" + "</option>";
        y.innerHTML += str;
    }
}

//下拉选择年份按钮
$(document).ready(function () {
    $("select").change(function () {
        var year = $(this).val();
        ratecount(year);
    });
});

$(function () {
    var year = $("#year").val()
    ratecount(year)
});

function ratecount(year) {
    $.get("/bill/showratebill", {year: year}, function (data) {

        $('#showdata').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: year+'年进销项各类数据比例图'
            },
            subtitle: {
                text: '《《左：进项数据----------右：销项数据》》',
                x: -20,
            },
            tooltip: {
                headerFormat: '{series.name}<br>',
                pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '进项数据各类占比',
                center: [300, null],
                size: 350,
                data: data.inputdata
            },
                {
                    type: 'pie',
                    name: '销项数据各类占比',
                    center: [980, null],
                    size: 350,
                    data: data.outputdata
                }],
        });
    });

}