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

    加入提纲：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    提纲：<input type="textarea" cols="30" rows="10" name="content">
    <input type="submit">
</form>

<form name="deleteOutline" action="/teaoperate/deleteOutline" method="get" onsubmit="return checknull()">

    删除提纲：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    <input type="submit">
</form>

<form name="changeOutline" action="/teaoperate/changeOutline" method="get" onsubmit="return checknull()">
    （暂不可用）<br>

    改变提纲：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    提纲：<input type="textarea" cols="30" rows="10" name="content">
    <input type="submit">
</form>

<form name="findOutline" action="/teaoperate/findOutline" method="get" onsubmit="return checknull()">

    搜索提纲：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    <input type="submit">
</form>

<form name="addQues" action="/teaoperate/addQues" method="get" onsubmit="return checknull()">

    添加问题：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    题目：<input type="textarea" cols="30" rows="10" name="ques">
    答案：<input type="textarea" cols="30" rows="10" name="ans">
    <input type="submit">
</form>

<form name="deleteQues" action="/teaoperate/deleteQues" method="get" onsubmit="return checknull()">

    删除问题：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    <input type="submit">
</form>

<form name="changeQues" action="/teaoperate/changeQues" method="get" onsubmit="return checknull()">

    （暂不可用）<br>

    改变问题：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    提纲：<input type="textarea" cols="30" rows="10" name="content">
    <input type="submit">
</form>

<form name="findQues" action="/teaoperate/findQues" method="get" onsubmit="return checknull()">

    寻找问题：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    <input type="submit">
</form>

<form name="checkQues" action="/teaoperate/checkQues" method="get" onsubmit="return checknull()">

    检查回答：<br>
    题目id：<input type="text" name="quesid">
    学生答案：<input type="text" name="stuans">
    <input type="submit">
</form>

<form name="checkSign" action="/teaoperate/checkSign" method="get" onsubmit="return checknull()">

    （暂不可用）<br>

    检查签到：<br>
    课程名：<input type="text" name="name">
    <input type="submit">
</form>

<form name="getRandStu" action="/teaoperate/getRandStu" method="get" onsubmit="return checknull()">

    随机学生：<br>
    课程名：<input type="text" name="name">
    <input type="submit">
</form>

<form name="getStu" action="/teaoperate/getStu" method="get" onsubmit="return checknull()">

    全部学生：<br>
    课程名：<input type="text" name="name">
    <input type="submit">
</form>

</body>
</html>
