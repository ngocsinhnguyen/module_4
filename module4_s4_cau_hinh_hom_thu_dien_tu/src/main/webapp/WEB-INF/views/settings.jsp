<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Email Settings</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 40px; color: #333; }
        h1 { font-size: 24px; margin-bottom: 20px; }
        .form-group { margin-bottom: 20px; display: flex; align-items: flex-start; }
        .label { width: 120px; font-weight: bold; }
        .control { flex: 1; }
        select, textarea { padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
        textarea { width: 300px; height: 100px; }
        .btn-update { background-color: #008CBA; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; }
        .btn-cancel { background-color: white; color: black; border: 1px solid #333; padding: 10px 20px; border-radius: 4px; margin-left: 10px; text-decoration: none; cursor: pointer; }
        .btn-update:hover { background-color: #007ba8; }
    </style>
</head>
<body>

<h1>Settings</h1>

<form:form action="${pageContext.request.contextPath}/settings/update" method="post" modelAttribute="settings">
    <div class="form-group">
        <div class="label">Languages</div>
        <div class="control">
            <form:select path="language">
                <form:options items="${languages}" />
            </form:select>
        </div>
    </div>

    <div class="form-group">
        <div class="label">Page Size:</div>
        <div class="control">
            Show 
            <form:select path="pageSize">
                <form:options items="${pageSizes}" />
            </form:select>
            emails per page
        </div>
    </div>

    <div class="form-group">
        <div class="label">Spams filter:</div>
        <div class="control">
            <form:checkbox path="spamFilter" label="Enable spams filter" />
        </div>
    </div>

    <div class="form-group">
        <div class="label">Signature:</div>
        <div class="control">
            <form:textarea path="signature" />
        </div>
    </div>

    <div>
        <button type="submit" class="btn-update">Update</button>
        <button type="button" class="btn-cancel" onclick="window.location.reload()">Cancel</button>
    </div>
</form:form>

</body>
</html>


