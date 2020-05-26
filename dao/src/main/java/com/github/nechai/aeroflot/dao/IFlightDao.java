package com.github.nechai.aeroflot.dao;

import com.github.nechai.aeroflot.model.Flight;
import com.github.nechai.aeroflot.model.Page;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IFlightDao {
//    boolean insert(int airportFrom, int airportTo, LocalDateTime  date);
    int save(Flight flight);
    Flight getFlightById(int flightId);
    int delete(int flightId);
    List<Flight> getListFlight();
    List<Flight> getListFlight(Page page);
    public int getCountOfFlights();
}
