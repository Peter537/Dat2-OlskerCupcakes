package dat.backend.control;

import dat.backend.model.entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddMoney", value = "/AddMoney")
public class AddMoney extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        try {
            float amount = Float.parseFloat(request.getParameter("addedAmount"));
            user.addBalance(amount);
        } catch (NumberFormatException e) {
            request.setAttribute("addMoneyError", "Please enter a valid amount");
        }
        request.getRequestDispatcher("WEB-INF/userpage.jsp").forward(request, response);
    }
}
