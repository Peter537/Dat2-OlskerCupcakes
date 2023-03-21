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
import java.util.List;

@WebServlet(name = "BuildCupcake", value = "/BuildCupcake")
public class BuildCupcake extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = ApplicationStart.getConnectionPool().getConnection();
            String toppingName = request.getParameter("topping");

            List<Top> topList = CupcakeFacade.getAllToppings(connection);
            List<Bottom> bottomList = CupcakeFacade.getAllBottoms(connection);

            int topIndex = -1;
            for (int i = 0; i < topList.size(); i++) {
                if (topList.get(i).getName().equals(toppingName)) {
                    topIndex = i;
                }
            }

            int bottomIndex = -1;
            for (int i = 0; i < bottomList.size(); i++) {
                if (bottomList.get(i).getName().equals(toppingName)) {
                    bottomIndex = i;
                }
            }

            Top top = topList.get(topIndex);
            Bottom bottom = bottomList.get(bottomIndex);

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
