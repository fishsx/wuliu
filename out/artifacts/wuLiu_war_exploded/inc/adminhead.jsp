<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>头部</title>
<link rel="stylesheet" type="text/css" href="../css/public.css" />
	
</head>

<body>
	<!-- 头部 -->
	<div class="head">
		<div class="headL">
			<p class="font_white">物流管理系统</p>
		</div>
		<div class="headR">
			<p class="p1">
				欢迎，${sessionScope.user.tname} ${sessionScope.role.roleName}
			</p>
			<p class="p2">
				<a href="/user/userQuit.do" id="btnQuit"  target="_top" >安全注销</a>
			</p>
		</div>

	</div>


</body>
</html>