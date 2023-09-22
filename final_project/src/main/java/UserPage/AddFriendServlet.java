package UserPage;

import DAOs.AccountDAO;
import DAOs.FriendDAO;
import DAOs.MailsDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(
        name = "addFriendServlet",
        value = {"/addFriendServlet"}
)
public class AddFriendServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("UserNameOfFriend");
        String messageForFriend = request.getParameter("MessageForAddingFriend");
        String ourUser = (String)request.getSession().getAttribute("UserName");
        AccountDAO acc = new AccountDAO();


        try {
            if (acc.accountUsernameExists(userName)) {
                FriendDAO friendDAO = new FriendDAO();
                if(friendDAO.areFriends(userName, ourUser)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("alreadyFriends.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                MailsDao mailsDao = new MailsDao();
                mailsDao.addMail(ourUser, userName, MailsDao.ADD_FRIEND, messageForFriend);
                RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("noSuchUser.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
