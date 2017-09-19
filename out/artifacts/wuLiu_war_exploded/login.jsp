
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/login.css" />
    <link rel="stylesheet" type="text/css" href="css/css.css"/>
    <script type="text/javascript" src="js/jquery-1.10.0.js"></script>
    <script type="text/javascript" src="js/jquery.validate-1.13.1.js"></script>

</head>
<body >


<div class="logDiv">
    <img class="logBanner" src="img/bg.png" />
    <div class="logGet">
        <!-- 头部提示信息 -->
        <div class="logD logDtip">
            <p class="p1">登录</p>
        </div>
        <!-- 输入框 -->
        <form id="logForm" name="userlogin" action="/user/loginCheck" method="post" >
            <div class="lgD">
                <img class="img1" src="img/logName.png" />
                <input id="uname" type="text" name="uname" placeholder="输入用户名" />
            </div>
            <div class="lgD">
                <img class="img1" src="img/logPwd.png" />
                <input id="upwd" type="password" name="upwd" placeholder="输入用户密码" />
            </div>

            <div class="mybbD">
                <select id="role" class="sel" name="role_id"></select>
            </div>
            <div class="lgD logD2">
                <div class="logC">
                    <button id="btnLogin" >登 录</button>
                </div>
            </div>

        </form>

    </div>
</div>


</body>

<script type="text/javascript" >
    $(document).ready(function () {
        $.ajax({
            url:"/data/allRole.json",
            dataType:"json",
            success:function (data) {
                $.each(data,function (index,val) {
                    $("#role").append("<option value = " + val["roleRoot"] + ">" + val["roleName"] + "</option>");

                })
            }
        })
    })


    $(document).ready(function () {
        $("#myForm").validate({
            rules:{
                uname:{
                    required:true
                },
                upwd:{
                    required:true
                }

            }
        })
    })

</script>
</html>
