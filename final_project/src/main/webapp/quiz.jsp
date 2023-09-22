<%@ page import="Quizzes.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DAOs.QuizzesDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: shmagi
  Date: 30.07.22
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
%>
<%
//    Quiz quiz = (Quiz) application.getAttribute("QUIZ");
    QuizzesDAO quizDB = (QuizzesDAO) application.getAttribute("QUIZ_DB");
    Quiz quiz;
    if(request.getParameter("QUIZ_ID") != null){
        request.getSession().removeAttribute("QUIZ");
        request.getSession().removeAttribute("LAST_ID");
    }
    if(request.getParameter("QUIZ_ID") == null){
        quiz = (Quiz) request.getSession().getAttribute("QUIZ");
    } else {
        int id = Integer.parseInt(request.getParameter("QUIZ_ID"));
        quiz = quizDB.getQuiz(id);
        request.getSession().setAttribute("QUIZ", quiz);
        request.getSession().setAttribute("QUIZ_ID", "" + id);
    }


    if(quiz.getQuizType().equals(quiz.MULTIPLE_PAGE) && request.getSession().getAttribute("LAST_ID") == null){
        request.getSession().setAttribute("USER_MARK", "0");
        request.getSession().setAttribute("LAST_ID", "0");
    }
%>


<html>
    <head>
        <title>Quiz</title>
    </head>
    <body>

        <form name="quiz" method="post" action="markQuizServlet">
            <ol>
                <%
                    ArrayList<Problem> list = quiz.getQuiz();
                    if(quiz.getQuizType().equals(quiz.SINGLE_PAGE)){
                        for(int i = 0; i < list.size(); i++){
                            out.println("<li>");
                            out.println(list.get(i).getQuestion().questionHtmlCode());
                            out.println(list.get(i).getAnswer().answerPromptHtmlCode(i));
                            out.println("</li>");
                        }
                    } else if(quiz.getQuizType().equals(quiz.MULTIPLE_PAGE)){
                        int lastId = Integer.parseInt((String)request.getSession().getAttribute("LAST_ID"));
                        out.println("<h4>" + (lastId + 1) + ". </h4>");
                        out.println(list.get(lastId).getQuestion().questionHtmlCode());
                        out.println(list.get(lastId).getAnswer().answerPromptHtmlCode(lastId));
                    }

                %>
            </ol>
            <input type="submit" value="Next">
        </form>

    </body>
</html>
