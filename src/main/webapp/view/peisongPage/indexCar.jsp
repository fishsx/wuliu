
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>车辆管理主页</title>
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
                    <input id="textSearchInfo" class="addUser" type="text" placeholder="车牌号/行驶证号码/所属公司" />
                    <a href="/view/peisongPage/searchCar.jsp"  > <button id="btnSearch" class="button" >搜索</button></a>
                    <a class="addA addA1" href="/view/peisongPage/addCar.jsp">添加汽车+</a>
                </div>
            </div>
            <!-- 表格 显示 -->
            <div class="conShow">
                <center>
                    <table id="tbUser" border="1" cellspacing="0" cellpadding="0" >
                        <tr>
                            <td width="160px" class="tdColor tdC">汽车编号</td>
                            <td width="100px" class="tdColor">汽车型号</td>
                            <td width="100px" class="tdColor">方量</td>
                            <td width="100px" class="tdColor">顿量</td>
                            <td width="160px" class="tdColor">车牌号</td>
                            <td width="160px" class="tdColor">行驶证号码</td>
                            <td width="160px" class="tdColor">所属公司</td>
                            <td width="80px" class="tdColor">状态</td>
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
        sessionStorage.removeItem("searchCarInfo");
        $.ajax({
            url:"/data/allCar.json",
            dataType:"json",
            success:function (data) {
                loadData(data);
            }
        })

    });
    //点击跳转页面
    $("#btnSearch").click(function () {
        var searchInfo = $("#textSearchInfo").val();
        sessionStorage.setItem("searchCarInfo",searchInfo);

    });

    function loadData(data) {
        var state =null;
        $.each(data,function (i,val) {
            switch (val.carState){
                case -1 :
                    state = "停运中";break;
                case  0 :
                    state = "待命中";break;
                case  1 :
                    state = "作业中";break;
                default: state = "状态异常"
            }


            $("table").append("<tr id='trCarList' class='trList'>"
                + "<td class='tdChild'>" + val.carId +"</td>"
                + "<td>" + val.carType + "</td>"
                + "<td>" + val.carFangliang+"</td>"
                + "<td>" + val.carDunliang + "</td>"
                + "<td>" + val.carChepai+ "</td>"
                + "<td>" + val.carDriverLicense+ "</td>"
                + "<td>" + val.companyByCarCompany.companyName+ "</td>"
                + "<td>" + state + "</td>"
                + "<td><a href='/car/getCarInfo.do?carId= "+ val.carId + " '><img class='operation' src='../../img/update.png'></a>"
                + "<a href='/car/delCar.do?carId= " + val.carId + "' > "
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