package com.brainacad.airport.controller;

import com.brainacad.airport.dao.DAOFactory;
import com.brainacad.airport.dao.SQLExceptionHandler;
import com.brainacad.airport.dao.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by User on 17.01.2017.
 */
public class UserController {

    public boolean confirmData(String userName, char[] password) {
        DAOFactory dao = DAOFactory.getDAOFactory();
        try (Connection conn = dao.getConnection()) {
            User user = dao.getUserDAO(conn).retrieveRecord(userName);
            if (user != null) {
                return Arrays.equals(user.getPassword().toCharArray(), password);
            }
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
        return false;
    }
}
