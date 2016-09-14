<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="POST">
    <table>

        <tr>
            <td align="right" >Название: </td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td align="right" >Автор: </td>
            <td>
                <input type="text" name="author">
            </td>
        </tr>
        <tr>
            <td align="right" >Описание: </td>
            <td>
                <input type="text" name="description">
            </td>
        </tr>
        <tr>
            <td><input type="submit" align="center" value="Сохранить"/></td>
        </tr>

    </table>
</form>
</body>
</html>