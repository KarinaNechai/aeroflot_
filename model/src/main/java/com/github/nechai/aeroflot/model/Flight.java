package com.github.nechai.aeroflot.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Flight {
    private int flightId;
    private Airport airportFrom;
    private Airport airportTo;
    private List<Worker> listWorker;
    private Plane plane;
    private int flightRange;
    private boolean isActual;
    private int amountPassengers;
    private LocalDateTime dateTime;


    public Flight() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Flight(Airport airportFrom, Airport airportTo, LocalDateTime dateTime) {
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.dateTime = dateTime;
        this.setActual(true);
    }

    public Flight(int flightId, Airport airportFrom, Airport airportTo, List<Worker> listWorker, Plane plane, int flightRange, boolean isActual, int amountPassengers, LocalDateTime dateTime) {
        this.flightId = flightId;
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.listWorker = listWorker;
        this.plane = plane;
        this.flightRange = flightRange;
        this.isActual = isActual;
        this.amountPassengers = amountPassengers;
        this.dateTime = dateTime;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Airport getAirportFrom() {

        return airportFrom;
    }

    public void setAirportFrom(Airport airportFrom) {
        this.airportFrom = airportFrom;
    }

    public Airport getAirportTo() {
        return airportTo;
    }

    public void setAirportTo(Airport airportTo) {
        this.airportTo = airportTo;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {

        this.plane = plane;
    }

    public int getFlightRange() {
        return flightRange;
    }

    public void setFlightRange(int flightrange) {
        this.flightRange = flightrange;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }



    public LocalDateTime getDatetime() {
        return dateTime;
    }

    public void setDatetime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public List<Worker> getListWorker() {
        return listWorker;
    }

    public void setListWorker(List<Worker> listWorker) {
        this.listWorker = listWorker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        boolean q=getAirportFrom().equals(flight.getAirportFrom());
        boolean q1=getAirportTo().equals(flight.getAirportTo());
        boolean q2=Objects.equals(getListWorker(), flight.getListWorker());
        boolean q3=Objects.equals(getPlane(), flight.getPlane());
        boolean q4=getDatetime().equals(flight.getDatetime());

        return getFlightId() == flight.getFlightId() &&
                flightRange == flight.flightRange &&
                isActual() == flight.isActual() &&
                amountPassengers == flight.amountPassengers &&
                getAirportFrom().equals(flight.getAirportFrom()) &&
                getAirportTo().equals(flight.getAirportTo()) &&
                Objects.equals(getListWorker(), flight.getListWorker()) &&
                Objects.equals(getPlane(), flight.getPlane()) &&
                getDatetime().equals(flight.getDatetime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlightId(), getAirportFrom(), getAirportTo(), getListWorker(), getPlane(), flightRange, isActual(), amountPassengers, getDatetime());
    }

    public int getAmountPassengers() {
        return amountPassengers;
    }

    public void setAmountPassengers(int amountPassengers) {
        this.amountPassengers = amountPassengers;
    }
}
