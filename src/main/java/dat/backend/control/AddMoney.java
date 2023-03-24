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

@WebServlet(name = "AddMoney", value = "/AddMoney")
public class AddMoney extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            float amount = Float.parseFloat(request.getParameter("addedAmount").replace(",", "."));
            user.addBalance(amount);
            UserFacade.updateBalance(user, ApplicationStart.getConnectionPool().getConnection());
        } catch (NumberFormatException e) {
            request.setAttribute("addMoneyError", "Please enter a valid amount");
        } catch (DatabaseException | SQLException e) {
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("WEB-INF/userpage.jsp").forward(request, response);
    }
}