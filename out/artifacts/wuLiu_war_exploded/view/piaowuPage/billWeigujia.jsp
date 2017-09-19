
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

</head>

<body>
<input id="state" type="hidden" value="${state}" />
<div id="pageAll">

    <div class="page">
        <!-- topic页面样式 -->
        <div class="topic">
            <div class="conform">
                <div class="cfD">
                    <input id="textSearchInfo" class="addUser" type="text" placeholder="货票ID/运票ID" />
                    <button class="button" id="btnSearch" >搜索</button>

                    <label><input type="radio" name="rdbill" checked onclick="showBill_weigujia()"/>&nbsp;显示未估价货票</label>
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
                            <td width="180px" class="tdColor">是否估价</td>
                            <td width="150px" class="tdColor">估价</td>
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
            url:"/data/findBill_weigujia.json",
            dataType:"json",
            success:function (data) {
                loadData(data);
            },
            error:function () {
                $("table").append("<tr class='trList'><td class='tdChild' colspan='8'>没有相关信息！</td></tr>")
            }
        })
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
                $("table").append("<tr class='trList'><td class='tdChild' colspan='8'>没有相关信息！</td></tr>")
            }
        })
    });

    function showBill_weigujia() {
        $.ajax({
            url:"/data/findBill_weigujia.json",
            dataType:"json",
            success:function (data) {
                $(".trList").html("");
                loadData(data);
            },
            error:function () {
                $(".trList").html("");
                $("table").append("<tr class='trList'><td class='tdChild' colspan='8'>没有相关信息！</td></tr>")
            }
        })
    }

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
                $("table").append("<tr class='trList'><td class='tdChild' colspan='8'>没有相关信息！</td></tr>")
            }
        })
    }






    function loadData(data) {
            var gujia,show = null;

        $.each(data,function (i,val) {
            gujia = (val.goodsByBillGoodId.goodBaojia == -1) ? "未估价" : "已估价";
            var edit = "<a href='/bill/getBillInfo.do?billId="+val.wayBillId+" '>" +"<img class='operation' src='../../img/update.png '></a>";
            var noedit = "<a href='#' class='operation'><img src='../../img/update2.png'></a>";
            show = (val.goodsByBillGoodId.goodBaojia == -1) ? edit : noedit;
            $("table").append("<tr id='trCompanyList' class='trList'>"
                + "<td class='tdChild'>" + val.wayBillId +"</td>"
                + "<td>" + val.billTrafficId + "</td>"
                + "<td>" + val.goodsByBillGoodId.goodId+"</td>"
                + "<td>" + val.factoryByBillFactoryId.factoryName + "</td>"
                + "<td>" + val.factoryByBillFactoryId.factoryAddress+"</td>"
                + "<td>" + val.consigneeByBillPerson.consigneeAddress + "</td>"
                + "<td>" + gujia + "</td>"
                + "<td>" + show
                + "</td>");

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
