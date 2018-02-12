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

<form name="addUser" action="./addUser" method="get" onsubmit="return checknull()">

    昵称：<input type="text" name="nickName">
    备注：<input type="text" name="remark">
    性别：<input type="text" name="gender">
    语言：<input type="text" name="lang">
    城市：<input type="text" name="city">
    省会：<input type="text" name="province">
    国家：<input type="text" name="country">
    URL：<input type="text" name="avatarUrl">
    加入课程：<input type="text" name="joinCourse">
    <input type="submit">
</form>
</body>
</html>
