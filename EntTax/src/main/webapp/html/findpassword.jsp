<%--
  Created by IntelliJ IDEA.
  User: lcyanxi
  Date: 17-3-22
  Time: 下午5:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>findpassword</title>
    <script type="text/javascript" src="<c:url value="/jcrop/js/jquery.min.js"/>"></script>
</head>
<body>
      电话号码 ：<input type="text" name="phone" id="phone" maxlength="11"><br>
      图片验证码: <input type="text" id="code" name="code" class="text" maxlength="6" onclick="findByPhone()"/>
              <img id="captchaImage" src="captcha.form"/><br><br>
              <input type="text" name="numcode" id="numcode" maxlength="6">
              <input type="button" value="获取短信验证码" onclick="getSMSCode()">
             <br> <br>
              <input type="button" value="提交" onclick="getvalidaSMS()">

</body>

<script type="text/javascript">
    // 更换验证码
    $('#captchaImage').click(function()
    {
        $('#captchaImage').attr("src", "captcha.form?timestamp=" + (new Date()).valueOf());
    });

    function getSMSCode(){
        $.get("sendSMS.from?phone="+$("#phone").val()+"",null,function (data) {
        });
    }
    function getvalidaSMS() {
        var code=$("#code").val();
        var  numcode=$("#numcode").val();
        $.get("validacode.from",{code:code,numcode:numcode},function(data){
            if (data.status==1){
                window.location.href="updatepassword.from";
            }else {
                alert("你的验证码有误！");
            }
        });
    }

function  findByPhone() {
    $.get("user/findphone.from",{phone:$("#phone").val()},function(data){
        if (data.status==0){
            alert("电话号码不存在！");
        }
    });
}
//
//    function getvalidaSMS() {
//        alert("aaa");
//    data:"name="+$("#name").val(),
//        $.ajax({
//            date: {code:$("#code").val(), numcode:$("#numcode").val()},
//            dataType: "JSON",
//            contentType: "application/json; charset=utf-8",
//            async: true,
//            url: "validacode.from",
//            success: function (data) {
//               alert(data);
//            }
//        });
//    }

</script>
</html>
