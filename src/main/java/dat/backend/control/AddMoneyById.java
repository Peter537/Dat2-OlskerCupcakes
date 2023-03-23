package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "AddMoneyById", value = "/AddMoneyById")
public class AddMoneyById extends HttpServlet {

    private Connection connection;

    @Override
    public void init() {
        try {
            this.connection = ApplicationStart.getConnectionPool().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        User user;
        try {
            user = UserFacade.getUserByEmail(userEmail, connection);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        float amount = Float.parseFloat(request.getParameter("amount"));
        try {
            user.addBalance(amount);
            UserFacade.updateBalance(user, connection);
            request.getSession().setAttribute("users", UserFacade.getAllUsers(connection));
        } catch (NumberFormatException e) {
            request.setAttribute("addMoneyError", "Inkorrekt beløb");
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("WEB-INF/adminpage.jsp").forward(request, response);
    }
}