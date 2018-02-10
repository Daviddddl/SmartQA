<%--
  Created by IntelliJ IDEA.
  User: didonglin
  Date: 2018/2/10
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师操作页面</title>
</head>
<body>

<form name="addOutline" action="/teaoperate/addOutline" method="get" onsubmit="return checknull()">

    课程id：<input type="text" name="courseid">
    章节：<input type="text" name="chapters">
    提纲：<input type="textarea" cols="30" rows="10" name="content">
    <input type="submit">
</form>

</body>
</html>
