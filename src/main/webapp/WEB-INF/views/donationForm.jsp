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

<form:form id="form" method="POST" modelAttribute="donation" action="/saveDonation">

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

<div id="summary"></div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        $('#donationForm').submit(function(event) {
            event.preventDefault();

            var summary = "<h2>Podsumowanie:</h2>";
            summary += "<p><strong>Kategorie darów:</strong> ";
            $('input[name="categories"]:checked').each(function() {
                summary += $(this).val() + ", ";
            });
            summary = summary.slice(0, -2); // Usuwa ostatnią przecinkę i spację
            summary += "</p>";

            summary += "<p><strong>Instytucja:</strong> " + $('select[name="institution"] option:selected').text() + "</p>";
            summary += "<p><strong>Kod pocztowy:</strong> " + $('input[name="zipCode"]').val() + "</p>";
            summary += "<p><strong>Ulica:</strong> " + $('input[name="street"]').val() + "</p>";
            summary += "<p><strong>Miasto:</strong> " + $('input[name="city"]').val() + "</p>";
            summary += "<p><strong>Liczba worków:</strong> " + $('input[name="quantity"]').val() + "</p>";
            summary += "<p><strong>Komentarz:</strong> " + $('textarea[name="pickUpComment"]').val() + "</p>";
            summary += "<p><strong>Data odbioru:</strong> " + $('input[name="pickUpDate"]').val() + "</p>";
            summary += "<p><strong>Godzina odbioru:</strong> " + $('input[name="pickUpTime"]').val() + "</p>";

            $('#summary').html(summary);
        });
    });
</script>
</body>
</html>
