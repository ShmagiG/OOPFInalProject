<%@ page import="Quizzes.*" %>
<%--
  Created by IntelliJ IDEA.
  User: shmagi
  Date: 30.07.22
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
%>
<%
    if(request.getSession().getAttribute("QUIZ") == null){
        request.getSession().setAttribute("QUIZ", new Quiz());
    }
%>
<%!
    public String createForm(String label, String questionInputs, String answerInputs){
        return    "<label><b>" + label + ":</b></label><br>" +
                "<form method=\"post\" action=\"createQuestionServlet\">" +
                questionInputs +
                "<br>" +
                answerInputs +
                "<input type=\"hidden\" value=\"" + label + "\" name=\"questionType\">" +
                "<input type=\"submit\" value=\"Add\">" +
                "</form>";
    }
%>

<html>
<head>
    <title>Create Quiz</title>
</head>
<body>
<h1>Try again to create new Question:</h1><br>
<p style="color:red">(Please do not leave question or answer field empty, new question will not be added. Also new Quiz can not be created if no question was added.)</p>
<%
    out.println(createForm(questionResponseQuestion.NAME, questionResponseQuestion.createQuestionHtmlCode, questionResponseAnswer.createAnswerHtmlCode));
    out.println(createForm(fillBlankQuestion.NAME, fillBlankQuestion.createQuestionHtmlCode, fillBlankAnswer.createAnswerHtmlCode));
    out.println(createForm(multipleChoiceQuestion.NAME, multipleChoiceQuestion.createQuestionHtmlCode, multipleChoiceAnswer.createAnswerHtmlCode));
    out.println(createForm(pictureResponseQuestion.NAME, pictureResponseQuestion.createQuestionHtmlCode, pictureResponseAnswer.createAnswerHtmlCode));
%>
<a href="finishCreatingQuiz.jsp">Finish Creating Quiz</a>
</body>
</html>
