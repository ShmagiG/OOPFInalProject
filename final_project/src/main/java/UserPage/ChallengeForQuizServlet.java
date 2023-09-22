package UserPage;

import DAOs.AccountDAO;
import DAOs.MailsDao;
import DAOs.QuizzesDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;



@WebServlet(
        name = "challengeForQuizServlet",
        value = {"/challengeForQuizServlet"}
)
public class ChallengeForQuizServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("UserNameOfFriend");
        int quizId = -1;
        try {
              quizId = Integer.parseInt(request.getParameter("CHALLENGED_QUIZ_ID"));
              System.out.println(quizId);
        } catch (NumberFormatException ex) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("notCorrectQuizId.jsp");
            dispatcher.forward(request,response);
            return;
        }
        String messageForFriend = request.getParameter("MessageForQuizChallenge");
        AccountDAO acc = new AccountDAO();
        QuizzesDAO quizzesDao = new QuizzesDAO();

        try {
            if(!quizzesDao.quizExists(quizId)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("notCorrectQuizId.jsp");
                dispatcher.forward(request,response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (acc.accountUsernameExists(userName)) {
                MailsDao mailsDao = new MailsDao();
                mailsDao.addMail((String)request.getSession().getAttribute("UserName"), userName, MailsDao.CHALLENGE_QUIZ, messageForFriend, quizId);
                RequestDispatcher dispatcher = request.getRequestDispatcher("startQuiz.jsp?QUIZ_ID=" + quizId);
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("noUserForChallenge.jsp?QUIZ_ID=" + quizId);
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
