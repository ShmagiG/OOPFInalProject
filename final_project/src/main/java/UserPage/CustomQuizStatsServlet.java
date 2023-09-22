
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

@WebServlet(name = "CustomQuizStatsServlet", value = "/CustomQuizStatsServlet")
public class CustomQuizStatsServlet extends HttpServlet {

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

        quizUserHistoryDao HistoryDao;
        try {
            HistoryDao = new quizUserHistoryDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String Quiz_Id = null;
        int sort;
        String MaxS;

        try {
            sort = Integer.parseInt(request.getParameter("sort"));
        } catch (NumberFormatException e) {
            sort = 0;
        }

        if(request.getParameter("quiz_name")!=null){
            Quiz_Id = request.getParameter("quiz_name");
        }else{
            Quiz_Id = request.getParameter("currQuiz");
        }
        request.setAttribute("curQuizName",Quiz_Id);


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

        request.setAttribute("Num",QP);



        Integer Page = 0;
        try {
            Page = Integer.parseInt(request.getParameter("currPage"));

        } catch (NumberFormatException e) {
            Page = 1;
        }

        int NumOfStats = -1;
        try {
            NumOfStats = HistoryDao.getQuizzesCountByQuiz(Quiz_Id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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



        ResultSet rs = null;

        try {
            switch (sort){
              case 0:
                    rs = HistoryDao.getQuizStats(Quiz_Id);
                                break;
              case 1:
                    rs = HistoryDao.getQuizStatsSortByScore(Quiz_Id);
                break;
              default:
                rs = HistoryDao.getQuizStatsSortByTime(Quiz_Id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        List<String> Quiz_Names = new ArrayList<>();
        List<String> Usernames = new ArrayList<>();
        List<String> Scores = new ArrayList<>();
        List<String> Times = new ArrayList<>();
        String MaxQuizScore = null;




        try {
            rs.absolute((Page-1)*QP);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int count = QP;


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

        ResultSet resultS;
        try {
            resultS = HistoryDao.getMaxQuizScore(Quiz_Id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try{
        while (resultS.next()) {
            MaxQuizScore = resultS.getString(1);
            System.out.println(MaxQuizScore);
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("Quiz_Ids",Quiz_Names);
        request.setAttribute("Scores",Scores);
        request.setAttribute("Times",Times);
        request.setAttribute("Usernames",Usernames);
        request.setAttribute("maxxx",MaxQuizScore);



        /*
        try {
            request.setAttribute("MaxS",MaxS.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        /*
        try{
        while (MaxS.next()) {
            request.setAttribute("MaxS",MaxS.getString(1));
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        request.getRequestDispatcher("CustomQuizStats.jsp").forward(request, response);
    }


}