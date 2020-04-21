package com.github.nechai.aeroflot.model;

import java.util.Date;
import java.util.List;

public class Flight {
    private int flightId;
    private int airportFrom;
    private int airportTo;
    private int crewid;
    private int planeid;
    private int flightrange;
    private boolean isActual;
    private int amountpassengers;
    private Date datetime;
    boolean actfl;

    public Flight(int flightId, int airportFrom, int airportTo, int crewid, int planeid, int flightrange,
                  int amountpassengers, Date datetime, boolean actfl) {
        this.flightId = flightId;
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.crewid = crewid;
        this.planeid = planeid;
        this.flightrange = flightrange;
        this.amountpassengers = amountpassengers;
        this.datetime = datetime;
        this.actfl = actfl;
    }

    public Flight(int airportFrom, int airportTo, Date datetime) {
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.datetime = datetime;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getAirportFrom() {
        return airportFrom;
    }

    public void setAirportFrom(int airportFrom) {
        this.airportFrom = airportFrom;
    }

    public int getAirportTo() {
        return airportTo;
    }

    public void setAirportTo(int airportTo) {
        this.airportTo = airportTo;
    }

    public int getCrewid() {
        return crewid;
    }

    public void setCrewid(int crewid) {
        this.crewid = crewid;
    }

    public int getPlaneid() {
        return planeid;
    }

    public void setPlaneid(int planeid) {
        this.planeid = planeid;
    }

    public int getFlightrange() {
        return flightrange;
    }

    public void setFlightrange(int flightrange) {
        this.flightrange = flightrange;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

     public int getAmountpassengers() {
        return amountpassengers;
    }

    public void setAmountpassengers(int amountpassengers) {
        this.amountpassengers = amountpassengers;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public boolean isActfl() {
        return actfl;
    }

    public void setActfl(boolean actfl) {
        this.actfl = actfl;
    }
}
