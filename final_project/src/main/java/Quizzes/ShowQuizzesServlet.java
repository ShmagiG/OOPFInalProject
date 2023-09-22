package Quizzes;

import DAOs.QuizzesDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ShowQuizzesServlet", value = "/ShowQuizzesServlet")
public class ShowQuizzesServlet extends HttpServlet {
    public static final int QUIZZES_PER_PAGE = 10;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
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
    private void changePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        QuizzesDAO db = (QuizzesDAO) request.getServletContext().getAttribute("QUIZ_DB");
        int currPage = Integer.parseInt(request.getParameter("currPage"));
        if(request.getParameter("prev") != null && currPage > 1){
            request.setAttribute("page", (currPage - 1));
            request.getRequestDispatcher("showQuizzes.jsp?page=" + (currPage - 1)).forward(request, response);
        } else if(request.getParameter("next") != null && db.getQuizzesCount() >= (currPage) * QUIZZES_PER_PAGE){
            request.setAttribute("page", (currPage + 1));
            request.getRequestDispatcher("showQuizzes.jsp?page=" + (currPage + 1)).forward(request, response);
        } else if(request.getParameter("jumpTo") != null && isNumeric(request.getParameter("jump")) && Integer.parseInt(request.getParameter("jump")) >= 0 && db.getQuizzesCount() >= QUIZZES_PER_PAGE * (Integer.parseInt(request.getParameter("jump")) - 1)){
            request.setAttribute("page", request.getParameter("jump"));
            request.getRequestDispatcher("showQuizzes.jsp?page=" + Integer.parseInt(request.getParameter("jump"))).forward(request, response);
        } else{
            request.getRequestDispatcher("showQuizzes.jsp?page=" + currPage).forward(request, response);
        }
    }
    private void updateResultSet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setAttribute("page", 1);
        if(request.getParameter("sort") != null){
            request.getSession().setAttribute("sort", request.getParameter("sort"));
        } else {
            request.getSession().setAttribute("sort", "Sort by creation date");
        }
        if(request.getParameter("search") != null){
            request.getSession().setAttribute("search", request.getParameter("search"));
        } else if(request.getSession().getAttribute("search") == null){
            request.getSession().setAttribute("search", "");
        }

        request.getRequestDispatcher("showQuizzes.jsp?page=1").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("prev") != null || request.getParameter("jumpTo") != null || request.getParameter("next") != null){
            try {
                changePage(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(request.getParameter("search") != null || request.getParameter("sort") != null){
            try {
                updateResultSet(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
