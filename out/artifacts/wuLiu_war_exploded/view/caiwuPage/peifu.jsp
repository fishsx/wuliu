
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>赔付页面</title>
    <link rel="stylesheet" type="text/css" href="../../css/css.css" />
    <link href="../../css/page.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>

</head>

<body>
<input id="state" type="hidden" value="${state}" />
<div id="pageAll">

    <div class="page">
        <!-- topic页面样式 -->
        <div class="topic">
            <div class="conform">
                <div class="cfD">
                    <input id="textSearchInfo" class="addUser" type="text" placeholder="货票ID" />
                    <button class="button" id="btnSearch" >搜索</button>
                    <label><input type="radio" name="rdbill" checked onclick="showBill_damage()"/>&nbsp;显示货损货票</label>

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
                            <td width="180px" class="tdColor">起始地</td>
                            <td width="180px" class="tdColor">目的地</td>
                            <td width="80px" class="tdColor">货票状态</td>
                            <td width="80px" class="tdColor">货损金额</td>
                            <td width="80px" class="tdColor">需赔付金额</td>
                            <td width="100px" class="tdColor">赔付操作</td>
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
        showBill_damage();
    })


    $("#btnSearch").click(function () {

        $.ajax({
            url:"/data/findBill_damage_byId/" + $("#textSearchInfo").val(),
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


    function showBill_damage() {
        $.ajax({
            url:"/data/findBill_damage.json",
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
        var state,peifu,peiqian = null;

        $.each(data,function (i,val) {
            switch (val.wayBillByBillId.billState){
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

            peiqian = (val.wayBillByBillId.billPeiqian == -1 )? '未登记': val.wayBillByBillId.billPeiqian+"元";
            peifu = "<a href='/bill/caiwuPeiqian.do?billId="+val.wayBillByBillId.wayBillId+" '>赔付</a>";
            peifu = (val.wayBillByBillId.billPeiqian == 0)?  '已赔付' : peifu;

            $("table").append("<tr id='trCompanyList' class='trList'>"
                + "<td class='tdChild'>" + val.wayBillByBillId.wayBillId +"</td>"
                + "<td>" + val.wayBillByBillId.billTrafficId + "</td>"
                + "<td>" + val.wayBillByBillId.goodsByBillGoodId.goodId+"</td>"
                + "<td>" + val.wayBillByBillId.factoryByBillFactoryId.factoryAddress+"</td>"
                + "<td>" + val.wayBillByBillId.consigneeByBillPerson.consigneeAddress + "</td>"
                + "<td>" + state + "</td>"
                + "<td>" + val.damageMoney+"元</td>"
                + "<td>" + peiqian+"</td>"
                + "<td>" + peifu  + "</td>");

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
