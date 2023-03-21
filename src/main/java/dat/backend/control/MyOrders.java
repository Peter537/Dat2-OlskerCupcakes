package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dat.backend.model.persistence.OrderFacade.getAllOrdersByUser;

@WebServlet(name = "MyOrders", value = "/MyOrders")
public class MyOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Connection connection = ApplicationStart.getConnectionPool().getConnection();

            User user = (User) request.getSession().getAttribute("user");
           List<Order> orderList =  OrderFacade.getAllOrdersByUser(user, connection);

           for(Order order : orderList){

               System.out.println(order);

           }
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("WEB-INF/myOrders.jsp").forward(request, response);

        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }


    }
}
