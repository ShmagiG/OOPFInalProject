<%@ page import="DAOs.QuizzesDAO" %><%--
  Created by IntelliJ IDEA.
  User: shmagi
  Date: 01.08.22
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
%>
<%
    String userMark = (String)request.getSession().getAttribute("USER_MARK");
    String totalMark = (String)request.getSession().getAttribute("MAX_MARK");
%>
<%
    QuizzesDAO quizDB = (QuizzesDAO) request.getServletContext().getAttribute("QUIZ_DB");
    int quizId = Integer.parseInt((String)request.getSession().getAttribute("QUIZ_ID"));
    quizDB.updateQuizPopularity(quizId);
%>
<html>
<head>
    <title>Results</title>
</head>
    <body>
    <h1><b>Quiz Name: <%out.println(quizDB.getQuizName(quizId));%></b></h1><br>
        <h>YOUR MARK IS:</h>
        <p>Your mark:
            <%
                out.println(userMark);
            %>
        </p>
        <br>
        <p>Total mark:
            <%
                out.print(totalMark);
            %>
        </p>
        <br>
        <p>Percentage:
            <%
                out.println("" + 100.0 * Integer.parseInt(userMark) / Integer.parseInt(totalMark) + "%");
            %>
        </p>
        <a href="showQuizzes.jsp">Quizzes page</a>
    </body>
</html>
