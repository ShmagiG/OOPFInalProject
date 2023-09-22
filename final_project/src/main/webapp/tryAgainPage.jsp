<%--
  Created by IntelliJ IDEA.
  User: nutsu
  Date: 8/4/2022
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Try Again</title>
    <link rel="stylesheet" type="text/css" href="login.css" />
    <link rel = "icon" href = "https://www.ukrgate.com/eng/wp-content/uploads/2021/02/The-Ukrainian-Book-Institute-Purchases-380.9-Thousand-Books-for-Public-Libraries1.jpeg"/>
</head>
<body>
<div class="LoginContainer">
    <form action="/LoginServlet" method="POST" >
        <h4>User Name or Password was incorrect, Try Again please</h4>
        <div><input type="text" placeholder="Name" name="UserName" id="UserName"></div>
        <div><input type="text" placeholder="Password" name="Password" id ="Password"></div>
        <input type="submit" value="login"><br>
        <a href="createAccount.jsp">Create New Account</a>
    </form>
</div>
</body>
</html>