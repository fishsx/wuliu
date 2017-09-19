<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>修改汽车页面</title>
    <link rel="stylesheet" type="text/css" href="../../css/css.css" />
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-1.10.0.js"></script>
    <script type="text/javascript" src="../../js/jquery.validate-1.13.1.js"></script>
</head>
<body>


<div id="pageAll">
    <div class="page ">
        <!-- 页面样式 -->
        <div class="banneradd bor">
            <div class="baTopNo">
                <span>修改汽车</span>
            </div>
            <div class="baBody">
                <form id="myForm" action="/car/updateCar.do" method="post" >

                    <input id="SearchInfo" type="text" name="isSearch"  hidden />
                    <input type="text" name="oldId" value="${car.carId}" hidden>

                    <div class="bbD">
                        汽车类型：<select class="input3" name="type">
                        <c:forTokens items="A,B,C,D" delims="," var="val">
                            <option value="${val}" ${val.equals(car.carType) ? 'selected' : ""}>${val}</option>
                        </c:forTokens>

                    </select>
                    </div>
                    <div class="bbD">
                        <label>方量：</label><input type="text" name="fangliang" class="input3" value="${car.carFangliang}" />
                    </div>
                    <div class="bbD">
                        <label>吨量：</label><input type="text" name="dunliang" class="input3"  value="${car.carDunliang}"/>
                    </div>
                    <div class="bbD">
                        车牌号: &nbsp;&nbsp;&nbsp; <input type="text" name="chepai" class="input3" value="${car.carChepai}" />
                    </div>
                    <div class="bbD">
                        行驶证号码:<input type="text" name="license" class="input3" value="${car.carDriverLicense}"/>
                    </div>

                    <div class="bbD">
                        当前状态：<select class="input3" name="state" >
                        <option value="-1" ${car.carState == -1 ? 'selected' : ''} >停运中</option>
                        <option value="0" ${car.carState == 0 ? 'selected' : ''} >待命中</option>
                        <option value="1" ${car.carState == 1 ? 'selected' : ''} >作业中</option>
                    </select>
                    </div>

                    <div class="bbD">
                        所属公司：<select id="selCompany" class="input3" name="companyId">
                            <option id="optCompanyId" value="${car.companyByCarCompany.companyId}">${car.companyByCarCompany.companyName}</option>

                    </select>
                    </div>


                    <div class="bbD">
                        <p class="bbDP">
                            <input type="submit" class="btn_ok" > </input>


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
        $("#SearchInfo").val(sessionStorage.getItem("searchCarInfo"));
        $.ajax({
            url:"/data/allCompany.json",
            dataType:"json",
            success:function (data) {
                var companyId = $("#optCompanyId").val();
                $.each(data,function (index,val) {
                    if(companyId != val["companyId"]){
                        $("#selCompany").append("<option value = " + val["companyId"] + " >" + val["companyName"] + "</option>");
                    }

                });
            }
        })

    })


    $(document).ready(function () {
        $("#myForm").validate({
            rules:{
                name:{
                    required:true
                },
                tname:{
                    required:true
                },
                pwd:{
                    required:true
                },
                company:{
                    required:true
                },
                dept:{
                    required:true
                },
                phone:{
                    required:true,
                    digits:true,
                    rangelength:[11,11]
                }


            },
            messages:{
                phone:{
                    required:"必须填写手机号",
                    digits:"请正确填写手机号码",
                    rangelength:"请正确填写手机号码"
                }
            }


        })
    })

</script>
</body>
</html>
