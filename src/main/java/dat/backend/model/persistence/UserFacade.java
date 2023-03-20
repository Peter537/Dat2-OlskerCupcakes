package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class UserFacade {

    public static User login(String username, String password, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.login(username, password, connectionPool);
    }

    public static User createUser(String username, String password, Role role, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.createUser(username, password, role, connectionPool);
    }

    public static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        return null;
        //return UserMapper.getAllUsers(connectionPool);
    }

    public static List<Order> getAllUserOrders(String email, ConnectionPool connectionPool) throws DatabaseException {
        return null;
        //return UserMapper.getAllUserOrders(email, connectionPool);
    }

    public static void deleteOrder(int id, ConnectionPool connectionPool) throws DatabaseException {
        //UserMapper.deleteOrder(id, connectionPool);
    }
}