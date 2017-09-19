
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>权限管理主页</title>
    <link rel="stylesheet" type="text/css" href="../../css/css.css" />
    <link href="../../css/page.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="../../js/jquery-1.10.0.js"></script>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/page.js"></script>
</head>

<body>
<input id="state" type="hidden" value="${state}" />
<div id="pageAll">

    <div class="page">
        <!-- topic页面样式 -->
        <div class="topic">
            <div class="conform">

            </div>
            <!-- 表格 显示 -->
            <div class="conShow">
                <center>
                    <table id="tbUser" border="1" cellspacing="0" cellpadding="0" >



                    </table></center>
                <%--<div class="paging"><ul class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul></div>--%>
            </div>
            <!-- 表格 显示 end-->
        </div>
        <!-- 页面样式end -->
    </div>

</div>


<script type="text/javascript">
    <%--数据初始化--%>
    $(document).ready(function () {
        $.ajax({
            url:"/data/allRoot.json",
            dataType:"json",
            success:function (data) {
                loadData(data);
            }
        })
    })

    function loadData(data) {
        $.each(data,function (i,val) {
            $("table").append("<tr id='trUserList' class='trList'>"
                + "<td class='tdChild'>" + val.root[0].roleName +"</td>"
//                + "<td>" + val.tname + "</td>"
                + "</a></td>");

        })
    }



</script>

</body>

</html>