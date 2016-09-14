<%--
  Created by IntelliJ IDEA.
  User: dead_rabbit
  Date: 14.09.2016
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>WEB LIBRARY</title>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/views/CreateBook.jsp">Добавить книгу</a>
<a href="${pageContext.servletContext.contextPath}/views/SearchBook.jsp">Найти книгу по названию</a>
<br/><br>
<h1>Список книг</h1>

<table border="1">
    <tr>
        <td>ID</td>
        <td>Название</td>
        <td>Автор</td>
        <td>Описание</td>
        <td>Действие</td>
    </tr>
    <c:forEach items="${books}" var="book" varStatus="status">
        <tr valign="top">
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.description}</td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/edit?id=${book.id}">Редактировать</a>
                <a href="${pageContext.servletContext.contextPath}/delete?id=${book.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>



</body>
</html>
