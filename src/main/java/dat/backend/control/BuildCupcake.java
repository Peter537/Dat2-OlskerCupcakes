package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Bottom;
import dat.backend.model.entities.Cupcake;
import dat.backend.model.entities.Top;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupcakeFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "BuildCupcake", value = "/BuildCupcake")
public class BuildCupcake extends HttpServlet {

    private ConnectionPool connection;

    @Override
    public void init() {
        connection = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int toppingID = Integer.parseInt(request.getParameter("toppingID"));
            int bottomID = Integer.parseInt(request.getParameter("bottomID"));

            Top top = CupcakeFacade.getTopById(toppingID, connection.getConnection());
            Bottom bottom = CupcakeFacade.getBottomById(bottomID, connection.getConnection());
            Cupcake cupcake = new Cupcake(bottom, top);

            request.setAttribute("topping", top);
            request.setAttribute("bottom", bottom);
            request.getSession().setAttribute("cupcake", cupcake);
            request.getRequestDispatcher("WEB-INF/order.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}