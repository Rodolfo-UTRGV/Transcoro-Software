/**
 *   _____
 * () | ,_   _,         ,   _   _   ,_   _
 *    |/  | / |  /|/|  / \_/   / \_/  | / \_
 *  (/    |/\/|_/ | |_/ \/ \__/\_/    |/\_/
 *
 *  Transcoro Software 2018 || The power of proyects
 **/
package Transcoro.Models;

import Transcoro.Classes.units.Unit;
import Transcoro.Database.Database;
import Transcoro.Environment;

import java.sql.*;
import java.util.ArrayList;

public class Units_Model {
    static Database instance = Environment.getDatabase();

    static Connection connection = instance.getMyConnection();

    /**
     * @description Adds units to the database.
     * @param unit
     * @return true if units was successfully added to the database. false if an error has occurred.
     */
    public static boolean addUnit(Unit unit) {
        if(unit == null){
            System.out.println("An error has occurred: units is invalid.");
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
            System.err.println("An error has occurred: Could not add units - " + e);
        }

        return false;
    }

    public boolean deleteUnit(int unitID){

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM `units` WHERE unitId = ?"))
        {
            statement.setInt(1, unitID);

            return statement.executeUpdate() >= 1;

        } catch (SQLException e) {
            System.err.println("An error has occurred: Could not delete unit " + unitID + " - " + e);
        }

        return false;
    }

    public static ArrayList<Unit> retrieveUnits(){
        ArrayList<Unit> _units = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `units`")) {
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    int unitId = set.getInt("unitId");
                    String unitPlates = set.getString("plates");
                    String unitVIN = set.getString("vin");
                    String unitBrand = set.getString("brand");
                    int unitModel = set.getInt("model");
                    Date unitPurchaseDate = set.getDate("datePurchase");


                    _units.add(new Unit(unitId, unitBrand, unitModel, unitPlates, unitPurchaseDate, unitVIN));
                }
            }
        } catch (SQLException e){
            System.err.println("An error has occurred: Couldn't retrieve all units. - " + e);
        }

        return _units;
    }

    /**
     * @description Counts how many units are stored in the database.
     * @return Total Count of units in the database.
     */
    public static int totalUnits() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM `units`"))
        {
            try (ResultSet set = statement.executeQuery())
            {
                if (set.next())
                    return set.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("An error has occurred: Couldn't retrieve total units count. - " + e);
        }

        return -1;
    }

    /**
     * @description Counts how many units on service are stored in the database.
     * @return Total Count of units on service in the database.
     */
    public static int onServiceUnits() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM `units` WHERE onService = '1'"))
        {
            try (ResultSet set = statement.executeQuery())
            {
                if (set.next())
                    return set.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("An error has occurred: Couldn't retrieve on service units count. - " + e);
        }

        return -1;
    }

    /**
     * @description Counts how many units on track are stored in the database.
     * @return Total Count of units on track in the database.
     */
    public static int onTrackUnits() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM `units` WHERE onTrack = '1'"))
        {
            try (ResultSet set = statement.executeQuery())
            {
                if (set.next())
                    return set.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("An error has occurred: Couldn't retrieve on track units count. - " + e);
        }

        return -1;
    }
}
