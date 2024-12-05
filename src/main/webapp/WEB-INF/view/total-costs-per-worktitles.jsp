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
    <title>Итоговые цены по видам работ</title>
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
<h2 class="table-title">Итоговые цены по видам работ</h2>
<table>
    <thead>
    <tr>
        <th>Категория</th>
        <th>Наименование работы</th>
        <th>Общее количество</th>
        <th>Единица измерения</th>
        <th>Итоговая стоимость, руб.</th>
        <th>Детали</th>
    </tr>
    </thead>
    <tbody>
    <!-- Основной цикл по totalCostsPerWorkTitles -->
    <c:forEach var="totalCostEntry" items="${totalCostsPerWorkTitles}">
        <tr>
            <td>${totalCostEntry.key.value.name}</td>
            <td>${totalCostEntry.key.key}</td>
            <td>${totalCostEntry.value.key}</td>
            <td>${totalCostEntry.key.value.unit}</td>
            <td style="text-align: right">${totalCostEntry.value.value}</td>
            <td>
                <details>
                    <summary>Показать детали</summary>
                    <table>
                        <thead>
                        <tr>
                            <th>Адрес объекта</th>
                            <th>Количество</th>
                            <th>Единица измерения</th>
                            <th>Цена за единицу, руб.</th>
                            <th>Общая стоимость, руб.</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- Вложенный цикл по allCostsQuantitiesAndTotalCosts для каждой строки цены -->
                        <c:forEach var="сostQuantitiesAndTotalCosts" items="${allCostsQuantitiesAndTotalCosts}">
                            <!-- Проверка на совпадение наименования работы -->
                            <c:if test="${сostQuantitiesAndTotalCosts.key.workTitle == totalCostEntry.key.key}">
                                <c:forEach var="quantityAndTotalCosts" items="${сostQuantitiesAndTotalCosts.value}">
                                    <tr>
                                        <td>${quantityAndTotalCosts.key.apartment.address}</td>
                                        <td>${quantityAndTotalCosts.key.quantity}</td>
                                        <td>${quantityAndTotalCosts.key.category.getUnit()}</td>
                                        <td style="text-align: right">${сostQuantitiesAndTotalCosts.key.costValue}</td>
                                        <td style="text-align: right">${quantityAndTotalCosts.value}</td>
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
        <td colspan="3" style="text-align: right">Итоговая стоимость по всем видам работ, руб.: </td>
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




