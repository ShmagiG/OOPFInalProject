<%--
  Created by IntelliJ IDEA.
  User: shmagi
  Date: 29.07.22
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
    <link rel="stylesheet" type="text/css" href="login.css" />
</head>
<body>
    <div class = "LoginContainer">
        <form action="CreateAccountServlet" method="post">
            <h2>Register</h2>
            <div><input type="text" placeholder="Name" name="UserName" id="UserName"></div>
            <div><input type="password" placeholder="Password" name="Password" id ="Password"></div>
            <input type="submit" value="register"><br>
        </form>
    </div>
</body>
</html>
