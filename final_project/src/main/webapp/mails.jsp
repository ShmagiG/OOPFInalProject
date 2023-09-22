<%@ page import="ObjectClasses.Mail" %>
<%@ page import="java.util.List" %>
<%@ page import="DAOs.AccountDAO" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="DAOs.MailsDao" %><%--
  Created by IntelliJ IDEA.
  User: nutsu
  Date: 8/9/2022
  Time: 6:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    public String createList(List<Mail> lst) {
        if (lst == null) {
            return "";
        } else {
            StringBuffer buf = new StringBuffer();
            buf.append("<ol start = \"1\">\n");
            for(Mail mail : lst) {
                if (mail.getType().equals(MailsDao.ADD_FRIEND)) {
                    buf.append("<li>").append("User: ").append(mail.getSender()).append(" Sent you friend request and this message : \n")
                            .append(mail.getMessage()).append("\n  <form action=\"/acceptFriendRequestServlet\" method=\"POST\" >\n   " + "<input type = \"hidden\" value =  ")
                            .append(mail.getSender()).append(" name = \"FriendSender\" id = \"FriendSender\">")
                            .append("<input type = \"hidden\" value =  ").append(mail.getId()).append(" name = \"MailId\" id = \"MailId\"> ")
                            .append("<input type=\"submit\" value=\"accept\"><br>\n </form>").append("</li>\n");
                }
                 else if (mail.getType().equals(MailsDao.MESSAGE)) {
                    buf.append("<li>").append("User: ").append(mail.getSender()).append(" Sent you this message : \n")
                            .append(mail.getMessage()).append("</li>\n");
                } else if(mail.getType().equals(MailsDao.CHALLENGE_QUIZ)) {
                    buf.append("<li>").append("User: ").append(mail.getSender()).append(" Challenged you for quizz id:").append(mail.getQuiz_id()).append(" and this message : \n")
                            .append(mail.getMessage()).append("\n  <form action=\"/acceptQuizChallengeServlet\" method=\"POST\" >\n   " + "<input type = \"hidden\" value =  ")
                            .append(mail.getSender()).append(" name = \"ChallengeSender\" id = \"ChallengeSender\">")
                            .append("<input type = \"hidden\" value =  ").append(mail.getId()).append(" name = \"MailId\" id = \"MailId\"> ")
                            .append("<input type = \"hidden\" value =  ").append(mail.getQuiz_id()).append(" name = \"ReceivedQuizId\" id = \"ReceivedQuizId\"> ")
                            .append("<input type=\"submit\" value=\"accept\"><br>\n </form>").append("</li>\n");
                }
            }
            buf.append("</ol>");
            return buf.toString();
        }
    }
%>

<html>
<head>
    <title>Mails</title>
</head>
<body>
<p>Your Received mails</p>
<a href="homepage.jsp">Return to HomePage</a><br>
<%

    MailsDao mailsDao = new MailsDao();
    List<Mail> lst = mailsDao.getReceivedMailsForUser((String) request.getSession().getAttribute("UserName"));
        out.println(createList(lst));
%>
</body>
</html>
