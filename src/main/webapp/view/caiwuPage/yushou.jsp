
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>货票冲减页面</title>
    <link rel="stylesheet" type="text/css" href="../../css/css.css" />
    <link href="../../css/page.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>

</head>

<body>

<div id="pageAll">

    <div class="page">
        <!-- topic页面样式 -->
        <div class="topic">
            <div class="conform">
                <div class="cfD">
                    <input id="textSearchInfo" class="addUser" type="text" placeholder="货票ID/运票ID" />
                    <button class="button" id="btnSearch" >搜索</button>
                    <label><input type="radio" name="rdbill" checked onclick="showBill_shouqi()"/>&nbsp;显示未收讫的货票</label>
                    <label><input type="radio" name="rdbill" onclick="showBill_all()" />&nbsp;显示所有货票</label>

                </div>
            </div>
            <!-- 表格 显示 -->
            <div class="conShow">
                <center>
                    <table id="tbCourse" border="1" cellspacing="0" cellpadding="0" >
                        <tr>
                            <td width="100px" class="tdColor tdC">货票编号</td>
                            <td width="100px" class="tdColor tdC">运单编号</td>
                            <td width="100px" class="tdColor tdC">货物编号</td>
                            <td width="160px" class="tdColor">发货厂商</td>
                            <td width="180px" class="tdColor">起始地</td>
                            <td width="180px" class="tdColor">目的地</td>
                            <td width="80px" class="tdColor">货票状态</td>
                            <td width="80px" class="tdColor">结算方式</td>
                            <td width="100px" class="tdColor">结算收讫</td>
                        </tr>


                    </table></center>
            </div>
            <!-- 表格 显示 end-->

        </div>
        <!-- 页面样式end -->
    </div>

</div>

<script type="text/javascript">

    $(document).ready(function () {
        showBill_shouqi();
    })


    $("#btnSearch").click(function () {

        $.ajax({
            url:"/data/findBillById_Tid.json/" + $("#textSearchInfo").val(),
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


    function showBill_all() {
        $.ajax({
            url:"/data/allBill.json",
            dataType:"json",
            success:function (data) {
                $(".trList").html("");
                loadData(data);
            },
            error:function () {
                $(".trList").html("");
                $("table").append("<tr class='trList'><td class='tdChild' colspan='9'>没有相关信息！</td></tr>")
            }
        })
    }

    function showBill_shouqi() {
        $.ajax({
            url:"/data/findBill_weiShouqi.json",
            dataType:"json",
            success:function (data) {
                $(".trList").html("");
                loadData(data);
            },
            error:function () {
                $(".trList").html("");
                $("table").append("<tr class='trList'><td class='tdChild' colspan='9'>没有相关信息！</td></tr>")
            }
        })
    }




    function loadData(data) {
        var state,payType,finish = null;

        $.each(data,function (i,val) {
            switch (val.billState){
                case -1 :
                    state = "未发货";break;
                case  1 :
                    state = "已发货";break;
                case  2 :
                    state = "已收货";break;
                case  3 :
                    state = "已完成";break;
                default: state = "状态异常"
            }
            switch (val.billPayState){
                case -1 :
                    payType = "提付";break;
                case 1 :
                    payType = "收讫";break;
                default :payType = "状态异常"
            }

            finish = "<a href='/bill/shouqiBill.do?billId="+val.wayBillId+" '>收讫</a>";
            switch (val.billPayType ){
                case -1 :
                    finish = "未支付";break;
                case 1 :
                    finish = (val.billPayState == 1) ? "已完成收讫操作" : finish;break;
                default : finish= "";break;
            }

            $("table").append("<tr id='trCompanyList' class='trList'>"
                + "<td class='tdChild'>" + val.wayBillId +"</td>"
                + "<td>" + val.billTrafficId + "</td>"
                + "<td>" + val.goodsByBillGoodId.goodId+"</td>"
                + "<td>" + val.factoryByBillFactoryId.factoryName + "</td>"
                + "<td>" + val.factoryByBillFactoryId.factoryAddress+"</td>"
                + "<td>" + val.consigneeByBillPerson.consigneeAddress + "</td>"
                + "<td>" + state + "</td>"
                + "<td>" + payType  + "</td>"
                + "<td>" + finish  + "</td>");

        })
    }



</script>
</body>

</html>
