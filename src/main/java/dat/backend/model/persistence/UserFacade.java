package dat.backend.model.persistence;

import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.util.List;

public class UserFacade {

    public static User login(String email, String password, Connection connection) throws DatabaseException {
        return UserMapper.login(email, password, connection);
    }

    public static User createUser(String email, String password, Role role, Connection connection) throws DatabaseException {
        return UserMapper.createUser(email, password, role, connection);
    }

    public static User getUserByEmail(String email, Connection connection) throws DatabaseException {
        return UserMapper.getUserByEmail(email, connection);
    }

    public static List<User> getAllUsers(Connection connection) throws DatabaseException {
        return UserMapper.getAllUsers(connection);
    }

    public static void setNewPassword(User user, Connection connection) throws DatabaseException {
        UserMapper.setNewPassword(user, connection);
    }
}