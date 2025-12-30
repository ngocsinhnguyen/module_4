<%-- Created by IntelliJ IDEA. User: Sinh Date: 12/29/2025 Time: 11:03 AM To change this template use File | Settings |
  File Templates. --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <html>

      <head>
        <title>Create Employee</title>
      </head>

      <body>
        <h2>Add New Employee</h2>
        <form:form action="add-employee" method="post" modelAttribute="employee">
          <table>
            <tr>
              <td>
                <form:label path="id">ID:</form:label>
              </td>
              <td>
                <form:input path="id" />
              </td>
            </tr>
            <tr>
              <td>
                <form:label path="name">Name:</form:label>
              </td>
              <td>
                <form:input path="name" />
              </td>
            </tr>
            <tr>
              <td>
                <form:label path="contactNumber">Contact Number:</form:label>
              </td>
              <td>
                <form:input path="contactNumber" />
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <input type="submit" value="Submit" />
              </td>
            </tr>
          </table>
        </form:form>
      </body>

      </html>