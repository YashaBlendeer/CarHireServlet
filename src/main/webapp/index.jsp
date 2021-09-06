<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<div class="container">
    <form action="/serv" method="POST" class="form-signin">
        <h3 class="form-signin-heading">Welcome</h3>
        <br/>

        <input type="text" name="userName" id="userName" placeholder="User Name"
               class="form-control"/>
        <input type="password" name="password" id="password" placeholder="Password"
               class="form-control"/>

<%--        <div align="center" th:if="${param.error}">--%>
<%--            <p style="font-size: 20; color: #FF1C19;">User Name or Password invalid, please verify</p>--%>
<%--        </div>--%>

        <input class="submit" type="submit" value="Login" class="btn btn-lg btn-primary btn-block"/>
    </form>
</div>

</body>
</html>