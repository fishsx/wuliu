<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限管理</title>
</head>
<body>

        <c:forEach items="${roleUserANDRootList}" var="rurl">
                ${rurl[0].uname}&nbsp;

                <c:forEach items="${role}" var="ro">
                <input type="checkbox" name="root" value="1"
                   ${(fn:contains(rurl[1],ro.roleId))?  'checked':''} >${ro.roleName}
                </c:forEach><br>
        </c:forEach>
</body>
</html>
