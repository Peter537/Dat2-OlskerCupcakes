package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "UpdateOrdersList", value = "/UpdateOrdersList")
public class UpdateOrdersList extends HttpServlet {

    private ConnectionPool connection;

    @Override
    public void init() {
        this.connection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders;
        try {
            orders = OrderFacade.getAllOrders(connection.getConnection());
        } catch (DatabaseException | SQLException e) {
            throw new RuntimeException(e);
        }

        orders.sort(Comparator.comparingInt(o -> o.getStatus().getValue()));
        Collections.reverse(orders);
        request.getSession().setAttribute("orders", orders);

        request.getRequestDispatcher("WEB-INF/adminpage.jsp").forward(request, response);
    }
}