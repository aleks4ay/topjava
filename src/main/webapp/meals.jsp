<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
    <link href="css/meals.css" rel="stylesheet" type="text/css" >
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Список еды:</h2>
    <br/>
    <table style="font-size: 18px; text-align: center">
        <tr style="height: 1.8em">
            <th width="180">Дата/Время</th>
            <th width="400">Описание</th>
            <th width="80">Калории</th>
        </tr>
        <c:forEach var="mealTo" items="${mealsTo}">
            <tr>
                <td class="${mealTo.excess ? "red" : "green"}">${mealTo.dateTimeString}</td>
                <td class="${mealTo.excess ? "red" : "green"}" style="text-align: left">${mealTo.description}</td>
                <td class="${mealTo.excess ? "red" : "green"}">${mealTo.calories}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>