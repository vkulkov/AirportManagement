package com.brainacad.airport.dao;

import com.brainacad.airport.dao.dbaccess.*;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * Created by User on 07.12.2016.
 */
public abstract class DAOFactory {
    public static final int MYSQL = 1;

    private static DAOFactory dao;

    public abstract void createDataBase(Connection conn);
    public abstract Vector<String> getTables(Connection conn);
    public abstract ResultSetMetaData getColumns(Connection conn, String table);
    public abstract Connection getConnection();


    public abstract AirportDAO getAirportDAO(Connection conn);
    public abstract FlightDAO getFlightDAO(Connection conn);
    public abstract PassengerDAO getPassengerDAO(Connection conn);
    public abstract UserDAO getUserDAO(Connection conn);

    public static DAOFactory getDAOFactory(int whichFactory, String[] prop) {
        switch (whichFactory) {
            case MYSQL:
                dao = new MySQLDAOFactory(prop);
                break;
        }
        return dao;
    }

    public static DAOFactory getDAOFactory() {
        return dao;
    }

    public void initializeTables(Connection conn) {
        AirportDAO myAirportTable = this.getAirportDAO(conn);
        FlightDAO myFlightTable = this.getFlightDAO(conn);
        PassengerDAO myPassengerTable = this.getPassengerDAO(conn);
        UserDAO myUserTable = this.getUserDAO(conn);

        myAirportTable.createTable();
        myAirportTable.populateTable();

        myFlightTable.createTable();
        myFlightTable.populateTable();

        myPassengerTable.createTable();
        myPassengerTable.populateTable();

        myUserTable.createTable();
        myUserTable.populateTable();
    }

    public void closeConnection(Connection conn) {
        System.out.println("Releasing resources ...");
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

}
