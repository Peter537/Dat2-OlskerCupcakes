package dat.backend.model.persistence;

import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.util.List;

public class UserFacade {

    public static User login(String username, String password, Connection connection) throws DatabaseException {
        return UserMapper.login(username, password, connection);
    }

    public static User createUser(String username, String password, Role role, Connection connection) throws DatabaseException {
        return UserMapper.createUser(username, password, role, connection);
    }

    public static User getUserByEmail(String email, Connection connection) throws DatabaseException {
        return UserMapper.getUserByEmail(email, connection);
    }

    public static List<User> getAllUsers(Connection connection) throws DatabaseException {
        return UserMapper.getAllUsers(connection);
    }
}