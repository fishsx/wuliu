
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="../../css/public.css" />
    <link rel="stylesheet" type="text/css" href="../../css/css.css"/>
    <script type="text/javascript" src="../../js/jquery-1.10.0.js"></script>
    <script type="text/javascript" src="../../js/jquery.validate-1.13.1.js"></script>

</head>
<body >

<div id="pageAll">

    <div class="page ">
        <form id="logForm" action="/user/updatePwd.do" method="post">
            <input id="uid" type="text" hidden value="${sessionScope.user.uid}" name="uid"/>
            <div class="bacen">
                <div class="bbD">
                    &nbsp;&nbsp;&nbsp;&nbsp;管理员ID：&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.user.uid}</div>
                <div class="bbD">管理员用户名：&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.user.tname}</div>
                <div class="bbD">
                    &nbsp;&nbsp;&nbsp;&nbsp;输入旧密码：<input type="password" class="input3" name="oldpwd" id="oldpwd"/>
                </div>
                <div class="bbD">
                    &nbsp;&nbsp;&nbsp;&nbsp;输入新密码：<input type="password" class="input3" name="pwd"  />
                </div>
                <div class="bbD">
                    再次确认密码：<input type="password" class="input3"  name="pwd2" />
                </div>
                <div class="bbD">
                    <p class="bbDP">
                        <%--<input type="submit" class="btn_yes btn_no" value="确认修改"/>--%>
                        <button class="btn_ok" onclick="check()">确认修改</button>
                    </p>
                </div>
            </div>
        </form>

    </div>
</div>

</body>

<script type="text/javascript" >




   $(document).ready(function () {
       $("#logForm").validate({
           rules:{
               oldpwd:{
                   required:true,
                   remote: {
                       url: "/data/checkPwd.json",
                       type: "Post",
                       data: {
                           oldpwd: function () { return $("#oldpwd").val(); },
                           uid:function(){return $("#uid").val();}
                       }
                   }
               },
               pwd:{
                   required:true

               },
               pwd2:{
                   required:true
               }

           }, messages:{
               oldpwd:{
                   remote:"密码错误"
                  }



           }
       })
   })


</script>
</html>
