package dat.backend.control;
import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.CupcakeFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

@WebServlet(name = "RemoveCupcake", value = "/RemoveCupcake")

public class RemoveCupcake extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            User user = (User) request.getSession().getAttribute("user");
            ShoppingCart shoppingCart = user.getShoppingCart();

            shoppingCart.removeCupcakeById(Integer.parseInt(request.getParameter("cupcake")));



            request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
    }
}
 /*try {




         Cupcake cupcake = (Cupcake) CupcakeFacade.getCupcakeById(request.getParameter("cupcake"));
         ShoppingCart shoppingCart = request.getParameter("shoppingCart");
         shoppingCart.removeCupcake(cupcake);
         request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
         } catch (Exception e) {
         e.printStackTrace();
         }
         */