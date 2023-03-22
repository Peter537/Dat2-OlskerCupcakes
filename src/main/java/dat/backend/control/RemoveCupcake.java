package dat.backend.control;
import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Bottom;
import dat.backend.model.entities.Cupcake;
import dat.backend.model.entities.ShoppingCart;
import dat.backend.model.entities.Top;
import dat.backend.model.persistence.CupcakeFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
@WebServlet(name = "RemoveCupcake", value = "/RemoveCupcake")

public class RemoveCupcake extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Cupcake cupcake = request.getParameter("cupcake");
            ShoppingCart shoppingCart = request.getParameter("shoppingCart");
            shoppingCart.removeCupcake(cupcake);
            request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
