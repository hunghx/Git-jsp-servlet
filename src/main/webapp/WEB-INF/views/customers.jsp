<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 10/3/2024
  Time: 9:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<h1>Danh sách khách hàng</h1>
<a href="/customer/add" class="btn btn-info">Add new Customer</a>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">#No.</th>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">Address</th>
        <th scope="col" colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="cus" varStatus="loop">
        <tr>
            <th scope="row">${loop.count}</th>
            <td>${cus.name}</td>
            <td>${cus.email}</td>
            <td>${cus.address}</td>
            <td><a class="btn btn-warning" href="/customer/edit?id=${cus.id}">Edit</a></td>
            <td><a href="/customer/delete?id=${cus.id}" onclick="return confirm('Bạn có chắc chắn muốn xoa ko')" class="btn btn-danger">Delete</a></td>
        </tr>
    </c:forEach>


    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
