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

<form name="joinCourse" action="/stuoperate/joinCourse" method="get" onsubmit="return checknull()">
    加入课程：<br>
    课程名称：<input type="text" name="name">
    课程密码：<input type="text" name="password">
    用户昵称：<input type="text" name="nickName">
    用户备注：<input type="text" name="remark">
    <input type="submit">
</form>

<form name="quitCourse" action="/stuoperate/quitCourse" method="get" onsubmit="return checknull()">
    退出课程：<br>
    用户昵称：<input type="text" name="nickName">
    用户备注：<input type="text" name="remark">
    课程名称：<input type="text" name="name">
    <input type="submit">
</form>

<form name="listMyCourse" action="/stuoperate/listMyCourse" method="get" onsubmit="return checknull()">

    列出我的课程：<br>
    用户昵称：<input type="text" name="nickName">
    用户备注：<input type="text" name="remark">
    <input type="submit">
</form>

<form name="putforwardQues" action="/stuoperate/putforwardQues" method="get" onsubmit="return checknull()">

    （暂不可用）<br>

    提出问题：
    <input type="submit">
</form>

<form name="markUnknown" action="/stuoperate/markUnknown" method="get" onsubmit="return checknull()">

    标记不懂：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    <input type="submit">
</form>

<form name="mySign" action="/stuoperate/mySign" method="get" onsubmit="return checknull()">

    （暂不可用）<br>
    我的签到：<br>
    <input type="submit">
</form>


</body>
</html>
