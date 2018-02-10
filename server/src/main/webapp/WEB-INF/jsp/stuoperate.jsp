<%--
  Created by IntelliJ IDEA.
  User: didonglin
  Date: 2018/2/10
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生操作页面</title>
</head>
<body>

<form name="addCourse" action="/stuoperate/joinCourse" method="get" onsubmit="return checknull()">

    课程名称：<input type="text" name="name">
    课程密码：<input type="text" name="password">
    学生学号：<input type="text" name="stuID">
    <input type="submit">
</form>

</body>
</html>
