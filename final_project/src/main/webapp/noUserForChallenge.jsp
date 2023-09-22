<%@ page import="DAOs.QuizzesDAO" %><%--
  Created by IntelliJ IDEA.
  User: nutsu
  Date: 8/24/2022
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    QuizzesDAO quizDB = (QuizzesDAO) application.getAttribute("QUIZ_DB");
    int quizId = Integer.parseInt(request.getParameter("QUIZ_ID"));
%>

<html>
<head>
    <title>No Such User</title>
    <link rel = "icon" href = "https://www.ukrgate.com/eng/wp-content/uploads/2021/02/The-Ukrainian-Book-Institute-Purchases-380.9-Thousand-Books-for-Public-Libraries1.jpeg"/>
</head>
<body>
<label style="color:red">There Was No Such User</label>
<form method="post" action="/challengeForQuizServlet">
    <label><b>Quiz Name: <%out.println(quizDB.getQuizName(quizId));%></b></label><br>
    <label for="CHALLENGED_QUIZ_ID">Challenge Friend: </label>
    <input type="text" placeholder="Enter User Name" name="UserNameOfFriend" value="">
    <input type="text" placeholder="Send Message" name="MessageForQuizChallenge" id="MessageForQuizChallenge">
    <input type="hidden" id="CHALLENGED_QUIZ_ID" name="CHALLENGED_QUIZ_ID" value="<%out.println(quizId);%>">
    <input type="submit" value="Next">
</form>
<a href="quiz.jsp?id=<%out.println(quizId);%>">Start Quiz</a>
</body>
</html>

