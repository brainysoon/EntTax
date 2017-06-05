/**
 * Created by brainy on 17-4-16.
 */

var curCount;   //当前剩余秒数
var maxCount = 35;   //最大等带时间
var InterValObj;    //定时器，用于控制时间

//发送手机验证码 间隔30秒
function sendSmsCode(obj) {

    var ways = $(obj).attr("name");

    //首先判断手机号码格式是否正确
    if (!checkPhoneNum()) {

        return false;
    }

    //Ajax 请求服务器发送短信验证码
    $.ajax({
        url: "/sendsmscode",
        type: "GET",
        async: true,           //关闭异步，这儿需要同步
        data: {
            sphone: $("#sphone").val(),      //传送电话号码
            ways:ways                        //用于区分重置电话号码和通过电话号码重置密码
        },
        timeout: 30000,       //超时时间
        dataType: "json",     //返回的数据类型
        success: function (data) {

            //如果发送成功 则开启定时
            if (data.status) {

                $("#message").text("发送成功注意查收");
            } else {
                $("#message").text("发送失败:" + data.message);
            }
        },
        complete: function (XMLHttpRequest, status) {

            //发送失败
            if (status == "timeout") {
                $("#message").text("发送超时请重发");
            }
        }
    });

    beginTimer();

    return false;
}

//启动定时
function beginTimer() {

    //重置
    curCount = maxCount;

    //设置 Button
    $("#sendcode").attr("disabled", "true");
    $("#sendcode").text(curCount + "后重新发送");

    //开始定时
    InterValObj = window.setInterval(setRemainTime, 1000);
}

//Timer 函数，用户实现定时
function setRemainTime() {

    //定时完成
    if (curCount == 0) {

        window.clearInterval(InterValObj);      //停止定时器
        $("#sendcode").removeAttrs("disabled");     //重启按钮
        $("#sendcode").text("重新发送验证码");          //更新按钮文字

        $("#message").text("");     //重置提示信息
    } else {
        curCount--;
        $("#sendcode").text(curCount + "后重新发送");
    }
}

//发送邮箱验证码 无间隔
function sendEMailCode(obj) {

    var ways = $(obj).attr("name");

    //首先校验邮箱的正确性
    if (!checkEMail()) {

        return false;
    }

    //Ajax 请求服务器发送邮箱验证码
    $.ajax({
        url: "/sendemailcode",
        type: "GET",
        async: true,
        data: {
            semail: $("#semail").val(),
            ways:ways
        },
        timeout: 30000,
        dataType: "json",
        success: function (data) {

            //如果发送成功 则开启定时
            if (data.status) {

                $("#message").text("发送成功注意查收");
            } else {
                $("#message").text("发送失败:" + data.message);
            }
        },
        complete: function (XMLHttpREquest, status) {
            //发送失败
            if (status == "timeout") {
                $("#sendcode").text("发送超时请重发");
            }
        }
    });

    beginTimer();
}

//添加员工
$('#add_staff_button').click(function(){

    if (!checkPhoneNum_addStaff()){
        return false;
    }

    $.ajax({
        url: "/staffs/addstaff",
        type: "POST",
        async: false,
        data: {
            sPhone: $("#sphone").val(),      //传送电话号码
            role:$("#role").val()         //传送角色
        },
        timeout: 30000,       //超时时间
        dataType: "json",     //返回的数据类型
        success: function (data) {

            window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info,{onOk:function () {
                if(data.status==1){
                    location.reload();
                }
            }});
        },
        complete: function (XMLHttpRequest, status) {

            //发送失败
            if (status == "timeout") {
                $("#message").text("发送超时请重发");
            }
        }
    });
});

//删除员工操作
function delete_staff (obj) {

    var group = $(obj).attr("id");

    var txt=  "您确定要删除编号为"+group+"的员工？";
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){

        $.get("/staffs/deletestaff",{sid:group},function(data){

            window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info,{onOk:function () {
                location.reload();
            }});

        });

    }});


}

//触发更新staff模态框的同时调用此方法  -----用于模态框传值
function editInfo(obj) {
    var id = $(obj).attr("id");
    //获取表格中的一行数据
    var sId = document.getElementById("tbody").rows[id].cells[2].innerText;
    var rName = document.getElementById("tbody").rows[id].cells[5].innerText;
    //向模态框中传值
    $('#update_sId').html(sId);
    $('#update_rName').val(rName);
    $('#update_staff_myModal').modal('show');
}


//更新员工操作
function update_staff(){
    //获取模态框数据
    var sId = $('#update_sId').html();
    var rName =$('#update_rName').val();


    $.ajax({
        type: "POST",
        url: "/staffs/updatestaff",
        data: {
            sId:sId,
            rName:rName
        },
        dataType: 'json',
        success: function(data) {

            window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info,{onOk:function () {
                location.reload();

            }});

        }
    });

}
//删除bill数据
function deletebill(obj) {
    var group = $(obj).attr("id");
    var txt=  "您确定要删除项目序号为"+group+"的数据吗？";
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{onOk:function(){

        $.get("/bill/deletebill",{bId:group},function(data){
            window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info,{onOk:function () {
                if (data.status==1){
                    location.reload();
                }
            }});

        });

    }});
}


