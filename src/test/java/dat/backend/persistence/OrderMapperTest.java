package dat.backend.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupcakeFacade;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class OrderMapperTest {

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
            stmt.execute("CREATE TABLE IF NOT EXISTS olskerCupcakes_test.order LIKE olskerCupcakes.order;");
            stmt.execute("CREATE TABLE IF NOT EXISTS olskerCupcakes_test.cupcake LIKE olskerCupcakes.cupcake;");
            stmt.execute("CREATE TABLE IF NOT EXISTS olskerCupcakes_test.cupcakebottom LIKE olskerCupcakes.cupcakebottom;");
            stmt.execute("CREATE TABLE IF NOT EXISTS olskerCupcakes_test.cupcaketop LIKE olskerCupcakes.cupcaketop;");
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
            stmt.execute("DELETE FROM olskerCupcakes_test.user");
            stmt.execute("DELETE FROM olskerCupcakes_test.order");
            stmt.execute("DELETE FROM olskerCupcakes_test.cupcake");
            stmt.execute("DELETE FROM olskerCupcakes_test.cupcakebottom");
            stmt.execute("DELETE FROM olskerCupcakes_test.cupcaketop");
            stmt.execute("TRUNCATE TABLE olskerCupcakes_test.user");
            stmt.execute("TRUNCATE TABLE olskerCupcakes_test.order");
            stmt.execute("TRUNCATE TABLE olskerCupcakes_test.cupcake");
            stmt.execute("TRUNCATE TABLE olskerCupcakes_test.cupcakebottom");
            stmt.execute("TRUNCATE TABLE olskerCupcakes_test.cupcaketop");

            // TODO: Insert a few users - insert rows into your own tables here
            stmt.execute("INSERT INTO olskerCupcakes_test.user (email, password, role) " +
                    "VALUES ('user', '1234', 'customer'), ('ben', '1234', 'customer')");
            stmt.execute("INSERT INTO olskerCupcakes_test.cupcakebottom (bottom, price) " +
                    "VALUES ('chocolate', 10), ('vanilla', 10)");
            stmt.execute("INSERT INTO olskerCupcakes_test.cupcaketop (topping, price) " +
                    "VALUES ('chocolate', 10), ('vanilla', 10)");
            stmt.execute("INSERT INTO olskerCupcakes_test.order (fk_user_email, readytime, totalprice, cupcakecount, status)" +
                    "VALUES ('user', '2020-01-01 00:00:00', 20, 2, 'PENDING'), ('ben', '2020-01-01 00:00:00', 20, 2, 'PENDING'), ('user', '2020-01-01 00:00:00', 20, 2, 'PENDING')");
            stmt.execute("INSERT INTO olskerCupcakes_test.cupcake (fk_cupcaketop_id, fk_cupcakebottom_id, fk_order_id)" +
                    "VALUES (1, 1, 1), (2, 1, 2), (1, 1, 2), (1, 2, 2), (2, 1, 3), (2, 2, 3)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    public void testGetAllOrders() throws DatabaseException {
        List<Order> orders = OrderFacade.getAllOrders(connection, true);
        assertEquals(3, orders.size());
    }

    @Test
    public void testGetAllOrdersByUser_user() throws DatabaseException {
        User user = UserFacade.getUserByEmail("user", connection);
        List<Order> orders = OrderFacade.getAllOrdersByUser(user, connection, true);
        assertEquals(2, orders.size());
    }

    @Test
    public void testGetAllOrdersByUser_ben() throws DatabaseException {
        User user = UserFacade.getUserByEmail("ben", connection);
        List<Order> orders = OrderFacade.getAllOrdersByUser(user, connection, true);
        assertEquals(1, orders.size());
    }

    @Test
    public void testCreateOrder() throws DatabaseException {
        User user = UserFacade.getUserByEmail("user", connection);
        Bottom bottom = CupcakeFacade.getBottomById(1, connection);
        Top topping = CupcakeFacade.getTopById(1, connection);
        user.getShoppingCart().addCupcake(new Cupcake(bottom, topping));
        Order order = new Order(user, LocalDateTime.now());
        OrderFacade.createOrder(order, connection, true);
        List<Order> orders = OrderFacade.getAllOrders(connection, true);
        assertEquals(4, orders.size());
    }

    @Test
    public void testUpdateOrder() throws DatabaseException {
        User user = UserFacade.getUserByEmail("user", connection);
        List<Order> orders = OrderFacade.getAllOrdersByUser(user, connection, true);
        Order order = orders.get(0);
        order.setStatus(OrderStatus.READY);
        OrderFacade.updateOrderStatus(order.getId(), order.getStatus(), connection, true);
        orders = OrderFacade.getAllOrdersByUser(user, connection, true);
        assertEquals(OrderStatus.READY, orders.get(0).getStatus());
    }
}