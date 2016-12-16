package com.brainacad.airport.dao.dbaccess.mysql;

import com.brainacad.airport.dao.SQLExceptionHandler;
import com.brainacad.airport.dao.dbaccess.AirportDAO;
import com.brainacad.airport.dao.models.Airport;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by User on 14.12.2016.
 */
public class MySQLAirportDAO implements AirportDAO {
    private Connection conn;
    private String dbName;

    public MySQLAirportDAO(Connection conn, String dbName) {
        this.conn = conn;
        this.dbName = dbName;
    }

    @Override
    public void createTable() {
        String createQuery =
                "CREATE TABLE IF NOT EXISTS `" + dbName + "`.`airport` (" +
                "`airport_iata` VARCHAR(3) NOT NULL, " +
                "`airport_icao` VARCHAR(4) NOT NULL, " +
                "`city` VARCHAR(32) NOT NULL, " +
                "PRIMARY KEY (`airport_iata`), " +
                "UNIQUE (`airport_icao`))";
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate(createQuery);
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

    @Override
    public void populateTable() {
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`airport` VALUES('ODS', 'UKOO', 'Odessa')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`airport` VALUES('IEV', 'UKKK', 'Kiev')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`airport` VALUES('SVO', 'UUEE', 'Moscow')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`airport` VALUES('JFK', 'KJFK', 'NYC')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`airport` VALUES('LAX', 'KLAX', 'LA')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`airport` VALUES('CDG', 'LFPG', 'Paris')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`airport` VALUES('DXB', 'OMDB', 'Dubai')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`airport` VALUES('PVG', 'ZSPD', 'Shanghai')");
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

    @Override
    public void createRecord(List<Airport> records) {

    }

    @Override
    public List<Airport> retrieveRecord() {
        return null;
    }

    @Override
    public List<Airport> retrieveRecord(String column, String value) {
        return null;
    }

    @Override
    public void changeRecord(List<Airport> records) {

    }

    @Override
    public void removeRecord(List<Airport> records) {

    }
}
