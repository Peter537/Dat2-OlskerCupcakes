package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.util.List;

public class OrderFacade {

    public static List<Order> getAllOrders(Connection connection) throws DatabaseException {
        return OrderMapper.getAllOrders(connection);
    }

    public static List<Order> getAllOrdersByUser(User user, Connection connection) throws DatabaseException {
        return OrderMapper.getAllOrdersByUser(user, connection);
    }

    public static void createOrder(Order order, Connection connection) throws DatabaseException {
        OrderMapper.createOrder(order, connection);
    }

    public static void deleteOrder(int orderId, Connection connection) throws DatabaseException {
        OrderMapper.deleteOrder(orderId, connection);
    }
}