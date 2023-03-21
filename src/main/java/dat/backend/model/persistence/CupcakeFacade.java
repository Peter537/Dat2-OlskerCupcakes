package dat.backend.model.persistence;

import dat.backend.model.entities.Bottom;
import dat.backend.model.entities.Top;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.util.List;

public class CupcakeFacade {

    public static Top getTopById(int id, Connection connection) throws DatabaseException {
        return CupcakeMapper.getTopById(id, connection);
    }

    public static Bottom getBottomById(int id, Connection connection) throws DatabaseException {
        return CupcakeMapper.getBottomById(id, connection);
    }

    public static Top getTopByName(String name, Connection connection) throws DatabaseException {
        return CupcakeMapper.getTopByName(name, connection);
    }

    public static Bottom getBottomByName(String name, Connection connection) throws DatabaseException {
        return CupcakeMapper.getBottomByName(name, connection);
    }

    public static List<Top> getAllToppings(Connection connection) throws DatabaseException {
        return CupcakeMapper.getAllToppings(connection);
    }

    public static List<Bottom> getAllBottoms(Connection connection) throws DatabaseException {
        return CupcakeMapper.getAllBottoms(connection);
    }
}