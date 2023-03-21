package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class OrderMapper {

    static List<Order> getAllOrders(Connection connection) throws DatabaseException {
        ArrayList<Order> orders = new ArrayList<>();
        String statement = "SELECT * FROM order";
        try {
            PreparedStatement pstmt = connection.prepareStatement(statement);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                String email = rs.getString("fk_user_email");
                LocalDateTime time = rs.getDate("readytime").toLocalDate().atStartOfDay();
                OrderStatus status = OrderStatus.valueOf(rs.getString("status").toUpperCase());
                ShoppingCart shoppingCart = getShoppingCartByOrderId(order_id, connection);
                Order order = new Order(order_id, UserMapper.getUserByEmail(email, connection), time, shoppingCart, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get all orders from database");
        }
        return orders;
    }

    static List<Order> getAllOrdersByUser(User user, Connection connection) throws DatabaseException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM order WHERE fk_user_email = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                LocalDateTime time = rs.getDate("readytime").toLocalDate().atStartOfDay();
                OrderStatus status = OrderStatus.valueOf(rs.getString("status").toUpperCase());
                ShoppingCart shoppingCart = getShoppingCartByOrderId(order_id, connection);
                Order order = new Order(order_id, user, time, shoppingCart, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get all orders from a user from database");
        }

        return orders;
    }

    static void createOrder(Order order, Connection connection) throws DatabaseException {
        String sqlStatement = "INSERT INTO order (fk_user_email, readytime, totalprice, cupcakecount, status) VALUES (?, ?, ?, ?, ?)";
        try {
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

    static void updateOrderStatus(int orderId, OrderStatus status, Connection connection) throws DatabaseException {

    }

    private static ShoppingCart getShoppingCartByOrderId(int orderId, Connection connection) throws DatabaseException {
        ShoppingCart shoppingCart = new ShoppingCart();
        String sql = "SELECT * FROM cupcake WHERE fk_order_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int cupcake_id = rs.getInt("cupcake_id");
                Top top = getTopById(rs.getInt("fk_cupcaketop_id"), connection);
                Bottom bottom = getBottomById(rs.getInt("fk_cupcakebottom_id"), connection);
                Cupcake cupcake = new Cupcake(cupcake_id, bottom, top);
                shoppingCart.addCupcake(cupcake);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get shopping cart by order id from database");
        }

        return shoppingCart;
    }


    public static Top getTopById(int id, Connection connection) throws DatabaseException {
        String sqlStatement = "SELECT * FROM cupcaketop WHERE cupcaketop_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlStatement);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int cupcaketop_id = rs.getInt("cupcaketop_id");
                String cupcakeTopping = rs.getString("topping");
                float cupcaketop_price = rs.getFloat("price");
                return new Top(cupcaketop_id, cupcakeTopping, cupcaketop_price);
            } else {
                throw new DatabaseException("Could not get top by id from database");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get top by id from database");
        }
    }

    public static Bottom getBottomById(int id, Connection connection) throws DatabaseException {
        String sqlStatement = "SELECT * FROM cupcakebottom WHERE cupcakebottom_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlStatement);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int cupcakebottom_id = rs.getInt("cupcakebottom_id");
                String cupcakeBottom = rs.getString("bottom");
                float cupcakebottom_price = rs.getFloat("price");
                return new Bottom(cupcakebottom_id, cupcakeBottom, cupcakebottom_price);
            } else {
                throw new DatabaseException("Could not get bottom by id from database");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get bottom by id from database");
        }
    }
}