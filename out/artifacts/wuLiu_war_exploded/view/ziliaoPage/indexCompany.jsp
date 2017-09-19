
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>公司管理主页</title>
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
                    <input id="textCompanySearchInfo" class="addUser" type="text" placeholder="公司id/公司名称" />
                    <a href="/view/ziliaoPage/searchCompany.jsp"  > <button id="btnSearch" class="button" >搜索</button></a>

                    <a class="addA addA1" href="addCompany.jsp">添加公司+</a>
                </div>
            </div>
            <!-- 表格 显示 -->
            <div class="conShow">
                <center>
                    <table id="tbCompany" border="1" cellspacing="0" cellpadding="0" >
                        <tr>
                            <td width="160px" class="tdColor tdC">公司编号</td>
                            <td width="200px" class="tdColor">公司名称</td>
                            <td width="280px" class="tdColor">地址</td>
                            <td width="100px" class="tdColor">负责人</td>
                            <td width="160px" class="tdColor">电话</td>
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
        sessionStorage.removeItem("searchCompanyInfo");
        $.ajax({
            url:"/data/allCompany.json",
            dataType:"json",
            success:function (data) {
                loadData(data);
            }
        })

    });
    //点击跳转页面
    $("#btnSearch").click(function () {
        var searchInfo = $("#textCompanySearchInfo").val();
        sessionStorage.setItem("searchCompanyInfo",searchInfo);

    });

    function loadData(data) {
        $.each(data,function (i,val) {
            $("table").append("<tr id='trCompanyList' class='trList'>"
                + "<td class='tdChild'>" + val.companyId +"</td>"
                + "<td>" + val.companyName + "</td>"
                + "<td>" + val.companyAddress+"</td>"
                + "<td>" + val.companyLeader + "</td>"
                + "<td>" + val.companyPhone+ "</td>"
                + "<td><a href='/company/getCompanyInfo.do?companyId= "+ val.companyId + " '><img class='operation' src='../../img/update.png'></a>"
                + "<a href='/company/delCompany.do?companyId= " + val.companyId + "' > "
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