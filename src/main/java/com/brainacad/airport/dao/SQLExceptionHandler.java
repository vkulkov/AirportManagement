package com.brainacad.airport.dao;

import java.sql.SQLException;

/**
 * Created by User on 16.12.2016.
 */
public abstract class SQLExceptionHandler {

    public static void printSQLException(SQLException e) {
        if (e != null) {
            if (!isIgnored(e)) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println("Error Code: " + e.getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = e.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
                printSQLException(e.getNextException());
            }
        }
    }

    private static boolean isIgnored(SQLException e) {
        return (e.getSQLState().equals("S1000"));
    }
}
