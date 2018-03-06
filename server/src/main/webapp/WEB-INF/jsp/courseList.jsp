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

<form name="addCourse" action="./addCourse" method="get" onsubmit="return checknull()">

    名称：<input type="text" name="name">
    密码：<input type="text" name="password">
    教师：<input type="text" name="teacher">
    容量：<input type="text" name="capacity">
    学生数量：<input type="text" name="stunum">
    起始时间：<input type="text" name="startdate">
    终止时间：<input type="text" name="enddate">
    活动：<input type="text" name="isactive">
    <input type="submit">
</form>

删除：
<form name="deleteCourse" action="./deleteCourse" method="get" onsubmit="return checknull()">

    课程id：<input type="text" name="courseid">
    密码：<input type="text" name="password">
    <input type="submit">
</form>

查询：
<form name="getCourse" action="./getCourse" method="get" onsubmit="return checknull()">

    课程id：<input type="text" name="courseid">
    <input type="submit">
</form>
</body>
</html>
