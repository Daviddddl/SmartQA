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

<form name="listMyOwnCourse" action="./listMyOwnCourse" method="get" onsubmit="return checknull()">

    列出课程：<br>
    用户id：<input type="text" name="userid">
    <input type="submit">
</form>

<form name="addOutline" action="./addOutline" method="get" onsubmit="return checknull()">

    加入提纲：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    提纲：<input type="textarea" cols="30" rows="10" name="content">
    <input type="submit">
</form>

<form name="deleteOutline" action="./deleteOutline" method="get" onsubmit="return checknull()">

    删除提纲：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    <input type="submit">
</form>

<form name="changeOutline" action="./changeOutline" method="get" onsubmit="return checknull()">
    （暂不可用）<br>

    改变提纲：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    提纲：<input type="textarea" cols="30" rows="10" name="content">
    <input type="submit">
</form>

<form name="findOutline" action="./findOutline" method="get" onsubmit="return checknull()">

    搜索提纲：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    <input type="submit">
</form>

<form name="addQues" action="./addQues" method="get" onsubmit="return checknull()">

    添加问题：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    题目：<input type="textarea" cols="30" rows="10" name="ques">
    答案：<input type="textarea" cols="30" rows="10" name="ans">
    <input type="submit">
</form>

<form name="deleteQues" action="./deleteQues" method="get" onsubmit="return checknull()">

    删除问题：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    <input type="submit">
</form>

<form name="changeQues" action="./changeQues" method="get" onsubmit="return checknull()">

    （暂不可用）<br>

    改变问题：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    提纲：<input type="textarea" cols="30" rows="10" name="content">
    <input type="submit">
</form>

<form name="findQues" action="./findQues" method="get" onsubmit="return checknull()">

    寻找问题：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    <input type="submit">
</form>

<form name="listQues" action="./listQues" method="get" onsubmit="return checknull()">

    列出题目：<br>
    课程名称：<input type="text" name="name">
    章节：<input type="text" name="chapters">
    <input type="submit">
</form>

<form name="quizQues" action="./quizQues" method="get" onsubmit="return checknull()">

    发布题目：<br>
    题号：<input type="text" name="quesid">
    <input type="submit">
</form>


<form name="checkQues" action="./checkQues" method="get" onsubmit="return checknull()">

    检查回答：<br>
    题目id：<input type="text" name="quesid">
    <input type="submit">
</form>

<form name="checkSign" action="./checkSign" method="get" onsubmit="return checknull()">

    （暂不可用）<br>

    检查签到：<br>
    课程名：<input type="text" name="name">
    <input type="submit">
</form>

<form name="getRandStu" action="./getRandStu" method="get" onsubmit="return checknull()">

    随机学生：<br>
    课程名：<input type="text" name="name">
    <input type="submit">
</form>

<form name="getStu" action="./getStu" method="get" onsubmit="return checknull()">

    全部学生：<br>
    课程名：<input type="text" name="name">
    <input type="submit">
</form>

</body>
</html>
