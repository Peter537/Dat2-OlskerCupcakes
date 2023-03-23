package dat.backend.control;

import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ToUserpage", value = "/ToUserpage")
public class ToUserpage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            request.getRequestDispatcher("login.jsp").forward(request, response);
        else if (user.getRole().equals(Role.ADMIN))
            request.getRequestDispatcher("WEB-INF/adminpage.jsp").forward(request, response);
        else
            request.getRequestDispatcher("WEB-INF/userpage.jsp").forward(request, response);
    }
}