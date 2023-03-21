package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class OrderMapper {

    static List<Order> getAllOrders(Connection connection) throws DatabaseException {
        try {
            String statement = "SELECT * FROM order";
            PreparedStatement pstmt = connection.prepareStatement(statement);

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
            throw new DatabaseException(e, "Could not get all orders from database");
        }
        return new ArrayList<>();
    }

    static List<Order> getAllOrdersByUser(User user, Connection connection) throws DatabaseException {

        try {
            String sql = "SELECT * FROM order WHERE fk_user_email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Order> userOrders = new ArrayList<>();

            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                LocalDateTime time = rs.getDate("readytime").toLocalDate().atStartOfDay();
                OrderStatus status = OrderStatus.valueOf(rs.getString("status").toUpperCase());
                ShoppingCart shoppingCart = getShoppingCartByOrderId(order_id, connection);
                Order order = new Order(order_id, user, time, shoppingCart, status);

            }

        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get all orders from a user from database");
        }

        return new ArrayList<>();
    }

    static void createOrder(Order order, Connection connection) throws DatabaseException {
        try {
            String sqlStatement = "INSERT INTO order (fk_user_email, readytime, totalprice, cupcakecount, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
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
                    PreparedStatement pstmt2 = connection.prepareStatement(insertOrderlist);
                    pstmt2.setInt(1, cupcake.getTop().getId());
                    pstmt2.setInt(2, cupcake.getBottom().getId());
                    pstmt2.setInt(3, order.getId());
                    pstmt2.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not create order in database");
        }
    }

    static void deleteOrder(int orderId, Connection connection) throws DatabaseException {

        try {
            String sqlStatement = "DELETE FROM order WHERE order_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sqlStatement);
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not delete order from database");
        }


    }

    private static ShoppingCart getShoppingCartByOrderId(int orderId, Connection connection) {
        ShoppingCart shoppingCart = new ShoppingCart();
        ArrayList<Cupcake> cupcakeList = new ArrayList<>();
        String sql = "SELECT * FROM cupcake WHERE fk_order_id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int cupcake_id = rs.getInt("cupcake_id");
                int fk_cupcaketop_id = rs.getInt("fk_cupcaketop_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }








        // TODO: Implement this method

        return shoppingCart;
    }
}