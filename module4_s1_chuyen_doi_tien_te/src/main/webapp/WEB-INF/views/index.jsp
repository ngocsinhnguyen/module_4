<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USD to VND Converter</title>
</head>
<body>
    <h2>Currency Converter</h2>
    <form action="convert" method="post">
        <label for="rate">Rate (Tỉ giá):</label><br>
        <input type="number" id="rate" name="rate" placeholder="25000" step="0.01" required><br><br>
        
        <label for="usd">USD Amount:</label><br>
        <input type="number" id="usd" name="usd" placeholder="10" step="0.01" required><br><br>
        
        <input type="submit" value="Convert">
    </form>
</body>
</html>
