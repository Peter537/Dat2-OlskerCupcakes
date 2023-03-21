package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Bottom;
import dat.backend.model.entities.Cupcake;
import dat.backend.model.entities.Top;
import dat.backend.model.persistence.CupcakeFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "BuildCupcake", value = "/BuildCupcake")
public class BuildCupcake extends HttpServlet {

    private Connection connection;

    @Override
    public void init() {
        try {
            connection = ApplicationStart.getConnectionPool().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String toppingName = request.getParameter("toppingName");
            String bottomName = request.getParameter("bottomName");

            Top top = CupcakeFacade.getTopByName(toppingName, connection);
            Bottom bottom = CupcakeFacade.getBottomByName(bottomName, connection);
            Cupcake cupcake = new Cupcake(bottom, top);

            request.setAttribute("topping", top);
            request.setAttribute("bottom", bottom);
            request.setAttribute("cupcake_price", cupcake.getPrice());
            request.getRequestDispatcher("WEB-INF/order.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}