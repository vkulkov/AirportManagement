package com.brainacad.airport.dao.dbaccess.mysql;

import com.brainacad.airport.dao.SQLExceptionHandler;
import com.brainacad.airport.dao.dbaccess.FlightDAO;
import com.brainacad.airport.dao.models.Flight;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by User on 07.12.2016.
 */
public class MySQLFlightDAO implements FlightDAO {
    private Connection conn;
    private String dbName;

    public MySQLFlightDAO(Connection conn, String dbName) {
        this.conn = conn;
        this.dbName = dbName;
    }

    @Override
    public void createTable() {
        String createQuery =
                "CREATE TABLE IF NOT EXISTS `" + dbName + "`.`flight` (" +
                "`flight_id` VARCHAR(32) NOT NULL, " +
                "`from_airport` VARCHAR(3) NOT NULL, " +
                "`to_airport` VARCHAR(3) NOT NULL, " +
                "`departure` DATETIME NOT NULL, " +
                "`arrival` DATETIME NOT NULL, " +
                "`gate` INTEGER NOT NULL, " +
                "`number_of_seats` INTEGER NOT NULL, " +
                "`flight_price` NUMERIC(10,2) NOT NULL, " +
                "`flight_status` VARCHAR(16) NOT NULL, " +
                "PRIMARY KEY (`flight_id`), " +
                "FOREIGN KEY (`from_airport`) REFERENCES `airport` (`airport_iata`), " +
                "FOREIGN KEY (`to_airport`) REFERENCES `airport` (`airport_iata`))";
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate(createQuery);
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

    @Override
    public void populateTable() {
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`flight` VALUES('1536-ODESSA-KIEV', 'ODS', 'IEV', '2016-12-16 18:30:00', " +
                    "'2016-12-16 19:12:00', 1, 120, 119.99, 'IN FLIGHT')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`flight` VALUES('4212-NYC-LA', 'JFK', 'LAX', '2016-12-16 9:50:00', " +
                    "'2016-12-16 14:45:00', 3, 200, 239.99, 'ARRIVED')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`flight` VALUES('2461-MOSCOW-PARIS', 'SVO', 'CDG', '2016-12-16 23:40:00', " +
                    "'2016-12-17 03:24:00', 2, 140, 199.99, 'AT GATE')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`flight` VALUES('1934-LA-SHANGHAI', 'LAX', 'PVG', '2016-12-16 16:20:00', " +
                    "'2016-12-16 22:40:00', 6, 200, 254.99, 'IN FLIGHT')");
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`flight` VALUES('1427-ODESSA-DUBAI', 'ODS', 'DXB', '2016-12-17 13:50:00', " +
                    "'2016-12-17 16:10:00', 1, 120, 169.99, 'CHECK IN')");
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

    @Override
    public void createRecord(List<Flight> records) {

    }

    @Override
    public List<Flight> retrieveRecord() {
        return null;
    }

    @Override
    public List<Flight> retrieveRecord(String column, String value) {
        return null;
    }

    @Override
    public void changeRecord(List<Flight> records) {

    }

    @Override
    public void removeRecord(List<Flight> records) {

    }
}
