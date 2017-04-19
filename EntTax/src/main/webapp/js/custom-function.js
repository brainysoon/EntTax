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
        url: "/sendsmscode",
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
        url: "/sendemailcode",
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