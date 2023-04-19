package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "AddMoneyById", value = "/AddMoneyById")
public class AddMoneyById extends HttpServlet {

    private ConnectionPool connection;

    @Override
    public void init() {
        this.connection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        User user;
        try {
            user = UserFacade.getUserByEmail(userEmail, connection.getConnection());
        } catch (DatabaseException | SQLException e) {
            throw new RuntimeException(e);
        }

        float amount = Float.parseFloat(request.getParameter("amount").replace(",", "."));
        try {
            user.addBalance(amount);
            UserFacade.updateBalance(user, connection.getConnection());
            request.getSession().setAttribute("users", UserFacade.getAllUsers(connection.getConnection()));
        } catch (NumberFormatException e) {
            request.setAttribute("addMoneyError", "Inkorrekt beløb");
        } catch (DatabaseException | SQLException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("WEB-INF/adminpage.jsp").forward(request, response);
    }
}