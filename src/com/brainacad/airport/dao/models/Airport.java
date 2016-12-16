package com.brainacad.airport.dao.models;

/**
 * Created by User on 14.12.2016.
 */
public class Airport {
    private String iataCode;
    private String icaoCode;
    private String cityName;

    public Airport(String iataCode, String icaoCode, String cityName) {
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
        this.cityName = cityName;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
