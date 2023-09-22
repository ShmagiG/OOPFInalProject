package UserLoginSystem;

import DAOs.AccountDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "CreateAccountServlet", value = "/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountManager manager = (AccountManager) getServletContext().getAttribute("Manager");
        String userName = request.getParameter("UserName");
        String password = request.getParameter("Password");
        AccountDAO acc = new AccountDAO();
        try {
            if(acc.addAccount(userName, password)){
                request.getSession().setAttribute("UserName", userName);
                RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("tryAgainPage.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
