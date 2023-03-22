package dat.backend.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

class OrderMapperTest {

    private final static String USER = "root";
    private final static String PASSWORD = "123";
    private final static String URL = "jdbc:mysql://localhost:3306/olskerCupcakes_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static List<Order> expectedOrders;
    private static ConnectionPool connectionPool;
    private static Connection connection;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
        User user1 = new User("user", "1234", Role.CUSTOMER);
        for (int i = 0; i < 10; i++) {
            Order order = new Order(user1, LocalDateTime.now());
            expectedOrders.add(order);
        }

        User user2 = new User("user", "1234", Role.CUSTOMER);
        for (int i = 0; i < 5; i++) {
            Order order = new Order(user2, LocalDateTime.now());
            expectedOrders.add(order);
        }

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
    public void testGetAllOrders() {

    }
}