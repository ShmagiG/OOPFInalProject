package UserPage;

import DAOs.QuizzesDAO;
import DAOs.quizUserHistoryDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AchievementServlet", value = "/AchievementServlet")
public class AchievementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {

        quizUserHistoryDao HistoryDao;
        try {
            HistoryDao = new quizUserHistoryDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet resultS = null;
        try {
            resultS = HistoryDao.getDistinctQuizCount((String) request.getSession().getAttribute("UserName"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int DistinctQuizCount = -1;
        try{
            while (resultS.next()) {
                DistinctQuizCount = resultS.getInt(1);
                System.out.println(DistinctQuizCount);
                System.out.println((String) request.getSession().getAttribute("UserName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        QuizzesDAO QuizDao;
        QuizDao = new QuizzesDAO();
        ResultSet CreatedQuiz = null;
        try {
            CreatedQuiz = QuizDao.GetQuizzesCountByAuthor((String) request.getSession().getAttribute("UserName"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int QuizCreated = -1;
        try{
            while (CreatedQuiz.next()) {
                QuizCreated = CreatedQuiz.getInt(1);
                System.out.println(QuizCreated);
                System.out.println("es mainc");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("QuizCreated",QuizCreated);
        request.setAttribute("QuizTaken",DistinctQuizCount);

        request.getRequestDispatcher("Achievments.jsp").forward(request, response);
    }
}