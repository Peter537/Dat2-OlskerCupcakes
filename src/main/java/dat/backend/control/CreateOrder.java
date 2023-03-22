package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.ShoppingCart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "CreateOrder", value = "/CreateOrder")
public class CreateOrder extends HttpServlet {

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
        User user = (User) request.getSession().getAttribute("user");
        String readyTimeStr = request.getParameter("readyTime");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(readyTimeStr);
        //LocalDateTime readyTime = LocalDateTime.parse(readyTimeStr, formatter);
        LocalDateTime readyTime = LocalDateTime.now();
        Order order = new Order(user, readyTime);
        try {
            OrderFacade.createOrder(order, connection);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("order", order);
        ShoppingCart cart = user.getShoppingCart();
        request.setAttribute("currentcart", cart);
        user.setShoppingCart(new ShoppingCart());
        request.getRequestDispatcher("WEB-INF/confirmation.jsp").forward(request, response);
    }
}
