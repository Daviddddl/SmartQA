<%--
  Created by IntelliJ IDEA.
  User: didonglin
  Date: 2018/1/30
  Time: 09:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>HomePage</title>
  </head>
  <body>
  HomePage!
  <form action="servlet/ServiceServlet" method="get">

    <input type="text" name="nickname">
    <input type="text" name="remark">
    <br>
    <input type="submit">
  </form>
  </body>
</html>
