<%--
  Created by IntelliJ IDEA.
  User: dead_rabbit
  Date: 15.09.2016
  Time: 1:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<h3>Поиск книг</h3>
<hr>
<a href="${pageContext.servletContext.contextPath}/">На главную страницу</a>
<br>
<br>
<form action="${pageContext.servletContext.contextPath}/search" method="POST">
    <p><b>Введите название книги:</b><br>
        <input type="text" name="search" size="40"><input type="submit" value="Поиск">
    </p>
</form>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Название</td>
        <td>Автор</td>
        <td>Описание</td>
        <td>Действие</td>
    </tr>
    <c:forEach items="${searchBooks}" var="findBooks" varStatus="status">
        <tr valign="top">
            <td>${findBooks.id}</td>
            <td>${findBooks.name}</td>
            <td>${findBooks.author}</td>
            <td>${findBooks.description}</td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/edit?id=${findBooks.id}">Редактировать</a>
                <a href="${pageContext.servletContext.contextPath}/delete?id=${findBooks.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
