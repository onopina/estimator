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
    <title>Итоговые цены по объектам</title>
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
<h2 class="table-title">Итоговые цены по объектам</h2>
<table>
    <thead>
    <tr>
        <th>Id объекта</th>
        <th>Адрес</th>
        <th>Итоговая стоимость, руб.</th>
        <th>Детали</th>
    </tr>
    </thead>
    <tbody>
    <!-- Основной цикл по totalCostsPerApartments -->
    <c:forEach var="totalCostEntry" items="${totalCostsPerApartments}">
        <tr>
            <td>${totalCostEntry.key.id}</td>
            <td>${totalCostEntry.key.address}</td>
            <td style="text-align: right">${totalCostEntry.value}</td>
            <td>
                <details>
                    <summary>Показать детали</summary>
                    <table>
                        <thead>
                        <tr>
                            <th>Наименование работы</th>
                            <th>Количество</th>
                            <th>Единица измерения</th>
                            <th>Цена за единицу, руб.</th>
                            <th>Общая стоимость, руб.</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- Вложенный цикл по allQuantitiesCostsAndTotalCosts для каждого id объекта -->
                        <c:forEach var="quantityCostAndTotalCosts" items="${allQuantitiesCostsAndTotalCosts}">
                            <!-- Проверка на совпадение id объекта -->
                            <c:if test="${quantityCostAndTotalCosts.key.apartment.id == totalCostEntry.key.id}">
                                <c:forEach var="costAndTotalCosts" items="${quantityCostAndTotalCosts.value}">
                                    <tr>
                                        <td>${costAndTotalCosts.key.workTitle}</td>
                                        <td>${quantityCostAndTotalCosts.key.quantity}</td>
                                        <td>${costAndTotalCosts.key.category.unit}</td>
                                        <td style="text-align: right">${costAndTotalCosts.key.costValue}</td>
                                        <td style="text-align: right">${costAndTotalCosts.value}</td>
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

    <tr class="total-row">
        <td colspan="2" style="text-align: right">Итоговая стоимость по всем объектам, руб.: </td>
        <td style="text-align: right">${totalCost}</td>
    </tr>

    </tbody>
</table>

<br/>

<button onclick="window.history.back()" class="styled-button">Назад</button>

<br/>
<br/>

<button onclick="window.location.href='/estimator'" class="styled-button">На Главную</button>

</body>

</html>



