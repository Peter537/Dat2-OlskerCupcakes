package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangePassword", value = "/ChangePassword")
public class ChangePassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String newPassword = request.getParameter("newPassword");
        user.setPassword(newPassword);
        try {
            UserFacade.setNewPassword(user, ApplicationStart.getConnectionPool().getConnection());
        } catch (DatabaseException | SQLException e) {
            request.setAttribute("changePasswordError", e.getMessage());
        }
        request.getRequestDispatcher("WEB-INF/userpage.jsp").forward(request, response);
    }
}
