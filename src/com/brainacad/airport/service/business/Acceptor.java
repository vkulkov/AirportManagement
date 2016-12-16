package com.brainacad.airport.service.business;

import com.brainacad.airport.dao.DAOFactory;
import com.brainacad.airport.dao.SQLExceptionHandler;
import com.brainacad.airport.dao.models.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 16.12.2016.
 */
public class Acceptor {

    public User getData(String userName) {
        DAOFactory dao = DAOFactory.getDAOFactory();
        try (Connection conn = dao.getConnection()) {
            return dao.getUserDAO(conn).retrieveRecord(userName);
        } catch (SQLException e) {
            SQLExceptionHandler.printSQLException(e);
        }
        return null;
    }
}
