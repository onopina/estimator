
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding />

<!DOCTYPE html>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Характеристики объекта</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
        }
        .header {
            text-align: center;
            font-size: 28px;
            color: #4CAF50;
            margin-bottom: 20px;
        }
        .table-title {
            text-align: center; /
            font-size: 24px;
            color: #4CAF50;
            margin-bottom: 20px;
            text-transform: uppercase;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .total-row {
            font-weight: bold;
            background-color: #e0ffe0;
        }
        @media (max-width: 600px) {
            table {
                width: 100%;
            }
        }
        .styled-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        .styled-button:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>

<h2 class="table-title">Характеристики объекта</h2>

<div class="header">${apt.address}</div>

<table>
    <tr>
        <th>Категория</th>
        <th>Единица измерения</th>
        <th>Количество</th>
        <th>Действия</th>
    </tr>

    <tbody>
    <jsp:useBean id="categoriesAndQuantitiesById" scope="request" type="java.util.List"/>
    <c:forEach var="categoryAndQuantity" items="${categoriesAndQuantitiesById}">

        <c:url var="updateButton" value="/editCategoryQuantity/${apt.id}">
            <c:param name="categoryId" value="${categoryAndQuantity.id.categoryId}"/>
        </c:url>

        <c:url var="deleteButton" value="/deleteCategoryQuantity/${apt.id}">
            <c:param name="categoryId" value="${categoryAndQuantity.id.categoryId}"/>
        </c:url>

        <tr>
            <td>${categoryAndQuantity.category.getName()}</td>
            <td>${categoryAndQuantity.category.getUnit()}</td>
            <td>${categoryAndQuantity.quantity}</td>
            <td>
                <input type="button" class="styled-button" value="Изменить"
                       onclick="window.location.href='${updateButton}'"/>
                <input type="button" class="styled-button" value="Удалить"
                       onclick="window.location.href='${deleteButton}'"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>

<input type="button" class="styled-button" value="Добавить характеристику"
       onclick="window.location.href='/estimator/addNewCategoryQuantity/${apt.id}'"/>
<input type="button" class="styled-button" value="Составить смету"
       onclick="window.location.href='/estimator/quantitiesAndCostsById/${apt.id}'"/>
<br/>
<br/>

<button onclick="window.history.back()" class="styled-button">Назад</button>

<br/>
<br/>
<button onclick="window.location.href='/estimator'" class="styled-button">На Главную</button>

</body>

</html>

