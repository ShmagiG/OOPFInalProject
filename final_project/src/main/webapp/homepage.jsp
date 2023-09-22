<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: shmagi
  Date: 29.07.22
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    public String createList(List<String> lst) {
        StringBuffer buf = new StringBuffer();
        buf.append("<ol start = \"1\">\n");
        for (String str: lst) {
            buf.append("<li>").append(str).append("</li>\n");
        }
        buf.append("</ol>");

        return buf.toString();
    }
%>

<html>
<head>
    <title>Home Page</title>
    <link rel = "icon" href = "https://www.ukrgate.com/eng/wp-content/uploads/2021/02/The-Ukrainian-Book-Institute-Purchases-380.9-Thousand-Books-for-Public-Libraries1.jpeg"/>
    <link rel="stylesheet" href="homeStyle.css"/>
</head>
<body>
    <h1><b>Quiz Website</b></h1>


    <div id="usernameSearch">
        <form action="/SearchUserServlet" method="GET" >
            <input id="searchField" type="text" placeholder="User Name" name="UserName" id="UserName">
            <input type="submit" value="Search"><br>
        </form>
    </div>

    <div id="profileDropdown">
        Logged in as:
        <br>
        <button onclick="dropdownProfile()" id="dropdownButton"><%=request.getSession().getAttribute("UserName")%> </button>
        <div id="dropdownContent" class="dropdownContent">
            <ul>
                <li><a href="addFriend.jsp">Add Friend</a></li>
                <li><a href="sendMessage.jsp">Send Message</a></li>
                <li><a href="mails.jsp">Mails</a></li>
                <li><a href="friendList.jsp">Friends</a></li>
            </ul>
        </div>
    </div>

    <div id="quizLinks">
        <a href="createQuestion.jsp" id="createQuestion">Create a Quiz</a>
        <a href="showQuizzes.jsp" id="showQuizzes">Show Quizzes</a>
        <form action="/QuizServletGetHistory" method="GET">
            <a href="QuizServletGetHistory.jsp" onclick='this.parentNode.submit(); return false;' id="quizStats">Quiz Stats</a>
        </form>
    </div>

    <a href="login.jsp">Sign out</a>

    <form action="/AchievementServlet" method="GET" >
        <button type="submit">Achievements</button>
    </form>


    <script src="homepageFunctions.js"></script>
</body>
</html>