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
        name = "acceptFriendRequestServlet",
        value = {"/acceptFriendRequestServlet"}
)
public class AcceptFriendRequestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = (String) request.getSession().getAttribute("UserName");
        String friendRequestSender = request.getParameter("FriendSender");
        int mailId = Integer.parseInt(request.getParameter("MailId"));
        FriendDAO friendDAO = new FriendDAO();
        MailsDao mailsDao = new MailsDao();
        try {
            friendDAO.addFriend(userName, friendRequestSender);
            mailsDao.removeMail(mailId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("mails.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
