<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>安排车次页面</title>
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
                <span>安排车次</span>
            </div>


                <div class="baBody">


                    <div class="bbD">
                        运单编号：<label>${trafficBill.trafficBillId}</label>
                    </div>

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



                    <form id="myForm" action="/bill/updateTrafficBill_CarDriver.do" method="post" >
                        <input type="text" hidden name="trafficBillId" value="${trafficBill.trafficBillId}"/>
                        <div class="bbD">
                           选择司机： <select id="selDriver" name="driverId" class="input3"></select>
                        </div>

                        <div class="bbD">
                            选择车辆： <select id="selCar" name="carId" class="input3"></select>
                        </div>

                        <div class="bbD">
                            <%--发车时间：<input type="date" name="startDate" class="input3"><input type="time" name="startTime" class="input2" />--%>
                            发车时间：<input type="datetime-local" name="startDate" class="input3" id=""/>
                        </div>

                        <div class="bbD">
                            预计到达时间：<input type="datetime-local" name="finalDate" class="input3">
                        </div>

                        <div class="bbD">
                            <p class="bbDP">
                                <input id="btnSubmit" type="submit" class="btn_ok" > </input>
                            </p>
                        </div>

                    </form>
                 </div>
        </div>
        <!-- 页面样式end -->
    </div>
</div>
<%--ajax获取json数据加入下拉列表--%>
<script type="text/javascript" >

    $(document).ready(function () {
        $.ajax({
            url:"/data/findCar_kongxian.json",
            dataType:"json",
            success:function (data) {
                $.each(data,function (index,val) {
                    $("#selCar").append("<option value='"+val.carId + "' >"+val.carChepai +"</option>")
                })
            }
        });
        $.ajax({
            url:"/data/findDriver_kongxian.json",
            dataType:"json",
            success:function (data) {
                $.each(data,function (index,val) {
                    $("#selDriver").append("<option value='"+val.driverId + "' >"+val.driverName +"</option>")
                })
            }
        });


    })


    $(document).ready(function () {
        $("#myForm").validate({
            rules:{
                driver:{
                    required:true
                },
                car:{
                    required:true
                },
                startDate:{
                    required:true,
                    dateTime:true
                },
                finalDate:{
                    required:true,
                    dateTime:true
                },
                startTime:{
                    required:true,
                    time:true
                },
                finalTime:{
                    required:true,
                    time:true
                }

            }


        })
    })


</script>

</body>
</html>
