package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.OrderStatus;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.util.List;

public class OrderFacade {

    public static List<Order> getAllOrders(Connection connection) throws DatabaseException {
        return getAllOrders(connection, false);
    }

    public static List<Order> getAllOrders(Connection connection, boolean isTest) throws DatabaseException {
        return OrderMapper.getAllOrders(connection, isTest);
    }

    public static List<Order> getAllOrdersByUser(User user, Connection connection) throws DatabaseException {
        return getAllOrdersByUser(user, connection, false);
    }

    public static List<Order> getAllOrdersByUser(User user, Connection connection, boolean isTest) throws DatabaseException {
        return OrderMapper.getAllOrdersByUser(user, connection, isTest);
    }

    public static void createOrder(Order order, Connection connection) throws DatabaseException {
        createOrder(order, connection, false);
    }

    public static void createOrder(Order order, Connection connection, boolean isTest) throws DatabaseException {
        OrderMapper.createOrder(order, connection, isTest);
    }

    public static void updateOrderStatus(int orderId, OrderStatus status, Connection connection) throws DatabaseException {
        updateOrderStatus(orderId, status, connection, false);
    }

    public static void updateOrderStatus(int orderId, OrderStatus status, Connection connection, boolean isTest) throws DatabaseException {
        OrderMapper.updateOrderStatus(orderId, status, connection, isTest);
    }
}