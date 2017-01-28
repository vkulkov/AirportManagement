package com.brainacad.airport.dao.dbaccess.mysql;

import com.brainacad.airport.dao.DAOFactory;
import com.brainacad.airport.dao.MySQLDAOFactory;
import com.brainacad.airport.dao.SQLExceptionHandler;
import com.brainacad.airport.dao.dbaccess.AirportDAO;
import com.brainacad.airport.dao.dbaccess.mysql.queries.AirportTable;
import com.brainacad.airport.dao.models.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
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
    public List<Airport> retrieveAllRecords() {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(AirportTable.SELECT);
            List<Airport> airports = new ArrayList<>();
            while (rs.next()) {
                Airport airport = new Airport();
                airport.setIataCode(rs.getString(1));
                airport.setIcaoCode(rs.getString(2));
                airport.setCityName(rs.getString(3));

                airports.add(airport);
            }
            return airports;
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
            return null;
        }
    }

    @Override
    public List<Airport> retrieveRecord(String column, String value) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM airport WHERE " + column + " LIKE ?")) {
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();
            List<Airport> airports = new ArrayList<>();
            while (rs.next()) {
                Airport airport = new Airport();
                airport.setIataCode(rs.getString(1));
                airport.setIcaoCode(rs.getString(2));
                airport.setCityName(rs.getString(3));

                airports.add(airport);
            }
            return airports;
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
            return null;
        }
    }

    @Override
    public void changeRecord(List<Airport> records) {

    }

    @Override
    public void removeRecord(List<Airport> records) {
        try (PreparedStatement stmt = conn.prepareStatement(AirportTable.DELETE)) {
            Iterator<Airport> iterator = records.iterator();
            while (iterator.hasNext()) {
                Airport airport = iterator.next();
                stmt.setString(1, airport.getIataCode());
                stmt.executeQuery();
            }
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

    @Override
    public ResultSetMetaData getMeta() {
        try (PreparedStatement stmt = conn.prepareStatement(AirportTable.SELECT_BY)) {
            stmt.setBoolean(1, true);
            stmt.setBoolean(2, false);
            ResultSet rs = stmt.executeQuery();
            return rs.getMetaData();
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
            return null;
        }
    }
}
