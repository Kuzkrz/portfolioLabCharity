<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Formularz przekazywania darów</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<h2>Formularz przekazywania darów</h2>

<form:form method="POST" modelAttribute="donation" action="/saveDonation">

    <div>
        <label for="categories">Kategorie darów:</label><br>
        <form:checkboxes path="categories" items="${categories}" />
    </div>

    <div>
        <label for="institution">Instytucja:</label><br>
        <form:select path="institution">
            <form:option value="">Wybierz instytucję</form:option>
            <form:options items="${institutions}" itemValue="id" itemLabel="name" />
        </form:select>
    </div>

    <div>
        <label for="zipCode">Kod pocztowy:</label><br>
        <form:input path="zipCode" />
    </div>

    <div>
        <label for="street">Ulica:</label><br>
        <form:input path="street" />
    </div>

    <div>
        <label for="city">Miasto:</label><br>
        <form:input path="city" />
    </div>

    <div>
        <label for="quantity">Liczba worków:</label><br>
        <form:input path="quantity" type="number" />
    </div>

    <div>
        <label for="pickUpComment">Komentarz:</label><br>
        <form:textarea path="pickUpComment" />
    </div>

    <div>
        <label for="pickUpDate">Data odbioru:</label><br>
        <form:input path="pickUpDate" type="date" />
    </div>

    <div>
        <label for="pickUpTime">Godzina odbioru:</label><br>
        <form:input path="pickUpTime" type="time" />
    </div>

    <div>
        <button type="submit">Prześlij darowiznę</button>
    </div>

</form:form>

</body>
</html>
