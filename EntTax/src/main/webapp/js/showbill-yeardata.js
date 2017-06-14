/**
 * Created by lcyanxi on 17-6-14.
 */
$().ready(function () {
    $.get("/bill/showyearbill",function(data){
        $(function () {
            $('#showdata').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '进销项数据年度统计'
                },
                subtitle: {
                    text: '数据来源:企业增值税DB'
                },
                xAxis: {
                    categories: data.year,
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
                    data: data.inputdata
                }, {
                    name: '销项数据',
                    data: data.outputdata
                }]
            });
        });
    });

});