
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="<c:url value="/jcrop/js/jquery.min.js"/>"></script>
</head>
<body>
<h2>登录界面</h2>
<form>
    用户名：<input type="text" name="sname" id="sname" width="10" maxlength="6"><br><br>
    密 码 ：<input type="password" name="spassword" id="spassword" width="10" maxlength="10"><br>
    验证码：<input type="text" name="kcode" id="kcode" width="5" maxlength="6">
          <img id="captchaImage" src="captcha.form"/><br><br>
          <input type="button" value="登 录" onclick="login()">
         <a href="findpassword.from">
          <input type="button" value="忘记密码">
         </a>
</form>

<h2>文件下载</h2>
<a href="downloadExcelModel.from">excel下载</a>

<h2>文件上传</h2>
<form id="fileUpload" action="uploadExcelDate.from" enctype="multipart/form-data" method="post">
    <input id="excelFile" name="excelFile" type="file"/>
    <input type="button" value="提交" onclick="submitExcel()"/>
</form>
</body>
<script type="text/javascript">
    // 更换验证码
    $('#captchaImage').click(function()
    {
        $('#captchaImage').attr("src", "captcha.form?timestamp=" + (new Date()).valueOf());
    });
    function login() {
        $.get("user/login.from",{sname:$("#sname").val(),spassword:$("#spassword").val(),kcode:$("#kcode").val()},
            function (data) {
            if (data.status==100){
                window.location.href="main.from";
            }else {
                alert(data.message);
            }
        });
    }



    function submitExcel(){
        var excelFile = $("#excelFile").val();
        if(excelFile=='') {alert("请选择需上传的文件!");return false;}
        if(excelFile.indexOf('.xls')==-1){
            alert("文件格式不正确，请选择正确的Excel文件(后缀名.xls或者xlsx)！");
            return false;
        }
        $("#fileUpload").submit();
    }
    //   function login(){
//       alert("aaa");
//
//        $.ajax({
//            type:"POST",
//            date: {
//                sname:$("#sname").val(),
//                spassword:$("#spassword").val(),
//                kcode:$("#kcode").val()
//            },
//            dataType: "JSON",
//            contentType: "application/json; charset=utf-8",
//            async: true,
//            url: "user/login.from",
//            success: function (data) {
////                if (data.status==100){
////                    window.location.href="main.from";
////                }else {
////                    alert(data.message);
////                }
//            },
//            error:function () {
//                alert("网络延迟！");
//            },
//        });
//   }

</script>
</html>
