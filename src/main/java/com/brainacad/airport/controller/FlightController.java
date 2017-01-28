package com.brainacad.airport.controller;

import com.brainacad.airport.dao.DAOFactory;
import com.brainacad.airport.dao.dbaccess.FlightDAO;
import com.brainacad.airport.dao.models.Flight;

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
public class FlightController {
    private boolean isGuest;

    public FlightController(boolean isGuest) {
        this.isGuest = isGuest;
    }

    public TableModel createTableModel() {
        DAOFactory dao = DAOFactory.getDAOFactory();
        FlightDAO flightDAO = dao.getFlightDAO(dao.getConnection());

        ResultSetMetaData metaData = flightDAO.getColumns();
        List<Flight> flights = flightDAO.retrieveAllRecords();
        Iterator<Flight> iterator = flights.iterator();

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
            Flight flight = iterator.next();
            vector.add(flight.getFlightID());
            vector.add(flight.getFromAirport());
            vector.add(flight.getToAirport());
            vector.add(flight.getDepartureTime());
            vector.add(flight.getArrivalTime());
            vector.add(flight.getGate());
            vector.add(flight.getFreeSeats());
            vector.add(flight.getPrice());
            vector.add(flight.getFlightStatus());
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
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
        FlightDAO flightDAO = dao.getFlightDAO(dao.getConnection());

        ResultSetMetaData metaData = flightDAO.getColumns();
        List<Flight> flights = flightDAO.retrieveRecord(columnName, "%" + value + "%");
        Iterator<Flight> iterator = flights.iterator();

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
            Flight flight = iterator.next();
            vector.add(flight.getFlightID());
            vector.add(flight.getFromAirport());
            vector.add(flight.getToAirport());
            vector.add(flight.getDepartureTime());
            vector.add(flight.getArrivalTime());
            vector.add(flight.getGate());
            vector.add(flight.getFreeSeats());
            vector.add(flight.getPrice());
            vector.add(flight.getFlightStatus());
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
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
        FlightDAO flightDAO = dao.getFlightDAO(dao.getConnection());

        List<Flight> flights = flightDAO.retrieveRecord(columnName, value);
        flightDAO.removeRecord(flights);
    }
}
