package dat.backend.persistence;

import dat.backend.model.persistence.ConnectionPool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.fail;

class CupcakeMapperTest {

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
            stmt.execute("DELETE FROM cupcakebottom");
            stmt.execute("DELETE FROM cupcaketop");

            // TODO: Insert a few users - insert rows into your own tables here
            stmt.execute("INSERT INTO cupcakebottom (bottom, price) " +
                    "VALUES ('chokolade', 5), ('vanilla', 7)");
            stmt.execute("INSERT INTO cupcaketop (topping, price) " +
                    "VALUES ('chokolade', 5), ('vanilla', 7)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("Database connection failed");
        }
    }
}