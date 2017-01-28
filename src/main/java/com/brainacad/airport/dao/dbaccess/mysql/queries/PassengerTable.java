package com.brainacad.airport.dao.dbaccess.mysql.queries;

/**
 * Created by User on 17.01.2017.
 */
public class PassengerTable {
    public static final String SELECT = "SELECT * FROM passenger";
    public static final String SELECT_BY = "SELECT * FROM passenger WHERE ? LIKE ?";
    public static final String CREATE = "INSERT INTO passenger VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE = "DELETE FROM passenger WHERE passenger_id = ?";
    public static final String UPDATE = "UPDATE flight SET first_name = ?, last_name = ?, date_of_birthday = ?, " +
                                        "sex = ?, flight_number = ? WHERE passenger_id = ?";
}
