<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<%--    <meta charset="UTF-8">--%>
    <title>Meals</title>
    <link href="css/meals.css" rel="stylesheet" type="text/css" >
    <link href="css/popUp.css" rel="stylesheet" type="text/css" >
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Список еды:</h2>
    <br/>
    <table style="font-size: 18px; text-align: center">
        <tr style="height: 1.8em">
            <th width="40">Id</th>
            <th width="180">Дата/Время</th>
            <th width="500">Описание</th>
            <th width="90">Калории</th>
            <th width="90">Редакти- ровать</th>
            <th width="90">Удалить</th>
        </tr>
        <c:forEach var="mealTo" items="${mealsTo}">
            <tr class="${mealTo.excess ? "red" : "green"}">
                <td>${mealTo.id}</td>
                <td>${mealTo.dateTimeString}</td>
                <td style="text-align: left">${mealTo.description}</td>
                <td>${mealTo.calories}</td>
                <td><a href="meals?action=update&id=${mealTo.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${mealTo.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

    <h3><a href="meals?action=insert">Add new meals</a></h3>

    <% if (request.getParameter("action") != null && request.getParameter("action").equals("insert")) { %>
        <%@include file="createMeal.jsp"%>
    <% } %>

    <% if (request.getParameter("action") != null && request.getParameter("action").equals("update")) { %>
        <%@include file="updateMeal.jsp"%>
    <% } %>
</body>
</html>