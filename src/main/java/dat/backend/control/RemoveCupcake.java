package dat.backend.control;

import dat.backend.model.entities.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveCupcake", value = "/RemoveCupcake")
public class RemoveCupcake extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        ShoppingCart shoppingCart = user.getCurrentOrder().getShoppingCart();
        shoppingCart.removeCupcakeById(Integer.parseInt(request.getParameter("cupcake")));
        request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
    }
}