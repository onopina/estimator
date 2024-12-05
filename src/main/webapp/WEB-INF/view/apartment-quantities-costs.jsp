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
    <title>Смета на объект</title>
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
            text-align: center;
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
        .notification {
            background-color: #e7f3fe;
            border-left: 6px solid #2196F3;
            padding: 10px;
            margin-top: 20px;
            color: #0c5460;
        }
    </style>
</head>

<body>


<h2 class="table-title">Смета на объект</h2>

<div class="header">${apt.address}</div>

<table>
    <tr>
        <th>Id объекта</th>
        <th>Категория</th>
        <th>Наименование работы</th>
        <th>Единица измерения</th>
        <th>Количество</th>
        <th>Цена за единицу, руб.</th>
        <th>Общая стоимость, руб.</th>
        <th>Действия</th>
    </tr>

    <tbody>
    <jsp:useBean id="quantitiesAndCosts" scope="request" type="java.util.Map"/>
    <c:forEach var="quantityAndCosts" items="${quantitiesAndCosts}">

        <c:forEach var="cost" items="${quantityAndCosts.value}">

            <c:url var="deleteButton" value="/deleteCostFromApartment/${apt.id}">
                <c:param name="costId" value="${cost.key.id}"/>
            </c:url>

            <tr>
                <c:set var="aptId" value="${quantityAndCosts.key.apartment.id}"/>
                <c:if test="${aptId != previousAptId}">
                    <c:set var="rowspan" value="${fn:length(quantityAndCosts.key.apartment.costs)}"/>
                    <td rowspan="${rowspan}">${aptId}</td>
                    <c:set var="previousAptId" value="${aptId}"/>
                </c:if>

                <c:set var="categoryName" value="${quantityAndCosts.key.category.name}"/>
                <c:if test="${categoryName != previousCategoryName}">
                    <c:set var="rowspan" value="${fn:length(quantityAndCosts.value.keySet())}"/>
                    <td rowspan="${rowspan}">${categoryName}</td>
                    <c:set var="previousCategoryName" value="${categoryName}"/>
                </c:if>

                <td>${cost.key.workTitle}</td>
                <td>${cost.key.category.unit}</td>
                <td>${quantityAndCosts.key.quantity}</td>
                <td style="text-align: right">${cost.key.costValue}</td>
                <td style="text-align: right">${cost.value}</td>
                <td>
                    <input type="button" class="styled-button" value="Удалить"
                           onclick="window.location.href='${deleteButton}'"/>
                </td>
            </tr>
        </c:forEach>
    </c:forEach>

    <tr class="total-row">
        <td colspan="6" style="text-align: right">Общая стоимость по объекту: </td>
        <td style="text-align: right">${totalCost}</td>
    </tr>

    </tbody>

</table>

<br/>

<input type="button" class="styled-button" value="Пересчитать в текущих ценах"
       onclick="window.location.href='/estimator/refreshCosts/${apt.id}?totalCost=${totalCost}'"/>
<input type="button" class="styled-button" value="Добавить работы"
       onclick="window.location.href='/estimator/addNewCostsToApartment/${apt.id}'"/>

<c:if test="${not empty refreshInfo}">
    <div class="notification">
        <p>${refreshInfo}</p>
    </div>
</c:if>

<br/>
<br/>

<button onclick="window.history.back()" class="styled-button">Назад</button>

<br/>
<br/>

<button onclick="window.location.href='/estimator'" class="styled-button">На Главную</button>

</body>

</html>
