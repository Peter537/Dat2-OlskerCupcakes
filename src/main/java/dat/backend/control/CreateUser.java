package dat.backend.control;


import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CreateUser", value = "/CreateUser")
public class CreateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            Connection connection = ApplicationStart.getConnectionPool().getConnection();

            List<User> userList = UserFacade.getAllUsers(connection);

            String newUsername = request.getParameter("username");
            String newUserPassword = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (userList.stream().anyMatch(user -> user.getEmail().equals(newUsername))) {
                request.setAttribute("errormessage", "Username already exists");
                request.getRequestDispatcher("createuser.jsp").forward(request, response);

            }

            if (!newUserPassword.equals(confirmPassword)) {
                request.setAttribute("errormessage", "Passwords do not match");
                request.getRequestDispatcher("createuser.jsp").forward(request, response);
            } else {
                UserFacade.createUser(newUsername, newUserPassword, Role.CUSTOMER, connection);
                User user = new User(newUsername, newUserPassword, Role.CUSTOMER);
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("WEB-INF/userpage.jsp").forward(request, response);
            }

        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }


    }
}
