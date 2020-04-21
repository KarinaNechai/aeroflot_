package com.github.nechai.aeroflot.service;

import com.github.nechai.aeroflot.model.Flight;

import java.util.Date;
import java.util.List;

public interface IFlightService {
    boolean insert(int airportFrom, int airportTo, Date date);
    Flight getFlightById(int flightId);
    List<Flight> getListFlight();
}