//触发更新bill模态框的同时调用此方法  -----用于模态框传值
function editbill(obj) {
    var id = $(obj).attr("id");
    //获取表格中的一行数据
    var bId = document.getElementById("tbody").rows[id].cells[1].innerText;
    var bName = document.getElementById("tbody").rows[id].cells[2].innerText;
    var bType = document.getElementById("tbody").rows[id].cells[3].innerText;
    var bPrice = document.getElementById("tbody").rows[id].cells[4].innerText;
    var bMonth = document.getElementById("tbody").rows[id].cells[5].innerText;
    //向模态框中传值
    $('#bId').html(bId);
    $('#bName').val(bName);

    if (bType=="进项数据"){
        $('#bType1').val(bType);
    }else {
        $('#bType2').val(bType);
    }

    $('#bPrice').val(bPrice);
    $('#bMonth').val(bMonth);
    $('#update_bill_myModal').modal('show');
}
//更新 bill 数据
function updatebill() {
    //获取模态框数据
    var bId = $('#bId').html();
    var bName =$('#bName').val();
    var bType =$('input[name="bType"]').filter(':checked').val();
    var bPrice =$('#bPrice').val();
    var bMonth =$('#bMonth').val();


    $.ajax({
        type: "POST",
        url: "/bill/updatebill",
        data: {
            bId:bId,
            bName:bName,
            bType:bType,
            bPrice:bPrice,
            bMonth:bMonth
        },
        dataType: 'json',
        success: function(data) {
            window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.info,{onOk:function () {
                if (data.status==1){
                    location.reload();
                }

            }});
        }
    });

}




// <!--charts-->
// $(function () {
//     // 获取 CSV 数据并初始化图表
//     $.getJSON('https://data.jianshukeji.com/jsonp?filename=csv/analytics.csv&callback=?', function (csv) {
//         $('#hightcharts').highcharts({
//             data: {
//                 csv: csv
//             },
//             title: {
//                 text: '进销项数据分析图'
//             },
//             subtitle: {
//                 text: '数据来源:企业增税DB'
//             },
//             xAxis: {
//                 tickInterval: 7 * 24 * 3600 * 1000, // 坐标轴刻度间隔为一星期
//                 tickWidth: 0,
//                 gridLineWidth: 1,
//                 labels: {
//                     align: 'left',
//                     x: 3,
//                     y: -3
//                 },
//                 dateTimeLabelFormats: {
//                     week: '%Y-%m-%d'
//                 }
//             },
//             yAxis: [{ // 第一个 Y 轴，放置在左边（默认在坐标）
//                 title: {
//                     text: null
//                 },
//                 labels: {
//                     align: 'left',
//                     x: 3,
//                     y: 16,
//                     format: '{value:.,0f}'
//                 },
//                 showFirstLabel: false
//             }, {    // 第二个坐标轴，放置在右边
//                 linkedTo: 0,
//                 gridLineWidth: 0,
//                 opposite: true,  // 通过此参数设置坐标轴显示在对立面
//                 title: {
//                     text: null
//                 },
//                 labels: {
//                     align: 'right',
//                     x: -3,
//                     y: 16,
//                     format: '{value:.,0f}'
//                 },
//                 showFirstLabel: false
//             }],
//             legend: {
//                 align: 'left',
//                 verticalAlign: 'top',
//                 y: 20,
//                 floating: true,
//                 borderWidth: 0
//             },
//             tooltip: {
//                 shared: true,
//                 crosshairs: true,
//                 dateTimeLabelFormats: {
//                     day: '%Y-%m-%d'
//                 }
//             },
//             plotOptions: {
//                 series: {
//                     cursor: 'pointer',
//                     point: {
//                         events: {
//                             // 数据点点击事件
//                             // 其中 e 变量为事件对象，this 为当前数据点对象
//                             click: function (e) {
//                                 $('.message').html(Highcharts.dateFormat('%Y-%m-%d', this.x) + ':<br/>  访问量：' + this.y);
//                             }
//                         }
//                     },
//                     marker: {
//                         lineWidth: 1
//                     }
//                 }
//             }
//         });
//     });
// });
// //柱行图
// $(function () {
//     $.getJSON('//data.jianshukeji.com/jsonp?filename=json/aapl-v.json&callback=?', function (data) {
//         // create the chart
//         $('#diagram').highcharts('StockChart', {
//             chart: {
//                 alignTicks: false
//             },
//             rangeSelector: {
//                 selected: 1
//             },
//             title: {
//                 text: '各项目详细数据图'
//             },
//             series: [{
//                 type: 'column',
//                 name: '各项目详细数据图',
//                 data: data,
//                 dataGrouping: {
//                     units: [[
//                         'week', // unit name
//                         [1] // allowed multiples
//                     ], [
//                         'month',
//                         [1, 2, 3, 4, 6]
//                     ]]
//                 }
//             }]
//         });
//     });
// });

//上传excel数据
function upload_excel() {
    $.get("#",function(data){
        if (data.message){
            alert("更新成功！");
            window.location.reload();
        }else {
            alert("操作失败！")
        }

    });
}




