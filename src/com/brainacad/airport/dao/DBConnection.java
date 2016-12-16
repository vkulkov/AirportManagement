package com.brainacad.airport.dao;

import com.brainacad.airport.dao.dbaccess.FlightDAO;
import com.brainacad.airport.dao.dbaccess.PassengerDAO;
import com.brainacad.airport.dao.dbaccess.UserDAO;
import com.brainacad.airport.dao.dbaccess.mysql.MySQLFlightDAO;
import com.brainacad.airport.dao.dbaccess.mysql.MySQLPassengerDAO;
import com.brainacad.airport.dao.dbaccess.mysql.MySQLUserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by User on 28.11.2016.
 */
public class DBConnection {

    public String dbms;
    public String jarFile;
    public String dbName;
    public String userName;
    public String password;
    public String urlString;

    private String driver;
    private String serverName;
    private int portNumber;
    private Properties prop;

    public DBConnection(String properties) {
        this.setProperties(properties);
    }

    private void setProperties(String fileName) {}

    public void printSQLException(SQLException e) {
        if (e != null) {
            e.printStackTrace(System.err);
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            Throwable t = e.getCause();
            while (t != null) {
                System.out.println("Cause: " + t);
                t = t.getCause();
            }
            printSQLException(e.getNextException());
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        Properties connectionProps = new Properties();
        return conn;
    }

    public static void createDataBase(Connection conn, String dbName) {
        try (Statement s = conn.createStatement()) {
            String newDatabase = "CREATE DATABASE IF NOT EXISTS " + dbName;
            s.executeUpdate(newDatabase);

            System.out.println("Created database " + dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initializeTables(Connection conn, String dbName) {
        FlightDAO myFlightTable = new MySQLFlightDAO(conn, dbName);
        PassengerDAO myPassengerTable = new MySQLPassengerDAO(conn, dbName);
        UserDAO myUserTable = new MySQLUserDAO(conn, dbName);
    }

    public void closeConnection(Connection conn) {
        System.out.println("Releasing all open resources ...");
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            this.printSQLException(e);
        }
    }

    public static void main(String[] args) {
        DBConnection jdbc;
        Connection myConnection = null;
        if (args[0] == null) {
            System.err.println("Properties file not specified at command line");
            return;
        } else {
            try {
                System.out.println("Reading properties file " + args[0]);
                jdbc = new DBConnection(args[0]);
            } catch (Exception e) {
                System.err.println("Problem reading properties file " + args[0]);
                e.printStackTrace();
                return;
            }
        }

        try {
            myConnection = jdbc.getConnection();
            createDataBase(myConnection, jdbc.dbName);
            initializeTables(myConnection, jdbc.dbName);
        } finally {
            jdbc.closeConnection(myConnection);
        }
    }
}
