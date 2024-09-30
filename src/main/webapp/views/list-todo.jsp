<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 9/30/2024
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sach công việc</title>
</head>
<body>
<h1>Danh sach cv</h1>
<a href="/todo?action=ADD">Them moi cong viec</a>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Content</th>
        <th>Status</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${todos}" var="todo">
        <tr>
            <td>${todo.id}</td>
            <td>${todo.getContent()}</td>
            <td>${todo.status?"xong":"chưa xong"}</td>
            <td><a href="/todo?action=EDIT&id=${todo.id}">Sửa</a></td>
            <td><a href="/todo?action=DELETE&id=${todo.id}">Xóa</a></td>
        </tr>
    </c:forEach>

    </tbody>

</table>
</body>
</html>
