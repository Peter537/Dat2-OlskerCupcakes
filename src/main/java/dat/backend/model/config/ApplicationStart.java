package dat.backend.model.config;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupcakeFacade;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    This class handles the birth and death of the connection pool.
    contextInitialized() initializes the connection pool at application start
    Then the connection pool can be obtained by ApplicationStart.getConnectionPool()
 */
@WebListener
public class ApplicationStart implements ServletContextListener {

    private static ConnectionPool connectionPool;

    public ApplicationStart() { }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger.getLogger("web").log(Level.INFO, "Starting up application and connection pool");
        try {
            Class.forName("org.slf4j.impl.StaticLoggerBinder");
            connectionPool = new ConnectionPool(getPassword(sce));
            sce.getServletContext().setAttribute("toppings", CupcakeFacade.getAllToppings(connectionPool.getConnection()));
            sce.getServletContext().setAttribute("bottoms", CupcakeFacade.getAllBottoms(connectionPool.getConnection()));
        } catch (SQLException | DatabaseException | ClassNotFoundException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Logger.getLogger("web").log(Level.INFO, "Shutting down application and connection pool");
        unregisterJDBCdrivers();
        connectionPool.close();
    }

    private void unregisterJDBCdrivers() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // Loop through all drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                // This driver was registered by the webapp's ClassLoader, so deregister it:
                try {
                    Logger.getLogger("web").log(Level.INFO, "Deregistering JDBC driver");
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    Logger.getLogger("web").log(Level.SEVERE, "Error deregistering JDBC driver");
                }
            } else {
                // driver was not registered by the webapp's ClassLoader and may be in use elsewhere
                Logger.getLogger("web").log(Level.WARNING, "Error deregistering JDBC driver");
            }
        }
    }

    /**
     * Get password from dbpass file
     */
    private String getPassword(ServletContextEvent sce) {
        String pass = "";
        try {
            String path = sce.getServletContext().getRealPath("/") + "WEB-INF\\classes\\dbpass.csv";
            File file = new File(path);
            Scanner s = new Scanner(file);
            pass = s.nextLine();
        } catch (FileNotFoundException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage(), e);
        }

        return pass;
    }
}