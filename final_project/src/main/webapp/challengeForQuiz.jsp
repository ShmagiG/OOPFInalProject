<%--
  Created by IntelliJ IDEA.
  User: nutsu
  Date: 8/10/2022
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Challenge For Quiz</title>
</head>
<body>
    <form action="/challengeForQuizServlet" method="POST" >
        <h2>Challenge for Quiz</h2>
        <input type="text" placeholder="Username" name="UserNameOfFriend" id="UserNameOfFriend">
        <input type="text" placeholder="quiz id" name="ChallengedQuizId" id ="ChallengedQuizId">
        <input type="text" placeholder="Send Message" name="MessageForAddingFriend" id="MessageForAddingFriend">
        <input type="submit" value="Challenge"><br>
    </form>
    <a href="homepage.jsp">Return to HomePage</a><br>
</body>
</html>
