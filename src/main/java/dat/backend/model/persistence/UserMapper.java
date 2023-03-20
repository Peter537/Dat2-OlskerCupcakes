package dat.backend.model.persistence;

import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper {

    static User login(String email, String password, Connection connection) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        User user;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                user = new User(email, password, Role.valueOf(role.toUpperCase()));
            } else {
                throw new DatabaseException("Wrong username or password");
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    static User createUser(String email, String password, Role role, Connection connection) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into user (email, password, role) values (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, role.toString().toUpperCase());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                user = new User(email, password, role);
            } else {
                throw new DatabaseException("The user with username = " + email + " could not be inserted into the database");
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return user;
    }

    static List<User> getAllUsers(Connection connection) throws DatabaseException {
        List<User> users = new ArrayList<>();
        try {
            Logger.getLogger("web").log(Level.INFO, "");


            String sql = "SELECT * FROM user";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

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