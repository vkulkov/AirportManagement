package com.brainacad.airport.dao.dbaccess.mysql.queries;

/**
 * Created by User on 17.01.2017.
 */
public class AirportTable {
    public static final String SELECT = "SELECT * FROM airport";
    public static final String SELECT_BY = "SELECT * FROM airport WHERE ? LIKE ?";
    public static final String CREATE = "INSERT INTO airport VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE = "DELETE FROM airport WHERE airport_iata = ?";
    public static final String UPDATE = "UPDATE flight SET airport_icao = ?, city = ? WHERE airport_iata = ?";
}
