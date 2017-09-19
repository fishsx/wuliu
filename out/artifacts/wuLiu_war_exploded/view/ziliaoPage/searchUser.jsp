
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>搜索结果页</title>
    <link rel="stylesheet" type="text/css" href="../../css/css.css" />
    <link href="../../css/page.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>

</head>

<body>
<input id="state" value="${state}" hidden/>
<div id="pageAll">

    <div class="page">
        <!-- topic页面样式 -->
        <div class="topic">
            <div class="conform">
                <div class="cfD">
                    <input id="textSearchInfo" class="addUser" type="text" placeholder="员工id/员工姓名" />
                    <button class="button" id="btnSearch" >搜索</button>

                    <a class="addA addA1" href="/view/ziliaoPage/addUser.jsp">添加员工+</a>
                </div>
            </div>
            <!-- 表格 显示 -->
            <div class="conShow">
                <center>
                    <table id="tbCourse" border="1" cellspacing="0" cellpadding="0" >
                        <tr>
                            <td width="160px" class="tdColor tdC">员工编号</td>
                            <td width="130px" class="tdColor">员工昵称</td>
                            <td width="110px" class="tdColor">员工真实姓名</td>
                            <td width="160px" class="tdColor">手机号码</td>
                            <td width="260px" class="tdColor">所属公司</td>
                            <td width="160px" class="tdColor">所属部门</td>
                            <td width="130px" class="tdColor">操作</td>
                        </tr>


                    </table></center>
                <%--<div class="paging"><ul class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul></div>--%>
            </div>
            <!-- 表格 显示 end-->

        </div>
        <!-- 页面样式end -->
    </div>

</div>

<script type="text/javascript">

    $(document).ready(function () {
        var searchInfo = sessionStorage.getItem("searchUserInfo");
        $.ajax({
            url:"/data/findUser.json/" + sessionStorage.getItem("searchUserInfo"),
            dataType:"json",
            success:function (data) {
                $("#textSearchInfo").val(searchInfo);
                loadData(data);
            },
            error:function () {
                $("#textSearchInfo").val(searchInfo);
                $(".trList").html("");
                $("table").append("<tr class='trList'><td class='tdChild' colspan='7'>没有相关信息！</td></tr>")
            }
        })

    });


    $("#btnSearch").click(function () {
        sessionStorage.setItem("searchUserInfo",$("#textSearchInfo").val());
        $.ajax({
            url:"/data/findUser.json/" + $("#textSearchInfo").val(),
            dataType:"json",
            success:function (data) {
                $(".trList").html("");
                loadData(data)
            },
            error:function () {
                $(".trList").html("");
                $("table").append("<tr class='trList'><td class='tdChild' colspan='10'>没有相关信息！</td></tr>")
            }
        })
    });
    function loadData(data) {

        $.each(data,function (i,val) {

            $("table").append("<tr id='trUserList' class='trList'>"
                + "<td class='tdChild'>" + val.uid +"</td>"
                + "<td>" + val.uname + "</td>"
                + "<td>" + val.tname+"</td>"
                + "<td>" + val.uphone + "</td>"
                + "<td>" + val.companyByCompanyId.companyName+ "</td>"
                + "<td>" + val.deptByDeptId.deptName+ "</td>"
                + "<td><a href='/user/getUserInfo.do?userId="+ val.uid + " '><img class='operation' src='../../img/update.png'></a>"
                + "<a href='/user/delUser.do?isSearch=1&userId=" + val.uid + "' > "
                + "<img class='operation delban' src='../../img/delete.png'></a></td>");

        })
    }
    alertMsg();
    function alertMsg() {
        var state = $("#state").val();
        if(state == 200){
            alert("操作成功！");
        }
        if(state == 500){
            alert("操作失败！")
        }
    }
</script>
</body>

</html>
