<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import="java.util.Iterator"%>

<html>
<head>
    <title>HisToryOfQuiz</title>
</head>
<body>

<a href="homepage.jsp">Return to homepage</a>

<% ArrayList<String> Q_IDS = (ArrayList) request.getAttribute("Quiz_Ids"); %>
<% ArrayList<String> USERS = (ArrayList) request.getAttribute("Usernames"); %>
<% ArrayList<String> SCORES = (ArrayList) request.getAttribute("Scores"); %>
<% ArrayList<String> Times = (ArrayList) request.getAttribute("Times"); %>
<% Integer Page =  (Integer) request.getAttribute("page"); %>
<% String name =  (String)request.getAttribute("curQuizName"); %>
<% Integer PageNum =  (Integer) request.getAttribute("Num"); %>

<h4>MAX SCORE ON QUIZ <%= name %>  IS ${maxxx}</h4>


     <div align="center">

        <table border="1" cellpadding="5">

            <caption><h2>History Of Quiz <%= name %></h2></caption>

            <tr>
                <th>Quiz_Id</th>
                <th>Username</th>
                <th>Score</th>
                <th>Time</th>
            </tr>

                <%
                 	Iterator<String> iterator_QI = Q_IDS.iterator();
                	Iterator<String> iterator_U = USERS.iterator();
                    Iterator<String> iterator_S = SCORES.iterator();
                    Iterator<String> iterator_T = Times.iterator();

                 	while(iterator_QI.hasNext())
                 	{
             		String S1 = iterator_QI.next();
               		String S2 = iterator_U.next();
            		String S3 = iterator_S.next();
               		String S4 = iterator_T.next();
                 	%>
                	<tr>
         	            	<td><%= S1 %> </td>
         	            	<td><%= S2 %> </td>
                         	<td><%= S3 %> </td>
                         	<td><%= S4 %> </td>
                   	</tr>
                    <%
                 	}
                    %>

        </table>



                                <form action="CustomQuizStatsServlet" method="get" style="text-align: center">
                                    <label>Page: </label>
                                    <input type="submit" name="jumpTo" value="jumpTo" style="display: none" />
                                    <input type="hidden" name="currPage" value=<%=Page%>>
                                    <input type="hidden" name="currQuiz" value=<%=name%>>
                                    <input type="hidden" name="currPageNum" value=<%=PageNum%>>
                                    <input type="submit" id="prev" name="prev" value="prev">
                                    <input type="text" id="jump" name="jump" value="<%out.println(Page);%>"  style="text-align: center">
                                    <input type="submit" id="next" name="next" value="next">
                                </form>



    </div>

        <form action="/CustomQuizStatsServlet" method="GET" >
            <label for="quiz_name">Change Number Of Quizzes per Page </label>
            <input type="hidden" name="currQuiz" value=<%=name%>>
            <input type="text" name="NumOfQuiz" value="${NumOfQuiz}" /><br/>
            <button type="submit">Change</button>
        </form>


        <form action="/CustomQuizStatsServlet" method="GET" >

            <label for="quiz_name">Search Stats Of Custom Quiz Name </label>
            <input type="text" name="quiz_name" value="${quiz_name}" /><br/>

            <label for="ORDER_BY">SORT BY</label>
            <input type="radio"  value="0" name="sort"> Time
            <button type="submit">Search</button>
        </form>


        <form action="/CustomUserStatsServlet" method="GET" >
            <label for="name"    >Search Stats Of Custom Username   </label>
            <input type="text" name="name" value="${name}" /><br/>

            <button type="submit">Search</button>
        </form>

        ${MaxS}
</body>
</html>