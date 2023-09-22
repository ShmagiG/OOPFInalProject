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

@WebServlet(name = "QuizCreatedUserServlet", value = "/QuizCreatedUserServlet")
public class QuizCreatedByUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        quizUserHistoryDao HistoryDao;
        try {
            HistoryDao = new quizUserHistoryDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet RS = null;

        try {
            RS = HistoryDao.getCreatedByUser((String) request.getSession().getAttribute("UserName"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<String> Quiz_Names = new ArrayList<>();
        List<String> Times = new ArrayList<>();

        try {
            while (RS.next()) {
                Quiz_Names.add(RS.getString("name"));
                Times.add(RS.getString("quiz_creation_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("Quiz_Names",Quiz_Names);
        request.setAttribute("Times",Times);


        request.getRequestDispatcher("QuizzesCreatedByUser.jsp").forward(request, response);
    }
}