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
                        <input type="text" name="num1"
                            value="${num1 != null ? num1 : ''}" required>
                        <input type="text" name="num2"
                            value="${num2 != null ? num2 : ''}" required>
                    </div>
                    <div class="button-group">
                        <input type="submit" name="operator" value="Addition(+)">
                        <input type="submit" name="operator" value="Subtraction(-)">
                        <input type="submit" name="operator" value="Multiplication(X)">
                        <input type="submit" name="operator" value="Division(/)">
                    </div>
                </form>

                <c:if test="${not empty result}">
                    <div class="result">
                        Result ${operator.substring(operator.indexOf('(') + 1,
                        operator.indexOf(')'))} : ${result}
                    </div>
                </c:if>

                <c:if test="${not empty error}">
                    <div class="error">
                        Error: ${error}
                    </div>
                </c:if>
            </div>
        </body>

        </html>