package com.brainacad.airport.controller;

import com.brainacad.airport.dao.DAOFactory;
import com.brainacad.airport.dao.dbaccess.AirportDAO;
import com.brainacad.airport.dao.models.Airport;
import com.brainacad.airport.dao.models.Flight;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by User on 17.01.2017.
 */
public class AirportController {

    private boolean isGuest;

    public AirportController(boolean isGuest) {
        this.isGuest = isGuest;
    }

    public TableModel createTableModel() {
        if (isGuest) {
            return new DefaultTableModel();
        }
        DAOFactory dao = DAOFactory.getDAOFactory();
        AirportDAO airportDAO = dao.getAirportDAO(dao.getConnection());

        ResultSetMetaData metaData = airportDAO.getMeta();
        List<Airport> airports = airportDAO.retrieveAllRecords();
        Iterator<Airport> iterator = airports.iterator();

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
            Airport airport = iterator.next();
            vector.add(airport.getIataCode());
            vector.add(airport.getIcaoCode());
            vector.add(airport.getCityName());
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public TableModel searchInDB(String columnName, String value) {
        DAOFactory dao = DAOFactory.getDAOFactory();
        AirportDAO airportDAO = dao.getAirportDAO(dao.getConnection());

        ResultSetMetaData metaData = airportDAO.getMeta();
        List<Airport> airports = airportDAO.retrieveRecord(columnName, "%" + value + "%");
        Iterator<Airport> iterator = airports.iterator();

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
            Airport airport = iterator.next();
            vector.add(airport.getIataCode());
            vector.add(airport.getIcaoCode());
            vector.add(airport.getCityName());
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public void deleteRecord(String columnName, String value) {
        DAOFactory dao = DAOFactory.getDAOFactory();
        AirportDAO airportDAO = dao.getAirportDAO(dao.getConnection());

        List<Airport> airports = airportDAO.retrieveRecord(columnName, value);
        airportDAO.removeRecord(airports);
    }

    public void addRecord(String iata, String icao, String city) {
        DAOFactory dao = DAOFactory.getDAOFactory();
        AirportDAO airportDAO = dao.getAirportDAO(dao.getConnection());

        Airport airport = new Airport();
        airport.setIataCode(iata);
        airport.setIcaoCode(icao);
        airport.setCityName(city);

        List<Airport> a = new ArrayList<>();
        a.add(airport);
        airportDAO.createRecord(a);
    }
}
