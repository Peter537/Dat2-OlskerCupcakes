package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.CupcakeFacade;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // You shouldn't end up here with a GET-request, thus you get sent back to frontpage
        response.sendRedirect("index.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.setAttribute("user", null); // invalidating user object in session scope
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = UserFacade.login(username, password, connection);

            session = request.getSession();
            session.setAttribute("user", user); // adding user object to session scope
            if (user.getRole() == Role.ADMIN) {
                request.getSession().setAttribute("users", UserFacade.getAllUsers(connection));
                List<Order> orders = OrderFacade.getAllOrders(connection);
                orders.sort(Comparator.comparingInt(o -> o.getStatus().getValue()));
                Collections.reverse(orders);

                request.getSession().setAttribute("orders", orders);
                request.getRequestDispatcher("WEB-INF/adminpage.jsp").forward(request, response);
                return;
            }
            request.getRequestDispatcher("WEB-INF/userpage.jsp").forward(request, response);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            if (e.getMessage().equals("Wrong username or password")) {
                request.setAttribute("msg", "Wrong username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}