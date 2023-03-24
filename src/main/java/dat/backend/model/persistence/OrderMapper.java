package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class OrderMapper {

    static List<Order> getAllOrders(Connection connection, boolean isTest) throws DatabaseException {
        ArrayList<Order> orders = new ArrayList<>();
        String sqlStatement = "SELECT * FROM olskerCupcakes" + (isTest ? "_test" : "") + ".order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                String email = rs.getString("fk_user_email");
                LocalDateTime time = rs.getDate("readytime").toLocalDate().atStartOfDay(); // TODO: Fjerne atStartOfDay() når vi har tid
                OrderStatus status;
                if (rs.getString("status") == null) {
                    status = OrderStatus.CANCELLED;
                } else {
                    status = OrderStatus.valueOf(rs.getString("status").toUpperCase());
                }
                ShoppingCart shoppingCart = getShoppingCartByOrderId(order_id, connection);
                User user = UserMapper.getUserByEmail(email, connection);
                Order order = new Order(order_id, user, time, shoppingCart, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get all orders from database");
        }
        return orders;
    }

    static List<Order> getAllOrdersByUser(User user, Connection connection, boolean isTest) throws DatabaseException {
        ArrayList<Order> orders = new ArrayList<>();
        String sqlStatement = "SELECT * FROM olskerCupcakes" + (isTest ? "_test" : "") + ".order WHERE fk_user_email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, user.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                LocalDateTime time = rs.getDate("readytime").toLocalDate().atStartOfDay();
                OrderStatus status;
                if (rs.getString("status") == null) {
                    status = OrderStatus.CANCELLED;
                } else {
                    status = OrderStatus.valueOf(rs.getString("status").toUpperCase());
                }
                ShoppingCart shoppingCart = getShoppingCartByOrderId(order_id, connection);
                Order order = new Order(order_id, user, time, shoppingCart, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get all orders from a user from database");
        }

        return orders;
    }

    static void createOrder(Order order, Connection connection, boolean isTest) throws DatabaseException {
        String sqlStatementOrder = "INSERT INTO olskerCupcakes" + (isTest ? "_test" : "") + ".order (fk_user_email, readytime, totalprice, cupcakecount, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatementInsertOrder = connection.prepareStatement(sqlStatementOrder, Statement.RETURN_GENERATED_KEYS);
            preparedStatementInsertOrder.setString(1, order.getUser().getEmail());
            String time = order.getReadyTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            preparedStatementInsertOrder.setString(2, time);
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

    static void updateOrderStatus(int orderId, OrderStatus status, Connection connection, boolean isTest) throws DatabaseException {
        String sqlStatement = "UPDATE olskerCupcakes" + (isTest ? "_test" : "") + ".order SET status = ? WHERE order_id = ?";
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
                Top top = CupcakeFacade.getTopById(rs.getInt("fk_cupcaketop_id"), connection);
                Bottom bottom = CupcakeFacade.getBottomById(rs.getInt("fk_cupcakebottom_id"), connection);
                Cupcake cupcake = new Cupcake(cupcake_id, bottom, top);
                shoppingCart.addCupcake(cupcake);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get shopping cart by order id from database");
        }

        return shoppingCart;
    }
}