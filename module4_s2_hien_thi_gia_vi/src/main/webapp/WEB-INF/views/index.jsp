<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sandwich Condiments</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; }
        h1 { color: #333; }
        .form-group { margin-bottom: 20px; }
        .save-btn { padding: 10px 20px; cursor: pointer; }
    </style>
</head>
<body>
    <h1>Sandwich Condiments</h1>
    <form action="save" method="post">
        <div class="form-group">
            <c:forEach var="item" items="${condiments}">
                <input type="checkbox" name="condiment" value="${item}" id="${item}">
                <label for="${item}">${item}</label>
            </c:forEach>
        </div>
        <button type="submit" class="save-btn">Save</button>
    </form>
</body>
</html>
