<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 9/30/2024
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/todo" method="post">
    <input type="text" name="id" readonly value="${todo.id}">
    <input type="text" name="content" value="${todo.content}">
    <select name="status">
        <option ${todo.status?'selected':''} value="true">Xong</option>
        <option ${!todo.status?'selected':''} value="false">Chưa xong</option>
    </select>
    <input type="submit" value="UPDATE" name="action">
</form>
</body>
</html>
