package com.brainacad.airport.service;

import com.brainacad.airport.dao.DAOFactory;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by User on 16.12.2016.
 */
public class MetaSelector {

    public Vector<String> getTables() {
        DAOFactory dao = DAOFactory.getDAOFactory();

        return dao.getTables(dao.getConnection());
    }

    public Vector<String> getColumns(String table) {

        DAOFactory dao = DAOFactory.getDAOFactory();

        table.substring(1, table.length() - 1);
        ResultSetMetaData metaData = dao.getColumns(dao.getConnection(), table);
        Vector<String> columnNames = new Vector<String>();
        try {
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnNames;
    }
}
