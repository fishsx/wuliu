
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>添加员工页面</title>
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
                <span>添加员工</span>
            </div>
            <div class="baBody">
                <form id="myForm" action="/user/addUser.do" method="post" >

                    <div class="bbD">
                        <label>员工昵称：</label><input type="text" name="name" class="input3" />
                    </div>
                    <div class="bbD">
                        员工真实姓名：<input type="text" name="tname" class="input3" />
                    </div>
                    <div class="bbD">
                        <label>员工密码:</label><input type="password" name="pwd" class="input3" />
                    </div>
                    <div class="bbD">
                        员工手机号码:<input type="text" name="phone" class="input3" />
                    </div>
                    <div class="bbD">
                        <label>所属公司：</label><select id="selcompany" class="input3" name="companyId"></select>
                    </div>

                    <div class="bbD">
                        <label>所属部门：</label><select id="seldept" class="input3" name="deptId"></select>
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
                    $("#selcompany").append("<option value = " + val["companyId"] + ">" + val["companyName"] + "</option>");
                });
            }
        })

        $.ajax({
            url:"/data/allDept.json",
            dataType:"json",
            success:function (data) {
                $.each(data,function (index,val) {
                    $("#seldept").append("<option value = " + val["deptId"] + ">" + val["deptName"] + "</option>");

                })
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
