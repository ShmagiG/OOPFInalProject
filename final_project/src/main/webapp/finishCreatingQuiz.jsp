<%--
  Created by IntelliJ IDEA.
  User: shmagi
  Date: 01.08.22
  Time: 09:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
%>
<html>
<head>
    <title>Finish Creating Quiz</title>
</head>
    <body>
        <form method="post" action="createQuestionServlet">
            <label for="quizName">Enter Quiz Name:</label><br>
            <input type="text" id="quizName" name="quizName"><br>
            <label for="quizDescription">Enter Quiz Description:</label><br>
            <input type="text" id="quizDescription" name="quizDescription"><br>
            <input type="radio" id="onePage" name="quizShowStyle" value="singlePage">
            <label for="onePage">Single Page</label><br>
            <input type="radio" id="multiplePage" name="quizShowStyle" value="multiplePage">
            <label for="multiplePage">Multiple Page</label><br>
            <input type="hidden" value="finishedDescriptions" name="questionType">
            <input type="submit" value="Finish">
        </form>
    </body>
</html>
