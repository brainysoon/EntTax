<%--
  Created by IntelliJ IDEA.
  User: lcyanxi
  Date: 17-3-22
  Time: 下午6:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>updatepassword</title>
    <script type="text/javascript" src="<c:url value="/jcrop/js/jquery.min.js"/>"></script>
</head>
<body>
    <h1>更新密码页面</h1>
     输入新密码：<input type="password" name="password" id="password"><br>
     <input type="button" value="提交" onclick="updatepassword()">
</body>


<script type="text/javascript">
    function updatepassword() {
        var newpassword=$("#password").val();
        alert(newpassword);
        $.get("user/updatepassword.from",{password:newpassword},function(data){
            if (data.status==1){
                window.location.href="main.from";
            }else {
                alert("密码设置失败！");
            }

        });
    }
</script>
</html>
