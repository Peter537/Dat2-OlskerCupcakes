package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.OrderStatus;
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
        ShoppingCart cart = user.getCurrentOrder().getShoppingCart();

        if (user.getBalance() < cart.getTotalPrice()) {
            request.setAttribute("msgmoney", "Du har ikke nok penge på din konto til at købe denne vare");
            request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
            return;
        } else {
            user.setBalance(user.getBalance() - cart.getTotalPrice());
        }

        String readyTimeDate = request.getParameter("readyTimeDate");
        String readyTimeHour = request.getParameter("readyTimeHour");
        String readyTimeMinute = request.getParameter("readyTimeMinute");
        System.out.println("ReadyTimeDate: " + readyTimeDate);
        System.out.println("ReadyTimeHour: " + readyTimeHour);
        System.out.println("ReadyTimeMinute: " + readyTimeMinute);
        if (readyTimeDate == null || readyTimeHour == null || readyTimeMinute == null) {
            request.setAttribute("msgReadyTime", "Du skal vælge en afhentningsdato og tid");
            request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
            return;
        }
        String readyTimeStr = readyTimeDate + " " + readyTimeHour + ":" + readyTimeMinute;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(readyTimeStr);
        LocalDateTime readyTime = LocalDateTime.parse(readyTimeStr, formatter);
        user.getCurrentOrder().setReadyTime(readyTime);
        user.getCurrentOrder().setStatus(OrderStatus.PENDING);
        try {
            OrderFacade.createOrder(user.getCurrentOrder(), connection);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("order", user.getCurrentOrder());
        request.setAttribute("currentcart", cart);
        request.setAttribute("readyTime", readyTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        user.setCurrentOrder(new Order(user));
        request.getRequestDispatcher("WEB-INF/confirmation.jsp").forward(request, response);
    }
}