
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>司机管理主页</title>
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
                <div class="cfD">
                    <input id="textSearchInfo" class="addUser" type="text" placeholder="司机姓名/驾照号码/所属公司" />
                    <a href="/view/peisongPage/searchDriver.jsp"  > <button id="btnSearch" class="button" >搜索</button></a>
                    <a class="addA addA1" href="/view/peisongPage/addDriver.jsp">添加司机+</a>
                </div>
            </div>
            <!-- 表格 显示 -->
            <div class="conShow">
                <center>
                    <table id="tbUser" border="1" cellspacing="0" cellpadding="0" >
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
    <%--数据初始化--%>
    $(document).ready(function () {
        sessionStorage.removeItem("searchDriverInfo");
        $.ajax({
            url:"/data/allDriver.json",
            dataType:"json",
            success:function (data) {
                loadData(data);
            }
        })

    });
    //点击跳转页面
    $("#btnSearch").click(function () {
        var searchInfo = $("#textSearchInfo").val();
        sessionStorage.setItem("searchDriverInfo",searchInfo);

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
                + "<td><a href='/driver/getDriverInfo.do?driverId= "+ val.driverId + " '><img class='operation' src='../../img/update.png'></a>"
                + "<a href='/driver/delDriver.do?driverId= " + val.driverId + "' > "
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