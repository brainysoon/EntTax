/**
 * Created by brainy on 17-6-19.
 */

$().ready(function () {
    var a = $("#taxout_key").val();
    var b = $("#taxout_month").val();
    showlinear(a, b);
});

//下拉选择年份按钮
$(document).ready(function () {
    $("select").change(function () {
        var a = $("#taxout_key").val();
        var b = $("#taxout_month").val();
        showlinear(a, b);
    });
});

function showlinear(key, count) {

    //异步加载
    $.get("/ai/linearreg", {key: key, count: count}, function (data) {

        $('#diagram').highcharts({
            xAxis: {
                categories: [
                    '前六月',
                    '前五月',
                    '前四月',
                    '前三月',
                    '前二月',
                    '前一月',
                    '预测月'
                ]
            },
            yAxis: {
                min: 0,
                title: {
                    text: '金额 (万元)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.x.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} 万元</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            title: {
                text: '销项数据线性回归线预测'
            },
            subtitle: {
                text: '数据来源:企业增值税DB'
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                type: 'line',
                name: '线性回归线',
                data: data.line,
                marker: {
                    enabled: false
                },
                states: {
                    hover: {
                        lineWidth: 0
                    }
                },
                enableMouseTracking: false
            }, {
                type: 'scatter',
                name: '销项数据',
                data: data.series,
                marker: {
                    radius: 4
                }
            }, {
                type: 'scatter',
                name: '预测数据',
                data: data.point,
                marker: {
                    radius: 5
                }
            }]
        });
    });
}