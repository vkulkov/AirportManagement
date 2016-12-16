package com.brainacad.airport.dao.models;

import java.time.LocalDate;

/**
 * Created by User on 28.11.2016.
 */
public class Passenger {
    private Integer passengerID;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String sex;
    private Flight flight;

    public Passenger(Integer passengerID, String firstName, String lastName,
                     LocalDate birthday, String sex, Flight flight) {
        this.passengerID = passengerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
        this.flight = flight;
    }

    public Integer getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(Integer passengerID) {
        this.passengerID = passengerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
