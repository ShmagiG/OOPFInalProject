<%--
  Created by IntelliJ IDEA.
  User: shmagi
  Date: 29.07.22
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="login.css" />
    <link rel = "icon" href = "https://www.ukrgate.com/eng/wp-content/uploads/2021/02/The-Ukrainian-Book-Institute-Purchases-380.9-Thousand-Books-for-Public-Libraries1.jpeg"/>
</head>
<body>
    <div class="LoginContainer">
        <form action="/LoginServlet" method="POST" >
            <h2>Login</h2>
            <div>
                <input type="text" placeholder="Username" name="UserName" id="UserName">
            </div>
            <div>
            <input type="password" placeholder="Password" name="Password" id ="Password">
            </div>
            <input type="submit" value="login"><br>
            <a href="createAccount.jsp">Create New Account</a>
        </form>
    </div>
</body>
</html>
