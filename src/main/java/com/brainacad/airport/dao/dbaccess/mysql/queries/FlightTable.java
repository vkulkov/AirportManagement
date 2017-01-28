package com.brainacad.airport.dao.dbaccess.mysql.queries;

/**
 * Created by User on 17.01.2017.
 */
public class FlightTable {
    public static final String SELECT = "SELECT * FROM flight";
    public static final String SELECT_BY = "SELECT * FROM flight WHERE ? LIKE ?";
    public static final String CREATE = "INSERT INTO flight VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE = "DELETE FROM flight WHERE flight_id = ?";
    public static final String UPDATE = "UPDATE flight SET from_airport = ?, to_airport = ?, departure = ?, arrival = ?, " +
                                                "gate = ?, number_of_seats = ?, flight_price = ?, flight_status = ? " +
                                                "WHERE flight_id = ?";
}
