package UserPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.faces.annotation.RequestMap;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserStatsServlet", value = "/UserStatsServlet")
public class UserStatsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException  {
        request.setAttribute("object","OBJ");
        request.getRequestDispatcher("stats.jsp").forward(request,response);
    }

}
