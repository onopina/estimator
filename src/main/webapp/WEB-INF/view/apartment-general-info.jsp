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
    <title>Инфо об объекте</title>
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
            text-align: left;
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
        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }
        .form-label {
            font-weight: bold;
            margin-top: 10px;
        }
        .styled-input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
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
        .form-group {
            margin-bottom: 20px;
        }
        .alert {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
        }
        .alert-danger {
            color: #d8000c;
            background-color: #f4c4c4;
            border-color: #d8000c;
        }

    </style>
</head>

<body>

<h2>Инфо об объекте</h2>

<%--@elvariable id="apt" type=""--%>
<form:form action="saveApartment" modelAttribute="apt" class="form-container" >

    <form:hidden path="id"/>

    <div class="form-group">
        <label class="form-label">Адрес объекта</label>
        <form:input path="address" class="styled-input"/>
        <form:errors path="address" class="alert alert-danger" />
    </div>

    <div class="form-group">
        <label class="form-label">Площадь объекта (кв.м)</label>
        <form:input path="area" class="styled-input"/>
        <form:errors path="area" class="alert alert-danger" />
    </div>

    <div class="form-group">
        <label class="form-label">Дата подписания договора</label>
        <form:input type="date" path="startDate" class="styled-input"/>
        <form:errors path="startDate" class="alert alert-danger" />
    </div>

    <input type="submit" class="styled-button" value="OK">

</form:form>

<br/>

<button onclick="window.history.back()" class="styled-button">Назад</button>

</body>

</html>