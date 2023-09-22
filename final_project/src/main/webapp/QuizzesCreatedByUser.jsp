<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.io.PrintStream"%>

<html>
<head>
    <title>ACHIEVEMENT STATS</title>
</head>
<body>

<a href="homepage.jsp">Return to homepage</a>

<% ArrayList<String> Q_IDS = (ArrayList) request.getAttribute("Quiz_Names"); %>
<% ArrayList<String> Times = (ArrayList) request.getAttribute("Times"); %>

     <div align="center">

        <table border="1" cellpadding="5">

            <caption><h2>CREATED QUIZZES</h2></caption>
            <tr>
                <th>Quiz_Name</th>
                <th>Creation Time</th>
            </tr>

            <%
            	Iterator<String> iterator_QI = Q_IDS.iterator();
                Iterator<String> iterator_T = Times.iterator();

            	while(iterator_QI.hasNext())
            	{
            		String S1 = iterator_QI.next();
               		String S2 = iterator_T.next();
            	%>
            	            	<tr>
            	            	<td><%= S1 %> </td>
            	            	<td><%= S2 %> </td>
                            	</tr>
                <%
            	}
                %>
        </table>
</body>
</html>