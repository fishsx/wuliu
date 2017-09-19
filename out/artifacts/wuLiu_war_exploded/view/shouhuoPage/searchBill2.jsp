
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
<input id="state" type="hidden" value="${state}" />
<div id="pageAll">

    <div class="page">
        <!-- topic页面样式 -->
        <div class="topic">
            <div class="conform">
                <div class="cfD">
                    <input id="textSearchInfo" class="addUser" type="text" placeholder="货票ID/运票ID" />
                    <button class="button" id="btnSearch" >搜索</button>

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
                            <td width="80px" class="tdColor">状态</td>
                            <td width="80px" class="tdColor">查看详情</td>
                            <td width="80px" class="tdColor">货损登记</td>
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
            url:"/data/allBill.json",
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
    function loadData(data) {
            var state,reg = null;
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

            reg = (val.billState == 2) ? "<a href='/shouhuo/getBillInfo2.do?showInfo=0&billId="+ val.wayBillId + " '>货损登记</a>" : "";
            $("table").append("<tr id='trCompanyList' class='trList'>"
                + "<td class='tdChild'>" + val.wayBillId +"</td>"
                + "<td>" + val.billTrafficId + "</td>"
                + "<td>" + val.goodsByBillGoodId.goodId+"</td>"
                + "<td>" + val.factoryByBillFactoryId.factoryName + "</td>"
                + "<td>" + val.factoryByBillFactoryId.factoryAddress+"</td>"
                + "<td>" + val.consigneeByBillPerson.consigneeAddress + "</td>"
                + "<td>" + state + "</td>"
                + "<td><a href='/shouhuo/getBillInfo2.do?showInfo=1&billId="+ val.wayBillId + " '>查看详情</a>" + "</td>"
                + "<td>"+ reg +"</td>");

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
