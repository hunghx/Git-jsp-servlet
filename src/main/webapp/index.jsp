<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="/home" method="post">
    <input type="text" name="id"><br>
    <input type="text" name="name"><br>
    <button type="submit">Gui</button>
</form>

<%-- thư viên jstl - hỗ trợ cú phap trong jsp--%>
<%--Câu lệnh điều kiện if - switch--%>
<c:if test="${4>3}">
    <p>4>3</p>
</c:if>
<c:if test="${3>4}">
    <p>3>4</p>
</c:if>

<c:set var="dtb" value="6"/>
<c:set var="array" value="<%=new int[]{1,3,6,9}%>"/>

<c:choose>
    <c:when test="${dtb<5}">
        <p>trung binh</p>
    </c:when>
    <c:when test="${dtb<6.5}">
        <p>khá</p>
    </c:when>
    <c:otherwise>
        <p>ko xác định</p>
    </c:otherwise>
</c:choose>

<%-- vòng lặp --%>
<%-- forr each : for(int x : arr)--%>

<c:forEach items="${array}" var="x" varStatus="loop">
<%--    khối code lặp --%>
    <li>Giá tri : ${x}</li>
</c:forEach>

<c:forEach begin="0" end="10" step="1" var="x">
    <li>${x}</li>
</c:forEach>
<a href="/todo?action=GETALL">Danh sách công việc</a>

</body>
</html>