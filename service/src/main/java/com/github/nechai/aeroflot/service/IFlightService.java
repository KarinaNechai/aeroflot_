package com.github.nechai.aeroflot.service;

import com.github.nechai.aeroflot.model.Flight;
import com.github.nechai.aeroflot.model.Page;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IFlightService {
    Flight getFlightById(int flightId);
    List<Flight> getListFlight();
    int save(Flight flight);
    List<Flight> getListFlight(Page page);
    int getCountOfFlights();
}
