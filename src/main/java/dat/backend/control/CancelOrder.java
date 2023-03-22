package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.OrderStatus;
import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "CancelOrder", value = "/CancelOrder")
public class CancelOrder extends HttpServlet {
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
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        try {
            OrderFacade.updateOrderStatus(orderId, OrderStatus.CANCELLED, connection);
            User user = (User) request.getSession().getAttribute("user");
            if (user.getRole() == Role.ADMIN) {
                request.getRequestDispatcher("WEB-INF/adminpage.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("WEB-INF/userpage.jsp").forward(request, response);
            }
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
