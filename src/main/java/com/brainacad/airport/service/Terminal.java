package com.brainacad.airport.service;

import com.brainacad.airport.dao.DAOFactory;
import com.brainacad.airport.view.AuthorizationForm;

import java.sql.Connection;

/**
 * Created by User on 16.12.2016.
 */
public class Terminal {
    public static void main(String[] args) {
        initializeGUI();
        initializeDB(args);
    }

    private static void initializeGUI() {
        new AuthorizationForm().initialize();
    }

    private static void initializeDB(String[] config) {
        DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL, config);
        Connection conn = dao.getConnection();
        dao.createDataBase(conn);
        dao.initializeTables(conn);
        dao.closeConnection(conn);
    }
}
