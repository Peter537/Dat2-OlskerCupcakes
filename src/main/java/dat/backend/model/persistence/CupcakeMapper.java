package dat.backend.model.persistence;

import dat.backend.model.entities.*;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class CupcakeMapper {

    public static Top getTopById(int id, ConnectionPool connectionPool) {

        try {
            String SqlStatement = "SELECT * FROM cupcaketop WHERE cupcaketop_id = ?";
            PreparedStatement pstmt = connectionPool.getConnection().prepareStatement(SqlStatement);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int cupcaketop_id = rs.getInt("cupcaketop_id");
                String cupcakeTopping = rs.getString("topping");
                float cupcaketop_price = rs.getFloat("price");
                return new Top(cupcaketop_id, cupcakeTopping, cupcaketop_price);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Bottom getBottomById(int id, ConnectionPool connectionPool) {

        try {
            String SqlStatement = "SELECT * FROM cupcakebottom WHERE cupcakebottom_id = ?";
            PreparedStatement pstmt = connectionPool.getConnection().prepareStatement(SqlStatement);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int cupcakebottom_id = rs.getInt("cupcakebottom_id");
                String cupcakeBottom = rs.getString("bottom");
                float cupcakebottom_price = rs.getFloat("price");
                return new Bottom(cupcakebottom_id, cupcakeBottom, cupcakebottom_price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Top> getAllToppings(ConnectionPool connectionPool) {

        try {
            String sqlStatement = "SELECT * FROM cupcaketop";
            PreparedStatement pstmt = connectionPool.getConnection().prepareStatement(sqlStatement);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Top> tops = new ArrayList<>();

            while (rs.next()) {
                int cupcaketop_id = rs.getInt("cupcaketop_id");
                String cupcaketopping = rs.getString("topping");
                float cupcaketop_price = rs.getFloat("price");
                tops.add(new Top(cupcaketop_id, cupcaketopping, cupcaketop_price));
            }
            return tops;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Bottom> getAllBottoms(ConnectionPool connectionPool) {
        try {
            String sqlStatement = "SELECT * FROM cupcaketop";
            PreparedStatement pstmt = connectionPool.getConnection().prepareStatement(sqlStatement);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Bottom> bottoms = new ArrayList<>();

            while (rs.next()) {
                int cupcakebottom_id = rs.getInt("cupcakebottom_id");
                String cupcakeBottom = rs.getString("bottom");
                float cupcakebottom_price = rs.getFloat("price");
                bottoms.add(new Bottom(cupcakebottom_id, cupcakeBottom, cupcakebottom_price));
            }
            return bottoms;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Order> getAllOrders(ConnectionPool connectionPool) {
        try {
            String statement = "SELECT * FROM order";
            PreparedStatement pstmt = connectionPool.getConnection().prepareStatement(statement);

            ResultSet rs = pstmt.executeQuery();

            ArrayList<Order> orders = new ArrayList<>();

            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                String email = rs.getString("fk_user_email");
                LocalDateTime time = rs.getDate("readytime").toLocalDate().atStartOfDay();
                float totalprice = rs.getFloat("totalprice");
                int cupcakecount = rs.getInt("cupcakecount");
                String status = rs.getString("status");
                Order order = new Order(order_id, null, time, null);
//TODO: Add more to order constructor, and then fix above line (123) so it uses the correct values.
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void createOrder(Order order, ConnectionPool connectionPool) {

        try {
            String sqlStatement = "INSERT INTO order (fk_user_email, readytime, totalprice, cupcakecount, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connectionPool.getConnection().prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, order.getUser().getEmail());
            pstmt.setDate(2, Date.valueOf(order.getReadyTime().toLocalDate()));
            pstmt.setFloat(3, order.getShoppingCart().getTotalPrice());
            pstmt.setInt(4, order.getShoppingCart().getCupcakeList().size());
            pstmt.setString(5, order.getStatus().toString().toUpperCase());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getInt(1));
            }

            String insertOrderlist = "INSERT INTO order (fk_cupcaketop_id, fk_cupcakebottom_id, fk_order_id) VALUES (?, ?, ?)";

            for (Cupcake cupcake : order.getShoppingCart().getCupcakeList()) {
                try {
                    PreparedStatement pstmt2 = connectionPool.getConnection().prepareStatement(insertOrderlist);
                    pstmt2.setInt(1, cupcake.getTop().getId());
                    pstmt2.setInt(2, cupcake.getBottom().getId());
                    pstmt2.setInt(3, order.getId());
                    pstmt2.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}