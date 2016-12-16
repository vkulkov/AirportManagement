package com.brainacad.airport.service.business;

import com.brainacad.airport.dao.DAOFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by User on 16.12.2016.
 */
public class MetaSelector {

    public Vector<String> getTables() {
        DAOFactory dao = DAOFactory.getDAOFactory();
        Vector<String> tables = dao.showTables(dao.getConnection());

        return tables;
    }
}
