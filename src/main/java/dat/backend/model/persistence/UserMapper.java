package dat.backend.model.persistence;

import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class UserMapper {

    static User login(String email, String password, Connection connection) throws DatabaseException {
        String sqlStatement = "SELECT * FROM user WHERE email = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                return new User(email, password, Role.valueOf(role.toUpperCase()));
            } else {
                throw new DatabaseException("Wrong username or password");
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
    }

    static User createUser(String email, String password, Role role, Connection connection) throws DatabaseException {
        String sqlStatement = "INSERT INTO user (email, password, role) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role.toString().toUpperCase());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                return new User(email, password, role);
            } else {
                throw new DatabaseException("The user with email = '" + email + "' could not be inserted into the database");
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
    }

    public static User getUserByEmail(String email, Connection connection) throws DatabaseException {
        String sqlStatement = "SELECT * FROM user WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                String role = rs.getString("role");
                return new User(email, password, Role.valueOf(role.toUpperCase()));
            } else {
                throw new DatabaseException("User with email = '" + email + "' does not exist");
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Error getting user by email. Something went wrong with the database");
        }
    }

    static List<User> getAllUsers(Connection connection) throws DatabaseException {
        List<User> users = new ArrayList<>();
        String sqlStatement = "SELECT * FROM user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                users.add(new User(email, password, Role.valueOf(role.toUpperCase())));
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not get all users from database");
        }

        return users;
    }
}