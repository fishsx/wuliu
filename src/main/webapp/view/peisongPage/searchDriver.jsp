
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
                    <input id="textSearchInfo" class="addUser" type="text" placeholder="司机姓名/驾照号码/所属公司" />
                    <button class="button" id="btnSearch" >搜索</button>

                    <a class="addA addA1" href="/view/peisongPage/addDriver.jsp">添加司机+</a>
                </div>
            </div>
            <!-- 表格 显示 -->
            <div class="conShow">
                <center>
                    <table id="tbCourse" border="1" cellspacing="0" cellpadding="0" >
                        <tr>
                            <td width="160px" class="tdColor tdC">司机编号</td>
                            <td width="100px" class="tdColor">姓名</td>
                            <td width="90px" class="tdColor">性别</td>
                            <td width="120px" class="tdColor">出生日期</td>
                            <td width="100px" class="tdColor">驾驶证类型</td>
                            <td width="180px" class="tdColor">驾驶证号码</td>
                            <td width="160px" class="tdColor">所属公司</td>
                            <td width="100px" class="tdColor">状态</td>
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
        var searchInfo = sessionStorage.getItem("searchDriverInfo");
        $.ajax({
            url:"/data/findDriver.json/" + sessionStorage.getItem("searchDriverInfo"),
            dataType:"json",
            success:function (data) {
                $("#textSearchInfo").val(searchInfo);
                loadData(data);
            },
            error:function () {
                $("#textSearchInfo").val(searchInfo);
                $(".trList").html("");
                $("table").append("<tr class='trList'><td class='tdChild' colspan='9'>没有相关信息！</td></tr>")
            }
        })

    });


    $("#btnSearch").click(function () {
        sessionStorage.setItem("searchDriverInfo",$("#textSearchInfo").val());
        $.ajax({
            url:"/data/findDriver.json/" + $("#textSearchInfo").val(),
            dataType:"json",
            success:function (data) {
                $(".trList").html("");
                loadData(data)
            },
            error:function () {
                $(".trList").html("");
                $("table").append("<tr class='trList'><td class='tdChild' colspan='9'>没有相关信息！</td></tr>")
            }
        })
    });
    function loadData(data) {
            var state = null;
        $.each(data,function (i,val) {
            switch (val.driverState){
                case -2 :
                    state = "离职";break;
                case -1 :
                    state = "休假";break;
                case  0 :
                    state = "空闲";break;
                case  1 :
                    state = "工作中";break;
                default: state = "状态异常"
            }
            $("table").append("<tr id='trDriverList' class='trList'>"
                + "<td class='tdChild'>" + val.driverId +"</td>"
                + "<td>" + val.driverName + "</td>"
                + "<td>" + val.driverSex+"</td>"
                + "<td>" + val.driverBirthday + "</td>"
                + "<td>" + val.driverLicenseType+ "</td>"
                + "<td>" + val.driverLicense+ "</td>"
                + "<td>" + val.companyByDriverCompany.companyName+ "</td>"
                + "<td>" + state+ "</td>"
                + "<td><a href='/driver/getDriverInfo.do?driverId="+ val.driverId + " '><img class='operation' src='../../img/update.png'></a>"
                + "<a href='/driver/delDriver.do?isSearch=1&driverId=" + val.driverId + "' > "
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
