<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>Selected Condiments</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 50px;
                }

                h1 {
                    color: #333;
                }

                .result {
                    margin-top: 20px;
                    font-size: 1.2em;
                }

                .back-link {
                    margin-top: 20px;
                    display: block;
                }
            </style>
        </head>

        <body>
            <h1>Selected Condiments</h1>
            <div class="result">
                <c:choose>
                    <c:when test="${not empty sandwich.condiments}">
                        Selected condiments:
                        <c:forEach var="item" items="${sandwich.condiments}" varStatus="status">
                            ${item}${not status.last ? ', ' : ''}
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        No condiments selected.
                    </c:otherwise>
                </c:choose>
            </div>
            <a href="./" class="back-link">Back to selection</a>
        </body>

        </html>