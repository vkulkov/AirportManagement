package com.brainacad.airport.dao.dbaccess.mysql;

import com.brainacad.airport.dao.SQLExceptionHandler;
import com.brainacad.airport.dao.dbaccess.UserDAO;
import com.brainacad.airport.dao.models.User;

import java.sql.*;

/**
 * Created by User on 11.12.2016.
 */
public class MySQLUserDAO implements UserDAO {
    private Connection conn;
    private String dbName;

    public MySQLUserDAO(Connection conn, String dbName) {
        this.conn = conn;
        this.dbName = dbName;
    }

    @Override
    public void createTable() {
        String createQuery =
                "CREATE TABLE IF NOT EXISTS `" + dbName + "`.`user` (" +
                "`user_name` VARCHAR(32) NOT NULL, " +
                "`user_password` VARCHAR(32) NOT NULL, " +
                "PRIMARY KEY (`user_name`))";
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate(createQuery);
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

    @Override
    public void populateTable() {
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate("INSERT IGNORE INTO `" + dbName + "`.`user` VALUES('admin', 'root')");
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
    }

    @Override
    public User retrieveRecord(String s) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE user_name = ?")) {
            stmt.setString(1, s);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new User(rs.getString(1), rs.getString(2));
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
            return null;
        }
    }
}
