package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class OrderMapper {

    static List<Order> getAllOrders(Connection connection) {
        return new ArrayList<>();
    }

    static List<Order> getAllOrdersByUser(User user, Connection connection) {
        return new ArrayList<>();
    }

    static void createOrder(Order order, Connection connection) {

    }

    static void deleteOrder(int orderId, Connection connection) {

    }
}