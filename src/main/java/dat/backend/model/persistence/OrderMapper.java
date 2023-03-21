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
        String sqlStatement = "SELECT * FROM order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet rs = preparedStatement.executeQuery();
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
        String sqlStatement = "SELECT * FROM order WHERE fk_user_email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, user.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
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
        String sqlStatementOrder = "INSERT INTO order (fk_user_email, readytime, totalprice, cupcakecount, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatementInsertOrder = connection.prepareStatement(sqlStatementOrder, Statement.RETURN_GENERATED_KEYS);
            preparedStatementInsertOrder.setString(1, order.getUser().getEmail());
            preparedStatementInsertOrder.setDate(2, Date.valueOf(order.getReadyTime().toLocalDate()));
            preparedStatementInsertOrder.setFloat(3, order.getShoppingCart().getTotalPrice());
            preparedStatementInsertOrder.setInt(4, order.getShoppingCart().getCupcakeList().size());
            preparedStatementInsertOrder.setString(5, order.getStatus().toString().toUpperCase());

            preparedStatementInsertOrder.executeUpdate();
            ResultSet rs = preparedStatementInsertOrder.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getInt(1));
            }

            String sqlStatementCupcake = "INSERT INTO cupcake (fk_cupcaketop_id, fk_cupcakebottom_id, fk_order_id) VALUES (?, ?, ?)";
            for (Cupcake cupcake : order.getShoppingCart().getCupcakeList()) {
                try {
                    PreparedStatement preparedStatementInsertCupcake = connection.prepareStatement(sqlStatementCupcake);
                    preparedStatementInsertCupcake.setInt(1, cupcake.getTop().getId());
                    preparedStatementInsertCupcake.setInt(2, cupcake.getBottom().getId());
                    preparedStatementInsertCupcake.setInt(3, order.getId());
                    preparedStatementInsertCupcake.executeUpdate();
                } catch (SQLException e) {
                    throw new DatabaseException(e, "Could not create cupcake in database");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not create order in database");
        }
    }

    static void updateOrderStatus(int orderId, OrderStatus status, Connection connection) throws DatabaseException {
        String sqlStatement = "UPDATE order SET status = ? WHERE order_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, status.toString().toUpperCase());
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not update order status in database");
        }
    }

    private static ShoppingCart getShoppingCartByOrderId(int orderId, Connection connection) throws DatabaseException {
        ShoppingCart shoppingCart = new ShoppingCart();
        String sql = "SELECT * FROM cupcake WHERE fk_order_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
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


    private static Top getTopById(int id, Connection connection) throws DatabaseException {
        String sqlStatement = "SELECT * FROM cupcaketop WHERE cupcaketop_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
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

    private static Bottom getBottomById(int id, Connection connection) throws DatabaseException {
        String sqlStatement = "SELECT * FROM cupcakebottom WHERE cupcakebottom_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
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