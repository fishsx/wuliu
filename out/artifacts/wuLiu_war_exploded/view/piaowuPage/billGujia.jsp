
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
            <div class="baTopNo">
                <span>货票详情</span>
            </div>
            <div class="baBody">
                <form id="myForm" action="/bill/billGujia.do" method="post" >

                    <input type="text" hidden name="goodId" value="${bill.goodsByBillGoodId.goodId}"/>
                    <div class="bbD">
                        厂商名称:<label>${bill.factoryByBillFactoryId.factoryName}</label>
                    </div>

                    <div class="bbD">
                        收货企业:<label>${bill.consigneeByBillPerson.consigneeCompany}</label>
                    </div>

                    <div class="bbD">
                        收货人姓名:<label>${bill.consigneeByBillPerson.consigneeName}</label>
                    </div>

                    <div class="bbD">
                        收货人电话:<label>${bill.consigneeByBillPerson.consigneePhone}</label>
                    </div>

                    <div class="bbD">
                        收货地址：<label>${bill.consigneeByBillPerson.consigneeAddress}</label>

                    </div>

                    <div class="bbD">
                        货物名称:<label>${bill.goodsByBillGoodId.goodName}</label>
                    </div>

                    <div class="bbD">
                        货物单位:<label>${bill.goodsByBillGoodId.goodNumType}</label>
                    </div>

                    <div class="bbD">
                        货物数量:<label>${bill.goodsByBillGoodId.goodNum}</label>
                    </div>

                    <div class="bbD">
                        货物价值:<label>${bill.goodsByBillGoodId.goodCount}</label>元
                    </div>




                    <div class="bbD">
                        当前货票状态:<label>
                        <c:if test="${bill.billState == -1}">未发货</c:if>
                        <c:if test="${bill.billState == 1}">已发货</c:if>
                        <c:if test="${bill.billState ==2}">已确认发货</c:if>

                    </label>
                    </div>

                    <div class="bbD">
                        保价:<input type="text" class="input3" name="baojia" placeholder="/元"/>
                    </div>

                    <div class="bbD">
                        <p class="bbDP">


                                <input id="btnSubmit" type="submit" class="btn_ok" value="提交"> </input>



                        </p>
                    </div>
                </form>
            </div>
        </div>

        <!-- 页面样式end -->
    </div>
</div>
<%--ajax获取json数据加入下拉列表--%>

</body>
</html>
