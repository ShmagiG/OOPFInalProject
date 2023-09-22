<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ObjectClasses.User" %><%--
  Created by IntelliJ IDEA.
  User: nutsu
  Date: 8/6/2022
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    public String createList(List<User> lst) {
        if(lst == null)  {
            return "<h1>There are no such users<h1>";
        }
        StringBuffer buf = new StringBuffer();
        buf.append("<ol start = \"1\">\n");
        for (User user: lst) {
            buf.append("<li>").append("<a href=\"VisitProfile.jsp\">").append(user.getUsername()).append("</a>").append("</li>\n");
        }
        buf.append("</ol>");

        return buf.toString();
    }
%>

<html>
<head>
    <title>Search</title>
    <link rel = "icon" href = "https://www.ukrgate.com/eng/wp-content/uploads/2021/02/The-Ukrainian-Book-Institute-Purchases-380.9-Thousand-Books-for-Public-Libraries1.jpeg"/>
</head>
<body>
    <a href="homepage.jsp">Return to HomePage</a><br>
    <p> Matching Users Found</p>
    <%
       List<User> lst = (List<User>) request.getAttribute("accountsFoundList");
        out.println(createList(lst));
    %>
</body>
</html>
