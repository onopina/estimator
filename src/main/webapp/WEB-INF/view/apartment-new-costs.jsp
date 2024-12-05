<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding />

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Новые работы</title>
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
        .form-container h2 {
            color: #4CAF50;
            text-align: center;
        }
        .form-section {
            margin-bottom: 20px;
        }
        .checkbox-item {
            margin-bottom: 5px;
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
            display: block;
            margin-top: 10px;
        }
        .styled-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h2>Новые работы</h2>

<c:if test="${empty availableCosts}">
    <h3>Нет доступных для добавления работ</h3>
</c:if>

<c:if test="${not empty availableCosts}">
    <h3>Выберите из списка доступных работ:</h3>

    <%--@elvariable id="selectedCosts" type=""--%>
    <form:form action="/estimator/saveNewCostsToApartment/${apt.id}" modelAttribute="selectedCosts" class="form-container">
        <div class="form-section">
            <c:forEach var="cost" items="${availableCosts}">
                <div class="checkbox-item">
                    <form:checkbox path="selectedCostIds" value="${cost.id}"/> ${cost.workTitle}
                </div>
            </c:forEach>
        </div>

        <div class="form-section">
            <button type="submit" class="styled-button">Добавить</button>
        </div>
    </form:form>
</c:if>


<br/>

<button onclick="window.history.back()" class="styled-button">Назад</button>

</body>

</html>