
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>确认收货页面</title>
    <link rel="stylesheet" type="text/css" href="../../css/css.css" />
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-1.10.0.js"></script>
    <script type="text/javascript" src="../../js/china_area.js"></script>
    <script type="text/javascript" src="../../js/jquery.validate-1.13.1.js"></script>
</head>
<body>

<div id="pageAll">
    <div class="page ">
        <!-- 页面样式 -->
        <div class="banneradd bor">

            <div class="baBody">

                <div class="conShow">
                  <center>  <table border="1" cellspacing="0" cellpadding="0">
                        <tr height="40px"><td colspan="4">成功生成货票,部分信息如下：</td> </tr>
                        <tr>
                            <td width="100px" class="tdColor tdC">货票编号</td>
                            <td width="200px" >${bill.wayBillId}</td>
                            <td width="100px" class="tdColor tdC">运单编号</td>
                            <td width="200px" >${bill.billTrafficId}</td>
                        </tr>
                        <tr>
                            <td class="tdColor">发货商</td>
                            <td >${bill.factoryByBillFactoryId.factoryName}</td>
                            <td class="tdColor">电话</td>
                            <td>${bill.factoryByBillFactoryId.factoryPhone}</td>
                        </tr>
                        <tr>
                            <td class="tdColor">收货人</td>
                            <td >${bill.consigneeByBillPerson.consigneeName}</td>
                            <td class="tdColor">电话</td>
                            <td>${bill.consigneeByBillPerson.consigneePhone}</td>
                        </tr>
                        <tr>
                            <td class="tdColor">发货地址</td>
                            <td >${bill.factoryByBillFactoryId.factoryAddress}</td>
                            <td class="tdColor">收货地址</td>
                            <td>${bill.consigneeByBillPerson.consigneeAddress}</td>
                        </tr>
                        <tr>
                            <td class="tdColor">货物名称</td>
                            <td >${bill.goodsByBillGoodId.goodName}</td>
                            <td class="tdColor">货物价值</td>
                            <td>${bill.goodsByBillGoodId.goodCount}元</td>
                        </tr>

                    </table></center>

                </div>
            </div>
        </div>

        <!-- 页面样式end -->
    </div>
</div>
<%--ajax获取json数据加入下拉列表--%>

</body>
</html>
