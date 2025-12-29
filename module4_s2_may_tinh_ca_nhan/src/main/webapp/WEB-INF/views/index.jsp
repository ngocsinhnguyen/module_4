<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>Calculator</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                }

                .container {
                    width: 500px;
                }

                .input-group {
                    margin-bottom: 10px;
                }

                input[type="number"] {
                    width: 240px;
                    padding: 5px;
                    font-size: 16px;
                }

                .button-group {
                    margin-bottom: 20px;
                }

                input[type="submit"] {
                    padding: 10px 15px;
                    font-size: 14px;
                    cursor: pointer;
                    margin-right: 5px;
                }

                .result {
                    font-size: 24px;
                    font-weight: bold;
                }

                .error {
                    color: red;
                    font-weight: bold;
                }
            </style>
        </head>

        <body>
            <div class="container">
                <h1>Calculator</h1>
                <form action="calculate" method="post">
                    <div class="input-group">
                        <input type="number" step="any" name="num1"
                            value="${calculator.num1 != null ? calculator.num1 : ''}" required>
                        <input type="number" step="any" name="num2"
                            value="${calculator.num2 != null ? calculator.num2 : ''}" required>
                    </div>
                    <div class="button-group">
                        <input type="submit" name="operator" value="Addition(+)">
                        <input type="submit" name="operator" value="Subtraction(-)">
                        <input type="submit" name="operator" value="Multiplication(X)">
                        <input type="submit" name="operator" value="Division(/)">
                    </div>
                </form>

                <c:if test="${not empty calculator.result}">
                    <div class="result">
                        Result ${calculator.operator.substring(calculator.operator.indexOf('(') + 1,
                        calculator.operator.indexOf(')'))} : ${calculator.result}
                    </div>
                </c:if>

                <c:if test="${not empty calculator.error}">
                    <div class="error">
                        Error: ${calculator.error}
                    </div>
                </c:if>
            </div>
        </body>

        </html>