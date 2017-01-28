package com.brainacad.airport.dao;

import com.brainacad.airport.dao.dbaccess.*;
import com.brainacad.airport.dao.dbaccess.mysql.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by User on 07.12.2016.
 */
public class MySQLDAOFactory extends DAOFactory {
//    private String dbName;
//    private String userName;
//    private String password;
//
//    private String serverName;
//    private int portNumber;
    private Properties prop;

    public MySQLDAOFactory(String[] properties) {
        if (properties.length == 0) {
            //ystem.out.println(getClass().getResource("properties/mysql.properties").toURI().toString());
            this.setProperties(ClassLoader.getSystemResourceAsStream("properties/mysql.properties"));
        } else {
            try {
                this.setProperties(new FileInputStream(properties[0]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void setProperties(InputStream fileName) {
        this.prop = new Properties();
      //  try (FileInputStream fis = new FileInputStream(fileName)) {
        try {
            prop.load(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
      /*  } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public Connection getConnection() {
        Connection conn = null;

        String currentUrlString = String.format("jdbc:mysql://%s:%s/%s", this.prop.getProperty("server_name"),
                this.prop.getProperty("port_number"), this.prop.getProperty("database_name"));
        try {
            conn = DriverManager.getConnection(currentUrlString, this.prop.getProperty("user_name"), this.prop.getProperty("password"));
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
        System.out.println("Connected to database");
        return conn;
    }

    @Override
    public void createDataBase(Connection conn) {
        try (Statement s = conn.createStatement()) {
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + this.prop.getProperty("database_name"));

            System.out.println("Created database " + this.prop.getProperty("database_name"));
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

    @Override
    public Vector<String> getTables(Connection conn) {
        Vector<String> tables = new Vector<>();
        try (Statement s = conn.createStatement()) {
            ResultSet rs = s.executeQuery("SHOW TABLES FROM " + this.prop.getProperty("database_name"));
            while (rs.next()) {
                String tableName;
                if (!(tableName = rs.getString(1)).equals("user")) {
                    tables.add(tableName);
                }
            }
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
        return tables;
    }

    @Override
    public ResultSetMetaData getColumns(Connection conn, String table) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
            return rs.getMetaData();
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
            return null;
        }
    }

    @Override
    public AirportDAO getAirportDAO(Connection conn) {
        return new MySQLAirportDAO(conn, this.prop.getProperty("database_name"));
    }

    @Override
    public FlightDAO getFlightDAO(Connection conn) {
        return new MySQLFlightDAO(conn, this.prop.getProperty("database_name"));
    }

    @Override
    public PassengerDAO getPassengerDAO(Connection conn) {
        return new MySQLPassengerDAO(conn, this.prop.getProperty("database_name"));
    }

    @Override
    public UserDAO getUserDAO(Connection conn) {
        return new MySQLUserDAO(conn, this.prop.getProperty("database_name"));
    }
}
