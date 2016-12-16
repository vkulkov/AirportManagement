package com.brainacad.airport.dao.dbaccess;

import com.brainacad.airport.dao.models.Flight;

import java.util.List;

/**
 * Created by User on 07.12.2016.
 */
public interface FlightDAO {
    void createTable();

    void populateTable();

    void createRecord(List<Flight> records);

    List<Flight> retrieveRecord();

    List<Flight> retrieveRecord(String column, String value);

    void changeRecord(List<Flight> records);

    void removeRecord(List<Flight> records);
}
