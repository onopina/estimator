<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding />

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Цены за единицу работы</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
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

        .styled-link {
            color: #4CAF50;
            text-decoration: none;
            transition: color 0.3s;
        }
        .styled-link:hover {
            color: #45a049;
        }

        .tooltip {
            position: relative;
            display: inline-block;
            cursor: pointer;
        }

        .tooltip .tooltiptext {
            visibility: hidden;
            width: 120px;
            background-color: black;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 5px;
            position: absolute;
            z-index: 1;
            bottom: 100%;
            left: 50%;
            margin-left: -60px;
            opacity: 0;
            transition: opacity 0.3s;
        }

        .tooltip:hover .tooltiptext {
            visibility: visible;
            opacity: 1;
        }

    </style>
</head>

<body>

<h2 class="table-title">Цены за единицу работы</h2>

<table>
    <tr>
        <th>Категория работы</th>
        <th>Наименование работы</th>
        <th>Единица измерения</th>
        <th>Цена, руб.</th>
        <th>Действия</th>
    </tr>

    <jsp:useBean id="allActualCosts" scope="request" type="java.util.List"/>
    <c:forEach var="actualCost" items="${allActualCosts}">

        <c:url var="changeButton" value="/changeCost">
            <c:param name="costId" value="${actualCost.id}"/>
        </c:url>
        <c:url var="deleteButton" value="/deleteCost">
            <c:param name="costId" value="${actualCost.id}"/>
        </c:url>
        <tr>
            <td>${actualCost.category.categoryName.getName()}</td>
            <td>${actualCost.workTitle}</td>
            <td>${actualCost.category.getUnit()}</td>
            <td style="text-align: right">${actualCost.costValue}</td>
            <td>
                <input type="button" class="styled-button" value="Изменить"
                       onclick="window.location.href='${changeButton}'"/>
                <input type="button" class="styled-button" value="Удалить"
                       onclick="window.location.href='${deleteButton}'"/>
            </td>
        </tr>
    </c:forEach>

</table>

<br/>

<input type="button" class="styled-button" value="Добавить цену"
       onclick="window.location.href='addNewCost'"/>

<br/>
<br/>

<button onclick="window.location.href='/estimator'" class="styled-button">На Главную</button>

</body>

</html>