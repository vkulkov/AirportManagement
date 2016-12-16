package com.brainacad.airport.dao.dbaccess.mysql;

import com.brainacad.airport.dao.SQLExceptionHandler;
import com.brainacad.airport.dao.dbaccess.PassengerDAO;
import com.brainacad.airport.dao.models.Passenger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by User on 07.12.2016.
 */
public class MySQLPassengerDAO implements PassengerDAO {
    private Connection conn;
    private String dbName;

    public MySQLPassengerDAO(Connection conn, String dbName) {
        this.conn = conn;
        this.dbName = dbName;
    }

    @Override
    public void createTable() {
        String createQuery =
                "CREATE TABLE IF NOT EXISTS `" + dbName + "`.`passenger` (" +
                "`passenger_id` INTEGER NOT NULL, " +
                "`first_name` VARCHAR(32) NOT NULL, " +
                "`last_name` VARCHAR(32) NOT NULL, " +
                "`date_of_birthday` DATE NOT NULL, " +
                "`sex` VARCHAR(1) NOT NULL, " +
                "`flight_number` VARCHAR(32) NOT NULL," +
                "PRIMARY KEY (`passenger_id`), " +
                "FOREIGN KEY (`flight_number`) REFERENCES `flight` (`flight_id`))";
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate(createQuery);
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

    @Override
    public void populateTable() {
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`passenger` VALUES(532721, 'Christopher', 'Harington', '1986-12-26', 'M', '1536-ODESSA-KIEV')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`passenger` VALUES(236232, 'Ivan', 'Rheon', '1985-05-13', 'M', '4212-NYC-LA')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`passenger` VALUES(554329, 'Aidan', 'Gillen', '1968-04-24', 'M', '2461-MOSCOW-PARIS')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`passenger` VALUES(953326, 'Sophie', 'Turner', '1996-02-21', 'F', '1934-LA-SHANGHAI')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`passenger` VALUES(265747, 'Liam', 'Cunningham', '1961-06-02', 'M', '1427-ODESSA-DUBAI')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`passenger` VALUES(316789, 'Emilia', 'Clarke', '1986-10-23', 'F', '1536-ODESSA-KIEV')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`passenger` VALUES(179432, 'Lena', 'Headey', '1973-10-03', 'F', '4212-NYC-LA')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`passenger` VALUES(467328, 'Isaac', 'Wright', '1999-04-09', 'M', '2461-MOSCOW-PARIS')");
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

    @Override
    public void createRecord(List<Passenger> records) {

    }

    @Override
    public List<Passenger> retrieveRecord() {
        return null;
    }

    @Override
    public List<Passenger> retrieveRecord(String column, String value) {
        return null;
    }

    @Override
    public void changeRecord(List<Passenger> records) {

    }

    @Override
    public void removeRecord(List<Passenger> records) {

    }
}
