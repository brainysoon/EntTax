/**
 * Created by lcyanxi on 17-6-9.
 */


var myDate = new Date();
var year = myDate.getFullYear();

$().ready(function () {
    showbill (year);

});

$(document).ready(function(){
    $("select").change(function(){
        var a = $(this).val();
        showbill (a);
    });
});


function showbill (year){
    $.get("/bill/showmonthbill",{year:year},function(data){
        $('#showdata').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: myDate.getFullYear()+'进销项数据月度统计'
            },
            subtitle: {
                text: '数据来源:企业增值税DB'
            },
            xAxis: {
                categories: [
                    '一月',
                    '二月',
                    '三月',
                    '四月',
                    '五月',
                    '六月',
                    '七月',
                    '八月',
                    '九月',
                    '十月',
                    '十一月',
                    '十二月'
                ],
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: '金额 (万元)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} 万元</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '进项数据',
                data: data.input
            }, {
                name: '销项数据',
                data: data.output
            }]
        });

    });
}