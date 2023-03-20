package dat.backend.model.persistence;

import dat.backend.model.entities.Bottom;
import dat.backend.model.entities.ShoppingCart;
import dat.backend.model.entities.Top;
import dat.backend.model.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public class CupcakeFacade {

    public static Top getTopById(int id, ConnectionPool connectionPool) {
        return CupcakeMapper.getTopById(id, connectionPool);
    }

    public static Bottom getBottomById(int id, ConnectionPool connectionPool) {
        return CupcakeMapper.getBottomById(id, connectionPool);
    }

    public static List<Top> getAllToppings(ConnectionPool connectionPool) {
        return CupcakeMapper.getAllToppings(connectionPool);
    }

    public static List<Bottom> getAllBottoms(ConnectionPool connectionPool) {
        return CupcakeMapper.getAllBottoms(connectionPool);
    }

    public static void createOrder(User user, LocalDateTime readyTime, ShoppingCart shoppingCart, ConnectionPool connectionPool) {
        CupcakeMapper.createOrder(user, readyTime, shoppingCart, connectionPool);
    }
}