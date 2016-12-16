package com.brainacad.airport.dao.models;

import java.time.LocalDateTime;

/**
 * Created by User on 28.11.2016.
 */
public class Flight {
    private String flightID;
    private Airport fromAirport;
    private Airport toAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String gate;
    private Integer freeSeats;
    private Float price;
    private String flightStatus;

    public Flight(String flightID, Airport fromAirport, Airport toAirport, LocalDateTime departureTime,
                  LocalDateTime arrivalTime, String gate, Integer freeSeats, Float price, String flightStatus) {
        this.flightID = flightID;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.gate = gate;
        this.freeSeats = freeSeats;
        this.price = price;
        this.flightStatus = flightStatus;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }
}
