/**
 * Created by brainy on 17-4-16.
 */

var curCount;   //当前剩余秒数
var maxCount = 35;   //最大等带时间
var InterValObj;    //定时器，用于控制时间

//发送手机验证码 间隔30秒
function sendSmsCode() {

    //首先判断手机号码格式是否正确
    if (!checkPhoneNum()) {

        return false;
    }

    //Ajax 请求服务器发送短信验证码
    $.ajax({
        url: "/sendsmscodetoupdate",
        type: "GET",
        async: true,           //关闭异步，这儿需要同步
        data: {
            sphone: $("#sphone").val()      //传送电话号码
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
function sendEMailCode() {

    //首先校验邮箱的正确性
    if (!checkEMail()) {

        return false;
    }

    //Ajax 请求服务器发送邮箱验证码
    $.ajax({
        url: "/sendemailcodetoupdate",
        type: "GET",
        async: true,
        data: {
            semail: $("#semail").val()
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

//删除员工操作
function delete_staff (sid) {
    alert(sid);
    $.get("user/deletestaff",{sid:sid},function(data){
        if (data.message){
            alert("删除成功！");
            window.location.reload();
        }else {
            alert("操作失败！")
        }

    });
}
//更新员工操作
function update_staff(sid,role){
    alert(sid+role);
    $.get("user/updatestaff",{sid:sid,role:role},function(data){
        if (data.message){
            alert("更新成功！");
            window.location.reload();
        }else {
            alert("操作失败！")
        }

    });
}


<!--charts-->
$(function () {
    // 获取 CSV 数据并初始化图表
    $.getJSON('https://data.jianshukeji.com/jsonp?filename=csv/analytics.csv&callback=?', function (csv) {
        $('#hightcharts').highcharts({
            data: {
                csv: csv
            },
            title: {
                text: '进销项数据分析图'
            },
            subtitle: {
                text: '数据来源:企业增税DB'
            },
            xAxis: {
                tickInterval: 7 * 24 * 3600 * 1000, // 坐标轴刻度间隔为一星期
                tickWidth: 0,
                gridLineWidth: 1,
                labels: {
                    align: 'left',
                    x: 3,
                    y: -3
                },
                dateTimeLabelFormats: {
                    week: '%Y-%m-%d'
                }
            },
            yAxis: [{ // 第一个 Y 轴，放置在左边（默认在坐标）
                title: {
                    text: null
                },
                labels: {
                    align: 'left',
                    x: 3,
                    y: 16,
                    format: '{value:.,0f}'
                },
                showFirstLabel: false
            }, {    // 第二个坐标轴，放置在右边
                linkedTo: 0,
                gridLineWidth: 0,
                opposite: true,  // 通过此参数设置坐标轴显示在对立面
                title: {
                    text: null
                },
                labels: {
                    align: 'right',
                    x: -3,
                    y: 16,
                    format: '{value:.,0f}'
                },
                showFirstLabel: false
            }],
            legend: {
                align: 'left',
                verticalAlign: 'top',
                y: 20,
                floating: true,
                borderWidth: 0
            },
            tooltip: {
                shared: true,
                crosshairs: true,
                dateTimeLabelFormats: {
                    day: '%Y-%m-%d'
                }
            },
            plotOptions: {
                series: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            // 数据点点击事件
                            // 其中 e 变量为事件对象，this 为当前数据点对象
                            click: function (e) {
                                $('.message').html(Highcharts.dateFormat('%Y-%m-%d', this.x) + ':<br/>  访问量：' + this.y);
                            }
                        }
                    },
                    marker: {
                        lineWidth: 1
                    }
                }
            }
        });
    });
});
//柱行图
$(function () {
    $.getJSON('//data.jianshukeji.com/jsonp?filename=json/aapl-v.json&callback=?', function (data) {
        // create the chart
        $('#diagram').highcharts('StockChart', {
            chart: {
                alignTicks: false
            },
            rangeSelector: {
                selected: 1
            },
            title: {
                text: '各项目详细数据图'
            },
            series: [{
                type: 'column',
                name: '各项目详细数据图',
                data: data,
                dataGrouping: {
                    units: [[
                        'week', // unit name
                        [1] // allowed multiples
                    ], [
                        'month',
                        [1, 2, 3, 4, 6]
                    ]]
                }
            }]
        });
    });
});




