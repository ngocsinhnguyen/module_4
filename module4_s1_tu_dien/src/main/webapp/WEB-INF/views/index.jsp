<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Từ điển Anh - Việt</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    max-width: 600px;
                    margin: 50px auto;
                    padding: 20px;
                }

                h1 {
                    color: #333;
                    text-align: center;
                }

                .subtitle {
                    text-align: center;
                    color: #666;
                    margin-bottom: 30px;
                }

                form {
                    margin-bottom: 20px;
                }

                input[type="text"] {
                    width: 70%;
                    padding: 10px;
                    font-size: 16px;
                    border: 1px solid #ccc;
                }

                button {
                    padding: 10px 20px;
                    font-size: 16px;
                    background-color: #4CAF50;
                    color: white;
                    border: none;
                    cursor: pointer;
                }

                button:hover {
                    background-color: #45a049;
                }

                .result {
                    padding: 15px;
                    margin-top: 20px;
                    border: 1px solid #ddd;
                    background-color: #f9f9f9;
                }

                .success {
                    background-color: #d4edda;
                    border-color: #c3e6cb;
                    color: #155724;
                }

                .error {
                    background-color: #f8d7da;
                    border-color: #f5c6cb;
                    color: #721c24;
                }

                .instruction {
                    margin-top: 30px;
                    padding: 15px;
                    background-color: #e7f3ff;
                    border-left: 4px solid #2196F3;
                }
            </style>
        </head>

        <body>
            <h1>Từ điển Anh - Việt</h1>


            <form action="${pageContext.request.contextPath}/search" method="post">
                <input type="text" name="word" placeholder="Nhập từ tiếng Anh..." value="${searchWord}" required>
                <button type="submit">Tra cứu</button>
            </form>

            <c:if test="${not empty searchWord}">
                <c:choose>
                    <c:when test="${not empty result}">
                        <div class="result success">
                            <strong>${searchWord}</strong>: ${result}
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="result error">
                            Không tìm thấy từ "<strong>${searchWord}</strong>"
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>


        </body>

        </html>