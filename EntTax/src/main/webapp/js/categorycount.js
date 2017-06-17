/**
 * Created by lcyanxi on 17-6-17.
 */

years();
/*用于实现年的选择*/
function years() {
    var y = document.getElementById('year');
    var dates = new Date();
    var nowYear = dates.getFullYear();
    for (i = nowYear; i >= 1995; i--) {
        //用于打印输出年的范围，以下类同
        var str = "<option value=\"" + i + "\">" + i + "年"+"</option>";
        y.innerHTML +=str;
    }
}


//填充项目名称下拉列表
bNames();
function bNames() {

    var bNameinput = document.getElementById('bNameinput');
    var bNameoutput = document.getElementById('bNameoutput');
    $.get("/bill/showbnames",function(data){
        //进项数据名称
        $.each(data.inputnames, function(index,item){
            var str = "<option value=\"" + item + "\">"+ "进项："+item+"</option>";
            bNameinput.innerHTML +=str;
        });
        //销项数据名称
        $.each(data.outputnames, function(index,item){
            var str = "<option value=\"" + item + "\">"+ "销项："+item+"</option>";
            bNameoutput.innerHTML +=str;
        });
    });
}



$('#categorybutton').click(function(){
       categoryCountBill();
});

$(document).ready(function(){
    categoryCountBill();
});
function categoryCountBill() {
    $.ajax({
        url: "/bill/showcategorybill",
        type: "POST",
        async: false,
        data: {
            year: $("#year").val(),      //传送电话号码
            inputbName:$("#bNameinput").val(),
            outputbName:$("#bNameoutput").val()
        },
        timeout: 30000,       //超时时间
        dataType: "json",     //返回的数据类型
        success: function (data) {
            $('#showdata').highcharts({
                title: {
                    text: '进销项数据分类统计',
                    x: -20
                },
                subtitle: {
                    text: '数据来源: 企业增值税DB',
                    x: -20
                },
                xAxis: {
                    categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
                },
                yAxis: {
                    title: {
                        text: '金额 (万元)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '万元'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '进项数据',
                    data: data.inputdata
                }, {
                    name: '销项数据',
                    data:data.outputdata
                }]
            });

        },
        complete: function (XMLHttpRequest, status) {

            //发送失败
            if (status == "timeout") {
                window.wxc.xcConfirm("请求超时,请重新操作！", window.wxc.xcConfirm.typeEnum.info);
            }
        }
    });

}
