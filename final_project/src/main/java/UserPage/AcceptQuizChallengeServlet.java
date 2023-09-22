//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package UserPage;

import DAOs.AccountDAO;
import DAOs.FriendDAO;
import DAOs.MailsDao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "acceptQuizChallengeServlet",
        value = {"/acceptQuizChallengeServlet"}
)
public class AcceptQuizChallengeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = (String) request.getSession().getAttribute("UserName");
        String challengeSender = request.getParameter("ChallengeSender");
        int quizId = Integer.parseInt(request.getParameter("ReceivedQuizId"));
        int mailId = Integer.parseInt(request.getParameter("MailId"));
        MailsDao mailsDao = new MailsDao();

        try {
            mailsDao.removeMail(mailId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("quiz.jsp?QUIZ_ID=" + quizId);
        dispatcher.forward(request, response);
    }
}
