<%--
  Created by IntelliJ IDEA.
  User: nutsu
  Date: 8/9/2022
  Time: 6:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>Add Friend</title>
    <head>
        <title>Add Friend</title>
   </head>
<body>
 <form action="/addFriendServlet" method="POST" >
    <h2>Add Friend</h2>
    <input type="text" placeholder="Username" name="UserNameOfFriend" id="UserNameOfFriend">
           <input type="text" placeholder="Send Message" name="MessageForAddingFriend" id="MessageForAddingFriend">
        <input type="submit" value="add user as friend"><br>
 </form>
 <a href="homepage.jsp">Return to HomePage</a><br>
</body>
</html>
