package com.github.nechai.aeroflot.dao;

import com.github.nechai.aeroflot.model.Flight;

import java.util.Date;
import java.util.List;

public interface IFlightDao {
    boolean insert(int airportFrom, int airportTo, Date date);
    Flight getFlightById(int flightId);
    boolean delete(int flightId);
    List<Flight> getListFlight();
}
