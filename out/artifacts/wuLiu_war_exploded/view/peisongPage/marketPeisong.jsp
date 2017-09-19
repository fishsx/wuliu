
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>市场配送页面</title>
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

                    <label><input type="radio" name="rdbill"  checked onclick="showTrafficBill_yi()"/>&nbsp;显示已安排的运票</label>


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
                            <td width="100px" class="tdColor">市场配送</td>

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
        showTrafficBill_yi();
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

    function showTrafficBill_yi() {
        $.ajax({
            url:"/data/findTrafficBill_peisong.json",
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

            name = (val.driverInfoByDriver == null) ? '未填写': val.driverInfoByDriver.driverName;
            car = (val.carInfoByCar == null) ? '未填写' : val.carInfoByCar.carChepai;
            startDate = (val.startDate == null) ? '未填写' : new Date(val.startDate).format("yyyy-MM-dd hh:mm");
            finalDate = (val.finalDate == null) ? '未填写' : new Date(val.finalDate).format("yyyy-MM-dd hh:mm");

            var anpai2 = "<a href='/bill/startPeisong.do?trafficBillId="+ val.trafficBillId + "' >开始配送</a>";
            var peisong = val.wayBillByWayBillId.billState;
            switch (peisong){
                case -1 :
                    peisong = anpai2;break;
                case  1 :
                    peisong = "已发货";break;
                case  2 :
                    peisong = "已收货";break;
                case  3 :
                    peisong = "已完成";break;
                default: peisong = "状态异常"
            }

            var anpai = (val.wayBillByWayBillId.billState == 1 ) ? '已开始配送' : anpai2;
            $("table").append("<tr id='trCompanyList' class='trList'>"
                + "<td class='tdChild'>" + val.trafficBillId +"</td>"
                + "<td>" + val.startPlace + "</td>"
                + "<td>" + val.finalPlace+"</td>"
                + "<td>" + name + "</td>"
                + "<td>" + car+"</td>"
                + "<td>" +startDate + "</td>"
                + "<td>" +finalDate + "</td>"
                + "<td>" + peisong +"</td>");


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
