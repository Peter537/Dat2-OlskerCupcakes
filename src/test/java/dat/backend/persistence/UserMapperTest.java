package dat.backend.persistence;

import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final static String USER = "root";
    private final static String PASSWORD = "123";
    private final static String URL = "jdbc:mysql://localhost:3306/olskerCupcakes_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;
    private static Connection connection;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try {
            connection = connectionPool.getConnection();
            Statement stmt = connection.createStatement();
            // Create test database - if not exist
            stmt.execute("CREATE DATABASE IF NOT EXISTS olskerCupcakes_test;");

            // TODO: Create user table. Add your own tables here
            stmt.execute("CREATE TABLE IF NOT EXISTS olskerCupcakes_test.user LIKE olskerCupcakes.user;");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp() {
        try {
            Statement stmt = connection.createStatement();
            // TODO: Remove all rows from all tables - add your own tables here
            stmt.execute("DELETE FROM user");

            // TODO: Insert a few users - insert rows into your own tables here
            stmt.execute("INSERT INTO user (email, password, role) " +
                    "VALUES ('user', '1234', 'customer'), ('admin', '1234', 'admin'), ('ben', '1234', 'customer')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        assertNotNull(connection);
        if (!connection.isClosed()) {
            connection.close();
            assertTrue(connection.isClosed());
            connection = connectionPool.getConnection();
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        }
    }

    @Test
    void login() throws DatabaseException {
        User expectedUser = new User("user", "1234", Role.CUSTOMER);
        User actualUser = UserFacade.login("user", "1234", connection);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() {
        assertThrows(DatabaseException.class, () -> UserFacade.login("user", "123", connection));
    }

    @Test
    void invalidUserNameLogin() {
        assertThrows(DatabaseException.class, () -> UserFacade.login("bob", "1234", connection));
    }

    @Test
    void createUser() throws DatabaseException {
        User newUser = UserFacade.createUser("jill", "1234", Role.CUSTOMER, connection);
        User logInUser = UserFacade.login("jill", "1234", connection);
        User expectedUser = new User("jill", "1234", Role.CUSTOMER);
        assertEquals(expectedUser, newUser);
        assertEquals(expectedUser, logInUser);
    }
}