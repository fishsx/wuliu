
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>添加司机页面</title>
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
                <span>添加司机</span>
            </div>
            <div class="baBody">
                <form id="myForm" action="/driver/addDriver.do" method="post" >

                    <div class="bbD">
                        司机名称：<input type="text" name="name" class="input3" />
                    </div>

                    <div class="bbD">
                        <label>性别：</label><label ><input type="radio"  name="sex" value="男" id="sex_0" checked />
                        &nbsp;男</label> <label ><input type="radio"  value="女" name="sex"  id="sex_1" />  &nbsp;女</label>
                    </div>

                    <div class="bbD">
                        &nbsp;出生日期：<input class="input3" type="date" name="birthday"/>
                    </div>


                    <div class="bbD">
                        驾驶证类型：<select class="input3" name="licenseType">
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>

                    </select>
                    </div>


                    <div class="bbD">
                       驾驶证号码：<input type="text" name="license" class="input3" />
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
                name:{
                    required:true
                },
                birthday:{
                    required:true,
                    dateISO:true
                },
                licenseType:{
                    required:true
                },
                license:{
                    number:true,
                    required:true,
                    rangelength:[18,18]
                },
                companyId:{
                    required:true
                }

            },
            messages:{
                license:{
                    number:"请填写正确的驾驶证号码",
                    required:true,
                    rangelength:"请填写正确的驾驶证号码"
                }
            }



        })
    })

</script>
</body>
</html>
