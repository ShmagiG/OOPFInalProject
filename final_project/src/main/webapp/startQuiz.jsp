<%@ page import="DAOs.QuizzesDAO" %>
<%@ page import="Quizzes.Quiz" %><%--
  Created by IntelliJ IDEA.
  User: shmagi
  Date: 24.08.22
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  QuizzesDAO quizDB = (QuizzesDAO) application.getAttribute("QUIZ_DB");
  int quizId = Integer.parseInt(request.getParameter("QUIZ_ID"));
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/challengeForQuizServlet">
  <label><b>Quiz Name: <%out.println(quizDB.getQuizName(quizId));%></b></label><br>
  <label for="CHALLENGED_QUIZ_ID">Challenge Friend: </label>
  <input type="text" placeholder="Enter User Name" name="UserNameOfFriend" value="">
  <input type="text" placeholder="Send Message" name="MessageForQuizChallenge" id="MessageForQuizChallenge">
  <input type="hidden" id="CHALLENGED_QUIZ_ID" name="CHALLENGED_QUIZ_ID" value="<%out.print(quizId);%>">
  <input type="submit" value="Next">
</form>
<a href="quiz.jsp?QUIZ_ID=<%out.println(quizId);%>">Start Quiz</a>
<a href="homepage.jsp">Return to HomePage</a><br>
</body>
</html>
