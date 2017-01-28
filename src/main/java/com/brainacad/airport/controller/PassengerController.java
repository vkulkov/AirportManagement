package com.brainacad.airport.controller;

import com.brainacad.airport.dao.DAOFactory;
import com.brainacad.airport.dao.dbaccess.PassengerDAO;
import com.brainacad.airport.dao.models.Passenger;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by User on 17.01.2017.
 */
public class PassengerController {

    private boolean isGuest;

    public PassengerController(boolean isGuest) {
        this.isGuest = isGuest;
    }

    public TableModel createTableModel() {
        if (isGuest) {
            return new DefaultTableModel();
        }
        DAOFactory dao = DAOFactory.getDAOFactory();
        PassengerDAO passengerDAO = dao.getPassengerDAO(dao.getConnection());

        ResultSetMetaData metaData = passengerDAO.getMeta();
        List<Passenger> passengers = passengerDAO.retrieveAllRecords();
        Iterator<Passenger> iterator = passengers.iterator();

        Vector<String> columnNames = new Vector<String>();
        try {
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (iterator.hasNext()) {
            Vector<Object> vector = new Vector<Object>();
            Passenger passenger = iterator.next();
            vector.add(passenger.getPassengerID());
            vector.add(passenger.getFirstName());
            vector.add(passenger.getLastName());
            vector.add(passenger.getBirthday());
            vector.add(passenger.getSex());
            vector.add(passenger.getFlight());
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    case 3:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public TableModel searchInDB(String columnName, String value) {
        DAOFactory dao = DAOFactory.getDAOFactory();
        PassengerDAO passengerDAO = dao.getPassengerDAO(dao.getConnection());

        ResultSetMetaData metaData = passengerDAO.getMeta();
        List<Passenger> passengers = passengerDAO.retrieveRecord(columnName, "%" + value + "%");
        Iterator<Passenger> iterator = passengers.iterator();

        Vector<String> columnNames = new Vector<String>();
        try {
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (iterator.hasNext()) {
            Vector<Object> vector = new Vector<Object>();
            Passenger passenger = iterator.next();
            vector.add(passenger.getPassengerID());
            vector.add(passenger.getFirstName());
            vector.add(passenger.getLastName());
            vector.add(passenger.getBirthday());
            vector.add(passenger.getSex());
            vector.add(passenger.getFlight());
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    case 3:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public void deleteRecord(String columnName, String value) {
        DAOFactory dao = DAOFactory.getDAOFactory();
        PassengerDAO passengerDAO = dao.getPassengerDAO(dao.getConnection());

        List<Passenger> passengers = passengerDAO.retrieveRecord(columnName, value);
        passengerDAO.removeRecord(passengers);
    }
}
