package dat.backend.persistence;

import dat.backend.model.entities.Bottom;
import dat.backend.model.entities.Top;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupcakeFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

            // Create user table. Add your own tables here
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
            // Remove all rows from all tables - add your own tables here
            stmt.execute("TRUNCATE TABLE cupcakebottom");
            stmt.execute("TRUNCATE TABLE cupcaketop");

            // Insert a few users - insert rows into your own tables here
            stmt.execute("INSERT INTO cupcakebottom (bottom, price) " +
                    "VALUES ('chokolade', 5), ('vanilla', 7)");
            stmt.execute("INSERT INTO cupcaketop (topping, price) " +
                    "VALUES ('chokolade', 5), ('vanilla', 7)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    public void testGetAllBottoms() throws DatabaseException {
        List<Bottom> bottoms = CupcakeFacade.getAllBottoms(connection);
        assertEquals(2, bottoms.size());
    }

    @Test
    public void testGetAllToppings() throws DatabaseException {
        List<Top> tops = CupcakeFacade.getAllToppings(connection);
        assertEquals(2, tops.size());
    }

    @Test
    public void testGetBottomById_1() throws DatabaseException {
        Bottom bottom = CupcakeFacade.getBottomById(1, connection);
        assertEquals("chokolade", bottom.getName());
        assertEquals(5, bottom.getPrice());
    }

    @Test
    public void testGetBottomById_2() throws DatabaseException {
        Bottom bottom = CupcakeFacade.getBottomById(2, connection);
        assertEquals("vanilla", bottom.getName());
        assertEquals(7, bottom.getPrice());
    }

    @Test
    public void testGetTopById_1() throws DatabaseException {
        Top top = CupcakeFacade.getTopById(1, connection);
        assertEquals("chokolade", top.getName());
        assertEquals(5, top.getPrice());
    }

    @Test
    public void testGetTopById_2() throws DatabaseException {
        Top top = CupcakeFacade.getTopById(2, connection);
        assertEquals("vanilla", top.getName());
        assertEquals(7, top.getPrice());
    }
}