<%@ page import="DAOs.QuizzesDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %><%--
  Created by IntelliJ IDEA.
  User: shmagi
  Date: 12.08.22
  Time: 08:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
%>
<%
    int QUIZZES_PER_PAGE = 10;
    request.getSession().removeAttribute("QUIZ");
%>
<%
    int Page = 1;
    if(request.getParameter("page") != null){
        Page = Integer.parseInt((String)request.getParameter("page"));
    } else {
        request.getSession().removeAttribute("sort");
        request.getSession().removeAttribute("search");
    }
%>
<%
    QuizzesDAO db = (QuizzesDAO) request.getServletContext().getAttribute("QUIZ_DB");
    ResultSet rs;

    if(request.getSession().getAttribute("sort") != null && request.getSession().getAttribute("sort").equals("Sort by creation date")){
        rs = db.orderByCreationDate((String)request.getSession().getAttribute("search"));
    } else if(request.getSession().getAttribute("sort") != null && request.getSession().getAttribute("sort").equals("Sort by popularity")){
        rs = db.orderByPopularity((String)request.getSession().getAttribute("search"));
    } else {
        rs = db.allRows();
    }

    rs.absolute((Page - 1) * 10);
%>
<html>
<head>
    <title>Quizzes</title>
</head>
    <body>
    <a href="homepage.jsp">Return to homepage</a>
    <br>
        <a href="createQuestion.jsp">Create new quiz</a>
        <form action="ShowQuizzesServlet" method="post" style="text-align: center">
            <label for="search">Name:</label>
            <input type="text" id="search" name="search" value=""><br>
            <input type="submit" value="Search">
        </form>
        <table style="width:100%" border="2">
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>
                    <form action="ShowQuizzesServlet" method="post">
                        <label>Users completed</label>
                        <input type="submit" name="sort" value="Sort by popularity">
                    </form>
                </th>
                <th> Quiz Author </th>
                <th>
                    <form action="ShowQuizzesServlet" method="post">
                        <label>Creation time</label>
                        <input type="submit" name="sort" value="Sort by creation date">
                    </form>
                </th>
            </tr>
        <%
            for(int i = 0; i < QUIZZES_PER_PAGE; i++){
                if(!rs.next()){
                    break;
                }
                String id = rs.getString(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                int num_participants = rs.getInt(5);
                String creationTime = rs.getString(6);
                String author = rs.getString(7);


                out.println("<tr>" +
                            "    <td style=\"text-align: center; vertical-align: middle;\">" +
                            "        <a href=\"startQuiz.jsp?QUIZ_ID=" + id + "\">" +
                            "          <div style=\"height:100%;width:100%\">" +
                                          name +
                            "          </div>" +
                            "        </a>" +
                            "    </td>" +
                            "    <td style=\"text-align: center; vertical-align: middle;\">" + description + "</td>" +
                            "    <td style=\"text-align: center; vertical-align: middle;\">" + num_participants + "</td>" +
                            "    <td style=\"text-align: center; vertical-align: middle;\">" + author + "</td>" +
                            "    <td style=\"text-align: center; vertical-align: middle;\">" + creationTime + "</td>" +
                            " </tr>");
            }
        %>
        </table>
        <form action="ShowQuizzesServlet" method="post" style="text-align: center">
            <label>Page: </label>
            <input type="submit" name="jumpTo" value="jumpTo" style="display: none" />
            <input type="hidden" name="currPage" value=<%=Page%>>
            <input type="submit" id="prev" name="prev" value="prev">
            <input type="text" id="jump" name="jump" value="<%out.println(Page);%>"  style="text-align: center">
            <input type="submit" id="next" name="next" value="next">
        </form>
    </body>
</html>
