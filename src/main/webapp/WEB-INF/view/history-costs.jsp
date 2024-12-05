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
    <title>История изменения цен</title>
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
        .total-row {
            font-weight: bold;
            background-color: #e0ffe0;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        details {
            margin-top: 10px;
        }
        summary {
            cursor: pointer;
            font-weight: bold;
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
<h2 class="table-title">История изменения цен</h2>
<table>
    <thead>
    <tr>
        <th>Наименование работы</th>
        <th>Единица измерения</th>
        <th>Актуальная цена за единицу, руб.</th>
        <th>Детали</th>
    </tr>
    </thead>
    <tbody>
    <!-- Основной цикл по allLatestCosts -->
    <c:forEach var="latestCost" items="${allLatestCosts}">
        <tr>
            <td>${latestCost.workTitle}</td>
            <td>${latestCost.category.unit}</td>
            <td style="text-align: right">${latestCost.costValue}</td>
            <td>
                <details>
                    <summary>Показать историю цены</summary>
                    <table>
                        <thead>
                        <tr>
                            <th>Цена за единицу, руб.</th>
                            <th>Дата</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- Вложенный цикл по allWorkTitlesDatesAndCostValues для каждого workTitle цены -->
                        <c:forEach var="workTitleDatesAndCostValues" items="${allWorkTitlesDatesAndCostValues}">
                            <c:if test="${workTitleDatesAndCostValues.key.equals(latestCost.workTitle)}">
                                <c:forEach var="dateAndCostValue" items="${workTitleDatesAndCostValues.value}">
                                    <tr>
                                        <td style="text-align: right">${dateAndCostValue.value}</td>
                                        <td><fmt:formatDate value="${dateAndCostValue.key}" pattern="dd MMM yyyy 'г.' HH:mm" /></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </details>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<br/>

<button onclick="window.history.back()" class="styled-button">Назад</button>

<br/>
<br/>

<button onclick="window.location.href='/estimator'" class="styled-button">На Главную</button>
</body>

</html>



