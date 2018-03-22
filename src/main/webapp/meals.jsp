<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" %>
<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 16px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 2px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 20px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 2px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg {
            background-color: #f9f9f9
        }
    </style>
    <style>
        .red {
            color: red; /* Красный цвет выделения */
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Редактировать</h2>

<form action="meals" method="POST">
    <p>№:        <input type="text" name="id"></p>
    <p>Дата:     <input type="text" name="date"></p>
    <p>Описание: <input type="text" name="description"></p>
    <p>Калории:  <input type="text" name="calories"></p>
    <input type="submit" value="Отправить" />
</form>

<h2>Моя еда</h2>
    <table class = "tg">
        <tr>
            <th>№</th>
            <th>Дата/Время</th>
            <th>Описание</th>
            <th>Калории</th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <tr>
                <c:if test="${!meal.isExceed()}">
                <td><c:out value="${meal.getId()}" /></td>
                <td><c:out value="${meal.getDate()}/${meal.getTime()}" /></td>
                <td><c:out value="${meal.getDescription()}" /></td>
                <td><c:out value="${meal.getCalories()}" /></td>
                </c:if>
                <c:if test="${meal.isExceed()}">
                <td><span class="red"><c:out value="${meal.getId()}" /></span></td>
                <td><span class="red"><c:out value="${meal.getDate()}/${meal.getTime()}" /></span></td>
                <td><span class="red"><c:out value="${meal.getDescription()}" /></span></td>
                <td><span class="red"><c:out value="${meal.getCalories()}" /></span></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
