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
    <title>Статистические данные</title>
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
        .container {
            display: flex;
            justify-content: center;
            gap: 20px;
        }
        .link {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .link:hover {
            background-color: #45a049;
        }
        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="header">Статистические данные</div>

<div class="container">
    <a href="<c:url value='/showTotalCostsPerApartments'/>" class="link">Итоговые цены по объектам</a>
    <a href="<c:url value='/showTotalCostsPerWorkTitles'/>" class="link">Итоговые цены по видам работ</a>
</div>

<div class="button-container">
    <a href="<c:url value='/showHistoryOfCosts'/>" class="link">История изменения цен</a>
</div>

</body>

</html>
