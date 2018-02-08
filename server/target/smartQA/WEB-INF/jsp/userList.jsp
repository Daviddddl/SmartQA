<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<table border="1">
    <tr>
        <th>昵称</th>
        <th>备注</th>

    </tr>
<c:forEach items="${userList}" var="user">

        <tr>
            <td>${user.nickName}</td>
            <td>${user.remark}</td>
        </tr>


</c:forEach>
</table>
</body>
</html>
