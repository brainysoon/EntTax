/**
 * Created by brainy on 17-6-19.
 */

$().ready(function () {
    showlinear(0);
});

//下拉选择年份按钮
$(document).ready(function () {
    $("select").change(function () {
        var a = $(this).val();
        showbill(a);
    });
});

function showlinear(key) {

    //异步加载
    $.get("/ai/linearreg", {key: key}, function () {

        $('#diagram').highcharts({
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
                data: [[0, 1.11], [5, 4.51]],
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
                data: [1, 1.5, 2.8, 3.5, 3.9, 4.2],
                marker: {
                    radius: 4
                }
            }, {
                type: 'scatter',
                name: '预测数据',
                chart: [2],
                data: [[3, 2]],
                marker: {
                    radius: 5
                }
            }]
        });
    });
}