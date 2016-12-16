package com.brainacad.airport.service;

/**
 * Created by User on 07.12.2016.
 */
public abstract class Validation {
    private static final int INTEGER = 1;
    private static final int FLOAT = 2;
    private static final int STRING = 3;
    private static final int DATE = 4;

    public static boolean validate(int type) {
        return true;
    }
}
