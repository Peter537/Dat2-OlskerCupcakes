package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;

import java.sql.Connection;
import java.util.List;

public class OrderFacade {

    public static List<Order> getAllOrders(Connection connection) {
        return OrderMapper.getAllOrders(connection);
    }

    public static List<Order> getAllOrdersByUser(User user, Connection connection) {
        return OrderMapper.getAllOrdersByUser(user, connection);
    }

    public static void createOrder(Order order, Connection connection) {
        OrderMapper.createOrder(order, connection);
    }

    public static void deleteOrder(int orderId, Connection connection) {
        OrderMapper.deleteOrder(orderId, connection);
    }
}