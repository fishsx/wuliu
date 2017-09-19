
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>收货登记页面</title>
    <link rel="stylesheet" type="text/css" href="../../css/css.css" />
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-1.10.0.js"></script>
    <script type="text/javascript" src="../../js/china_area.js"></script>
    <script type="text/javascript" src="../../js/jquery.validate-1.13.1.js"></script>
</head>
<body>
<input id="state" type="hidden" value="${state}" />

<div id="pageAll">
    <div class="page ">
        <!-- 页面样式 -->
        <div class="banneradd bor">
            <div class="baTopNo">
                <span>收货登记</span>  <a class="addA addA1" href="/view/shouhuoPage/addFactory.jsp">添加厂商信息+</a>
            </div>

            <form id="myForm" action="/shouhuo/addBill.do" method="post" >
            <div class="baBody">

                    <div class="bbD">
                        厂商名称:<select id="selFactory" name="factoryId" class="input3" ></select>
                    </div>

                    <div class="bbD">
                        收货企业:<input type="text" name="companyName" class="input3"/>
                    </div>

                    <div class="bbD">
                        收货人姓名:<input type="text" name="name" class="input3" />
                    </div>

                    <div class="bbD">
                        收货人电话:<input type="text" name="phone" class="input3" />
                    </div>

                    <div class="bbD">
                        收货地址：
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
                        <input class="input3" type="text" name="jie" placeholder="输入街道详细地址"/>
                    </div>
            </div>

                <div class="baTopNo">
                    <span>货物情况登记</span>
                </div>
                    <div class="baBody">
                        <div class="bbD">
                            货物名称:<input type="text" name="goodname" class="input3" />
                        </div>

                        <div class="bbD">
                            货物类型:<select name="goodruletype" class="input3" >
                            <option value="0">方量</option>
                            <option value="1">重量</option>
                        </select>
                        </div>

                        <div class="bbD">
                            货物类型:<select name="goodnumtype" class="input3" >
                            <option value="件">件</option>
                            <option value="吨">吨</option>
                            <option value="方">方</option>
                            <option value="套">套</option>
                            <option value="台">台</option>
                        </select>
                        </div>

                        <div class="bbD">
                            货物数量:<input type="text" class="input3" name="goodnum"/>
                        </div>

                        <div class="bbD">
                            货物价值:<input type="text" class="input3" name="goodcount" placeholder="/元"/>
                        </div>


                        <div class="bbD">
                        <p class="bbDP">
                            <input id="btnSubmit" type="submit" class="btn_ok" > </input>
                        </p>
                        </div>

                    </div>

        </form>
            </div>

        <!-- 页面样式end -->
    </div>
</div>
<%--ajax获取json数据加入下拉列表--%>
<script type="text/javascript" >

    $(document).ready(function () {
        alertMsg();
        $.ajax({
            url:"/data/allFactory.json",
            dataType:"json",
            success:function (data) {
                $.each(data,function (index,val) {
                    $("#selFactory").append("<option value='"+val.factoryId + "' >"+val.factoryName +"</option>")
                })
            }
        });


    });

    function alertMsg() {
        var state = $("#state").val();
        if(state == 200){
            alert("操作成功！");
        }
        if(state == 500){
            alert("操作失败！")
        }
    }



    $(document).ready(function () {
        $("#myForm").validate({
            rules:{
                name:{
                    required:true
                },
                leader:{
                    required:true
                },
                companyName:{
                    required:true
                },
                jie:{
                    required:true
                },
                goodname:{
                    required:true
                },
                goodnum:{
                    required:true,
                    digits:true
                },
                goodcount:{
                    required:true,
                    number:true
                },
                goodbaojia:{
                    required:true,
                    number:true
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
