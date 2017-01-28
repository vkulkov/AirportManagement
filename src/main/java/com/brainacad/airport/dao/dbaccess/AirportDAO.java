package com.brainacad.airport.dao.dbaccess;

import com.brainacad.airport.dao.models.Airport;

import java.sql.ResultSetMetaData;
import java.util.List;

/**
 * Created by User on 14.12.2016.
 */
public interface AirportDAO {
    void createTable();

    void populateTable();

    void createRecord(List<Airport> records);

    List<Airport> retrieveAllRecords();

    List<Airport> retrieveRecord(String column, String value);

    void changeRecord(List<Airport> records);

    void removeRecord(List<Airport> records);

    ResultSetMetaData getMeta();
}
