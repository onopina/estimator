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
    <title>Мои объекты</title>
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

<h2 class="table-title">Мои объекты</h2>

<table>
    <tr>
        <th>Адрес</th>
        <th>Дата подписания договора</th>
        <th>Площадь объекта</th>
        <th>Действия</th>
    </tr>

    <jsp:useBean id="allApts" scope="request" type="java.util.List"/>
    <c:forEach var="apt" items="${allApts}">

        <c:url var="updateButton" value="/updateApartment">
            <c:param name="aptId" value="${apt.id}"/>
        </c:url>
        <c:url var="deleteButton" value="/deleteApartment">
            <c:param name="aptId" value="${apt.id}"/>
        </c:url>

        <tr>
            <td>
                <div class="tooltip">
                    <a href="<c:url value="/doActions/${apt.id}"/>" target="_blank" class="styled-link">${apt.address}</a>
                    <span class="tooltiptext">Показать характеристики и смету</span>
                </div>
            </td>

            <td><fmt:formatDate value="${apt.startDate}" pattern="dd MMM yyyy 'г.'" /></td>
            <td>${apt.area} кв.м</td>
            <td>
                <input type="button" class="styled-button" value="Изменить"
                       onclick="window.location.href='${updateButton}'"/>
                <input type="button" class="styled-button" value="Удалить"
                       onclick="window.location.href='${deleteButton}'"/>
            </td>
        </tr>
    </c:forEach>

</table>

<br/>

<input type="button" class="styled-button" value="Добавить объект"
       onclick="window.location.href='addNewApartment'"/>

<br/>
<br/>

<button onclick="window.location.href='/estimator'" class="styled-button">На Главную</button>

</body>

</html>

