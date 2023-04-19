package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "AdminUserOverview", value = "/AdminUserOverview")
public class AdminUserOverview extends HttpServlet {

    private ConnectionPool connection;

    @Override
    public void init() {
        this.connection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        try {
            user = UserFacade.getUserByEmail(request.getParameter("chosenuser"), connection.getConnection());
        } catch (DatabaseException | SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("chosenuser", user);
        List<Order> orders = null;
        try {
            orders = OrderFacade.getAllOrdersByUser(user, connection.getConnection());
        } catch (DatabaseException | SQLException e) {
            throw new RuntimeException(e);
        }

        orders.sort(Comparator.comparingInt(o -> o.getStatus().getValue()));
        Collections.reverse(orders);
        request.setAttribute("userorders", orders);
        request.getRequestDispatcher("WEB-INF/admincustomerview.jsp").forward(request, response);
    }
}