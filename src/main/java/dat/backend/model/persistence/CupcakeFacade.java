package dat.backend.model.persistence;

import dat.backend.model.entities.*;

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

    public static void createOrder(Order order, ConnectionPool connectionPool) {
        CupcakeMapper.createOrder(order, connectionPool);
    }
}