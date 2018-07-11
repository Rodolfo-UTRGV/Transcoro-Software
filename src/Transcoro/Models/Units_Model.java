package Transcoro.Models;

import Transcoro.Classes.Unit;
import Transcoro.Database.Database;
import Transcoro.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Units_Model {
    static Database instance = Environment.getDatabase();

    static Connection connection = instance.getMyConnection();

    public static boolean addUnit(Unit unit) {
        if(unit == null){
            System.out.println("An error has occurred: Unit is invalid.");
            return false;
        }

        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO `units` (`unitId`, `plates`, `vin`, `brand`, `model`, `datePurchase`) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, unit.getId());
            statement.setString(2, unit.getPlates());
            statement.setString(3, unit.getVIN());
            statement.setString(4, unit.getBrand());
            statement.setInt(5, unit.getModel());
            statement.setDate(6, unit.getPurchaseDate());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("An error has occurred: Could not add unit - " + e);
        }

        return false;
    }
}
