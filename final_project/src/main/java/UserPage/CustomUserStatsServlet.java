package UserPage;

import DAOs.quizUserHistoryDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomUserStatsServlet", value = "/CustomUserStatsServlet")
public class CustomUserStatsServlet extends HttpServlet {

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        String Username = null;

        quizUserHistoryDao HistoryDao;
        try {
            HistoryDao = new quizUserHistoryDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(request.getParameter("name") != null){
            Username = request.getParameter("name");
        }
        else {
            Username = request.getParameter("currUser");
        }
        request.setAttribute("User",Username);


        int NumOfStats = -1;
        try {
            NumOfStats = HistoryDao.getQuizzesCountByUser(Username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String QuizPerPage= request.getParameter("NumOfQuiz");

        int QP = 4;
        if(QuizPerPage != null && isNumeric(QuizPerPage)){
            QP = Integer. parseInt(QuizPerPage);
        }else if (request.getParameter("currPageNum") != null){
            if(isNumeric(request.getParameter("currPageNum")))
             QP = Integer.parseInt(request.getParameter("currPageNum"));
        }else{
            QP = 4;
        }



        Integer Page = 0;
        try {
            Page = Integer.parseInt(request.getParameter("currPage"));

        } catch (NumberFormatException e) {
            Page = 1;
        }



        if(request.getParameter("prev") != null && Page>1) {
            Page--;
        } else if (request.getParameter("next") != null && Page < ((NumOfStats-1+QP)/QP)) {
            Page++;
        } else if (request.getParameter("jumpTo") != null &&
                isNumeric(request.getParameter("jump")) &&
                (Integer.valueOf(request.getParameter("jump"))) > 0 &&
                (Integer.valueOf(request.getParameter("jump"))) <= ((NumOfStats-1+QP)/QP)){
            Page = Integer.valueOf(request.getParameter("jump"));
        }
        request.setAttribute("page", Page);
        request.setAttribute("Num",QP);

        ResultSet rs = null;

        try {
            rs = HistoryDao.getUserStatsByTime(Username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<String> Quiz_Names = new ArrayList<>();
        List<String> Usernames = new ArrayList<>();
        List<String> Scores = new ArrayList<>();
        List<String> Times = new ArrayList<>();


        try {
            rs.absolute((Page-1)*QP);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        int count=QP;

        try {
            while (rs.next() && count>0) {
                count--;
                Quiz_Names.add(rs.getString("quiz_name"));
                Scores.add(rs.getString("score"));
                Usernames.add(rs.getString("username"));
                Times.add(rs.getString("quiz_creation_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("Quiz_Ids",Quiz_Names);
        request.setAttribute("Scores",Scores);
        request.setAttribute("Times",Times);
        request.setAttribute("Usernames",Usernames);
        request.setAttribute("Username,",Username);

        request.getRequestDispatcher("CustomUserStats.jsp").forward(request, response);
    }
}