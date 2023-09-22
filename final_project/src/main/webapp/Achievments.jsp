<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import="java.util.Iterator"%>

<html>
<head>
    <title>Achievements</title>
</head>
<body>

<a href="homepage.jsp">Return to homepage</a>

<h4>${QuizCreated} Quizzes Created</h4>
<form action="/QuizCreatedUserServlet" method="GET" >
   <button type="submit">See Quizzes Created </button>
</form>

<h4>${QuizTaken} Different Quiz Taken</h4>
<form action="/MaxScoreByUser" method="GET" >
   <button type="submit">See Best Scores For each Quiz Written</button>
</form>

     <div align="center">


</body>
</html>