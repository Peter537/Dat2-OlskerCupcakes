package dat.backend.control;

import dat.backend.model.entities.Cupcake;
import dat.backend.model.entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddToCart", value = "/AddToCart")
public class AddToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cupcake cupcake = (Cupcake) request.getAttribute("cupcake");
        User user = (User) request.getSession().getAttribute("user");
        user.getShoppingCart().addCupcake(cupcake);
        request.setAttribute("cupcake", null);
        request.setAttribute("bottom", null);
        request.setAttribute("topping", null);
        request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
    }
}