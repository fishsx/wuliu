
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>未估价货票页面</title>
    <link rel="stylesheet" type="text/css" href="../../css/css.css" />
    <link href="../../css/page.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/numToTimeStamp.js"></script>
</head>

<body>
<input id="state" type="hidden" value="${state}" />
<div id="pageAll">

    <div class="page">
        <!-- topic页面样式 -->
        <div class="topic">
            <div class="conform">
                <div class="cfD">
                    <input id="textSearchInfo" class="addUser" type="text" placeholder="运票ID" />
                    <button class="button" id="btnSearch" >搜索</button>

                    <label><input type="radio" name="rdbill"  onclick="showTrafficBill_wei()"/>&nbsp;显示未安排的运票</label>
                    <label><input type="radio" name="rdbill" checked onclick="showTrafficBill_all()" />&nbsp;显示所有运票</label>

                </div>
            </div>
            <!-- 表格 显示 -->
            <div class="conShow">
                <center>
                    <table id="tbCourse" border="1" cellspacing="0" cellpadding="0" >
                        <tr>
                            <td width="150px" class="tdColor tdC">运单编号</td>
                            <td width="180px" class="tdColor">初始地</td>
                            <td width="180px" class="tdColor">目的地</td>
                            <td width="60px" class="tdColor">司机姓名</td>
                            <td width="160px" class="tdColor">车牌号</td>
                            <td width="100px" class="tdColor">发车时间</td>
                            <td width="100px" class="tdColor">预计到达时间</td>
                            <td width="100px" class="tdColor">安排司机车辆</td>
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
        alertMsg();
        $.ajax({
            url:"/data/allTrafficBill.json",
            dataType:"json",
            success:function (data) {
                loadData(data);
            },
            error:function () {
                $("table").append("<tr class='trList'><td class='tdChild' colspan='10'>没有相关信息！</td></tr>")
            }
        })
    })


    $("#btnSearch").click(function () {


        $.ajax({
            url:"/data/findTrafficBillById.json/" + $("#textSearchInfo").val(),
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

    function showTrafficBill_wei() {
        $.ajax({
            url:"/data/findTrafficBill_NoCarDriver.json",
            dataType:"json",
            success:function (data) {
                $(".trList").html("");
                loadData(data);
            },
            error:function () {
                $(".trList").html("");
                $("table").append("<tr class='trList'><td class='tdChild' colspan='10'>没有相关信息！</td></tr>")
            }
        })
    }

    function showTrafficBill_all() {
        $.ajax({
            url:"/data/allTrafficBill.json",
            dataType:"json",
            success:function (data) {
                $(".trList").html("");
                loadData(data);
            },
            error:function () {
                $(".trList").html("");
                $("table").append("<tr class='trList'><td class='tdChild' colspan='10'>没有相关信息！</td></tr>")
            }
        })
    }






    function loadData(data) {
        var name,car,startDate,finalDate= null;

        $.each(data,function (i,val) {
            var anpai2 = "<a href='/bill/getTrafficBillInfo.do?trafficBillId="+ val.trafficBillId + "' >安排车次</a>"
            name = (val.driverInfoByDriver == null) ? '未填写': val.driverInfoByDriver.driverName;
            car = (val.carInfoByCar == null) ? '未填写' : val.carInfoByCar.carChepai;
            startDate = (val.startDate == null) ? '未填写' : new Date(val.startDate).format("yyyy-MM-dd hh:mm");
            finalDate = (val.finalDate == null) ? '未填写' : new Date(val.finalDate).format("yyyy-MM-dd hh:mm");




            var anpai = (val.driverInfoByDriver != null ) ? '已安排车次' : anpai2;
            $("table").append("<tr id='trCompanyList' class='trList'>"
                + "<td class='tdChild'>" + val.trafficBillId +"</td>"
                + "<td>" + val.startPlace + "</td>"
                + "<td>" + val.finalPlace+"</td>"
                + "<td>" + name + "</td>"
                + "<td>" + car+"</td>"
                + "<td>" +startDate + "</td>"
                + "<td>" +finalDate + "</td>"
                + "<td>" + anpai +"</td>"
                + "<td><a href='/bill/getTrafficBillInfo.do?trafficBillId="+ val.trafficBillId + " '><img class='operation' src='../../img/update.png'></a>"
                + "<a href='/bill/delTrafficBill.do?trafficBillId= " + val.trafficBillId + "' >"
                + "<img class='operation delban' src='../../img/delete.png'></a></td>");


        })
    }


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
