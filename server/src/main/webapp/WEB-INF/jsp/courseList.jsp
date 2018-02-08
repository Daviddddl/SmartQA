<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: didonglin
  Date: 2018/2/8
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程列表</title>
</head>
<body>
<table border="1">
    <tr>
        <th>名称</th>
        <th>密码</th>

    </tr>
    <c:forEach items="${courseList}" var="course">

        <tr>
            <td>${course.name}</td>
            <td>${course.password}</td>
        </tr>


    </c:forEach>
</table>
</body>
</html>
