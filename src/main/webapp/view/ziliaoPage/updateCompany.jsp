<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>修改公司页面</title>
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
                <span>修改公司</span>
            </div>
            <div class="baBody">
                <form id="myForm" action="/company/updateCompany.do" method="post" >

                    <input id="SearchInfo" type="text" name="isSearch"  hidden />
                    <input type="text" name="oldId" value="${company.companyId}" hidden>

                    <div class="bbD">
                        公司名称：<input type="text" name="name" class="input3" value="${company.companyName}" />
                    </div>
                    <div class="bbD">
                        <label >地址：</label>
                        <input id="sheng" type="hidden" name="sheng"/>
                        <input id="shi" type="hidden" name="shi" />
                        <input id="qu" type="hidden" name="qu"/>
                        <select id="selProvince" class="input2" name="province">
                            <option value="0">请选择省份</option>
                        </select>
                        <select id="selCity" class="input2" name="city" >
                            <option value="0">请选择城市</option>
                        </select>
                        <select id="selDistrict" class="input2" name="county">
                            <option value="0">请选择区/县</option>
                        </select>
                        <input class="input3" type="text" name="jie" placeholder="输入街道详细地址" />
                    </div>

                    <div class="bbD">
                        公司负责人:<input type="text" name="leader" class="input3" value="${company.companyLeader}"/>
                    </div>

                    <div class="bbD">
                        负责人电话:<input type="text" name="phone" class="input3" value="${company.companyPhone}"/>
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
        $("#SearchInfo").val(sessionStorage.getItem("searchCompanyInfo"));
    })



    $(document).ready(function () {
        $("#myForm").validate({
            rules:{
                name:{
                    required:true
                },
                leader:{
                    required:true
                },
                jie:{
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





    $(function() {
        $.each(provinceJson, function(k, p) {
            var option = "<option value='" + p.id + "' >" + p.province + "</option>";
            $("#selProvince").append(option);
        });
        $("#selProvince").change(function() {
            var selValue = $(this).val();
            $("#selCity option:gt(0)").remove();
            $.each(cityJson, function(k, p) {
                // 直辖市处理.|| p.parent == selValue，直辖市为当前自己
                if (p.id == selValue || p.parent == selValue) {
                    var option = "<option value='" + p.id + "'>" + p.city +  "</option>";
                    $("#selCity").append(option);
                }
            });
        });
        $("#selCity").change(function() {
            var selValue = $(this).val();
            $("#selDistrict option:gt(0)").remove();
            $.each(countyJson, function(k, p) {
                if (p.parent == selValue) {
                    var option = "<option value='" + p.id + "'>" + p.county + "</option>";
                    $("#selDistrict").append(option);
                }
            });
        });
    });

    $("#btnSubmit").click(function () {
        $("#sheng").val($("#selProvince option:selected").text());
        $("#shi").val($("#selCity option:selected").text());
        $("#qu").val($("#selDistrict option:selected").text());
    })
</script>

</body>
</html>
