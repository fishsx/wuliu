
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>添加汽车页面</title>
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
                <span>添加汽车</span>
            </div>
            <div class="baBody">
                <form id="myForm" action="/car/addCar.do" method="post" >

                    <div class="bbD">
                       汽车类型：<select class="input3" name="type">
                            <option value="A">A</option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                            <option value="D">D</option>

                    </select>
                    </div>
                    <div class="bbD">
                        <label>方量：</label><input type="text" name="fangliang" class="input3" />
                    </div>
                    <div class="bbD">
                        <label>吨量：</label><input type="text" name="dunliang" class="input3" />
                    </div>
                    <div class="bbD">
                        车牌号: &nbsp;&nbsp;&nbsp; <input type="text" name="chepai" class="input3" />
                    </div>
                    <div class="bbD">
                        行驶证号码:<input type="text" name="license" class="input3"/>
                    </div>

                    <div class="bbD">
                       所属公司：<select id="selCompany" class="input3" name="companyId"></select>
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

        $.ajax({
            url:"/data/allCompany.json",
            dataType:"json",
            success:function (data) {
                $.each(data,function (index,val) {
                    $("#selCompany").append("<option value = " + val["companyId"] + ">" + val["companyName"] + "</option>");
                });
            }
        })


    })


    $(document).ready(function () {
        $("#myForm").validate({
            rules:{
                type:{
                    required:true
                },
                fangliang:{
                    number:true,
                    required:true
                },
                dunliang:{
                    number:true,
                    required:true
                },
                chepai:{
                    required:true
                },
                license:{
                    required:true
                },
                company:{
                    required:true

                }

            }



        })
    })

</script>
</body>
</html>
