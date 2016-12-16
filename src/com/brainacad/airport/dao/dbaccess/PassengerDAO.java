package com.brainacad.airport.dao.dbaccess;

import com.brainacad.airport.dao.models.Passenger;

import java.util.List;

/**
 * Created by User on 07.12.2016.
 */
public interface PassengerDAO {
    void createTable();

    void populateTable();

    void createRecord(List<Passenger> records);

    List<Passenger> retrieveRecord();

    List<Passenger> retrieveRecord(String column, String value);

    void changeRecord(List<Passenger> records);

    void removeRecord(List<Passenger> records);
}
