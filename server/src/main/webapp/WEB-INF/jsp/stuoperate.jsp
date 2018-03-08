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

<form name="joinCourse" action="./joinCourse" method="get" onsubmit="return checknull()">
    加入课程：<br>
    课程id：<input type="text" name="courseid">
    课程密码：<input type="text" name="password">
    用户id：<input type="text" name="userid">
    <input type="submit">
</form>

<form name="quitCourse" action="./quitCourse" method="get" onsubmit="return checknull()">
    退出课程：<br>
    用户id：<input type="text" name="userid">
    课程id：<input type="text" name="courseid">
    <input type="submit">
</form>

<form name="listMyCourse" action="./listMyCourse" method="get" onsubmit="return checknull()">

    列出我的课程：<br>
    用户id：<input type="text" name="userid">
    <input type="submit">
</form>

<form name="putforwardQues" action="./putforwardQues" method="get" onsubmit="return checknull()">

    （暂不可用）<br>

    提出问题：
    <input type="submit">
</form>

<form name="listAllOutline" action="./listAllOutline" method="get" onsubmit="return checknull()">

    列出课程提纲：<br>
    课程id：<input type="text" name="courseid">
    <input type="submit">
</form>

<form name="listOutline" action="./listOutline" method="get" onsubmit="return checknull()">

    列出提纲：<br>
    课程id：<input type="text" name="courseid">
    章节：<input type="text" name="chapterid">
    <input type="submit">
</form>

<form name="courseDetail" action="./courseDetail" method="get" onsubmit="return checknull()">

    列出课程详细信息：<br>
    用户id：<input type="text" name="userid">
    课程id：<input type="text" name="courseid">

    <input type="submit">
</form>

<form name="markUnknown" action="./markUnknown" method="get" onsubmit="return checknull()">

    标记不懂：<br>
    课程id：<input type="text" name="courseid">
    章节：<input type="text" name="chapterid">
    <input type="submit">
</form>

<form name="listQuiz" action="./listQuiz" method="get" onsubmit="return checknull()">

    列出题目：<br>
    课程id：<input type="text" name="courseid">
    <input type="submit">
</form>

<form name="ansQuiz" action="./ansQuiz" method="get" onsubmit="return checknull()">

    回答问题：<br>
    用户id：<input type="text" name="userid">
    题号：<input type="text" name="quesid">
    答案：<input type="text" name="answer">
    <input type="submit">
</form>

<form name="ansQuizList" action="./ansQuizList" method="get" onsubmit="return checknull()">

    回答一堆问题：<br>
    用户id：<input type="text" name="userid">
    题号：<input type="text" name="quesid">
    答案：<input type="text" name="answer">
    <input type="submit">
</form>

<form name="listMyAns" action="./listMyAns" method="get" onsubmit="return checknull()">

    列出我的回答：<br>
    用户id：<input type="text" name="userid">
    课程id：<input type="text" name="courseid">
    章节：<input type="text" name="chapterid">
    <input type="submit">
</form>

<form name="mySign" action="./mySign" method="get" onsubmit="return checknull()">

    （暂不可用）<br>
    我的签到：<br>
    <input type="submit">
</form>


</body>
</html>
