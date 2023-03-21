package dat.backend.model.persistence;

import dat.backend.model.entities.Bottom;
import dat.backend.model.entities.Top;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CupcakeMapper {

    static Top getTopById(int id, Connection connection) throws DatabaseException {
        String sqlStatement = "SELECT * FROM cupcaketop WHERE cupcaketop_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int cupcaketop_id = rs.getInt("cupcaketop_id");
                String cupcakeTopping = rs.getString("topping");
                float cupcaketop_price = rs.getFloat("price");
                return new Top(cupcaketop_id, cupcakeTopping, cupcaketop_price);
            } else {
                throw new DatabaseException("Could not get top by id from database");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get top by id from database");
        }
    }

    static Bottom getBottomById(int id, Connection connection) throws DatabaseException {
        String sqlStatement = "SELECT * FROM cupcakebottom WHERE cupcakebottom_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int cupcakebottom_id = rs.getInt("cupcakebottom_id");
                String cupcakeBottom = rs.getString("bottom");
                float cupcakebottom_price = rs.getFloat("price");
                return new Bottom(cupcakebottom_id, cupcakeBottom, cupcakebottom_price);
            } else {
                throw new DatabaseException("Could not get bottom by id from database");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get bottom by id from database");
        }
    }

    static Top getTopByName(String name, Connection connection) throws DatabaseException {
        String sqlStatement = "SELECT * FROM cupcaketop WHERE topping = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int cupcaketop_id = rs.getInt("cupcaketop_id");
                String cupcakeTopping = rs.getString("topping");
                float cupcaketop_price = rs.getFloat("price");
                return new Top(cupcaketop_id, cupcakeTopping, cupcaketop_price);
            } else {
                throw new DatabaseException("Could not get top by name from database");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get top by name from database");
        }
    }

    static Bottom getBottomByName(String name, Connection connection) throws DatabaseException {
        String sqlStatement = "SELECT * FROM cupcakebottom WHERE bottom = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int cupcakebottom_id = rs.getInt("cupcakebottom_id");
                String cupcakeBottom = rs.getString("bottom");
                float cupcakebottom_price = rs.getFloat("price");
                return new Bottom(cupcakebottom_id, cupcakeBottom, cupcakebottom_price);
            } else {
                throw new DatabaseException("Could not get bottom by name from database");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get bottom by name from database");
        }
    }

    static List<Top> getAllToppings(Connection connection) throws DatabaseException {
        ArrayList<Top> toppings = new ArrayList<>();
        String sqlStatement = "SELECT * FROM cupcaketop";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cupcaketop_id = rs.getInt("cupcaketop_id");
                String cupcaketopping = rs.getString("topping");
                float cupcaketop_price = rs.getFloat("price");
                toppings.add(new Top(cupcaketop_id, cupcaketopping, cupcaketop_price));
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get all toppings from database");
        }

        return toppings;
    }

    static List<Bottom> getAllBottoms(Connection connection) throws DatabaseException {
        ArrayList<Bottom> bottoms = new ArrayList<>();
        String sqlStatement = "SELECT * FROM cupcakebottom";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cupcakebottom_id = rs.getInt("cupcakebottom_id");
                String cupcakeBottom = rs.getString("bottom");
                float cupcakebottom_price = rs.getFloat("price");
                bottoms.add(new Bottom(cupcakebottom_id, cupcakeBottom, cupcakebottom_price));
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Could not get all bottoms from database");
        }
        return bottoms;
    }
}