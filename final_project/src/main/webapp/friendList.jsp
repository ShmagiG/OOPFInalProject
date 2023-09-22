<%@ page import="java.sql.ResultSet" %>
<%@ page import="DAOs.FriendDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DAOs.AccountDAO" %><%--
  Created by IntelliJ IDEA.
  User: nutsu
  Date: 8/10/2022
  Time: 1:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
  public String createList(String userName) throws SQLException {
    FriendDAO friendDAO = new FriendDAO();
    AccountDAO accountDAO = new AccountDAO();
    List<Integer> friends = friendDAO.getFriends(userName);
    StringBuffer buf = new StringBuffer();
    buf.append("<ol start = \"1\">\n");
    for (Integer id: friends) {
      buf.append("<li>").append(accountDAO.getUserNameByAccountId(id)).append("</li>\n");
    }
    buf.append("</ol>");

    return buf.toString();
  }
%>

<html>
<head>
    <title>Friend List</title>
</head>
<body>
  <a href="homepage.jsp">Return to HomePage</a><br>
    <%
      try {
        out.println(createList((String) request.getSession().getAttribute("UserName")));
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    %>
</body>
</html>
