package com.brainacad.airport.dao.dbaccess;

import com.brainacad.airport.dao.models.User;

/**
 * Created by User on 11.12.2016.
 */
public interface UserDAO {
    void createTable();

    void populateTable();

    User retrieveRecord(String s);
}
