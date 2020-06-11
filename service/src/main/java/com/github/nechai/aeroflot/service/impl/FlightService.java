package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.IFlightDao;
import com.github.nechai.aeroflot.model.Flight;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.service.IFlightService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public class FlightService implements IFlightService {
    IFlightDao flightDao;

    public FlightService(IFlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    @Transactional
    public Flight getFlightById(int flightId) {
        return flightDao.getFlightById(flightId);
    }

    @Override
    @Transactional
    public List<Flight> getListFlight() {
        return flightDao.getListFlight();
    }

    @Override
    @Transactional
    public int save(Flight flight) {
        return flightDao.save(flight);
    }

    @Override
    @Transactional
    public List<Flight> getListFlight(Page page) {
        return flightDao.getListFlight(page);
    }

    @Override
    @Transactional
    public int getCountOfFlights() {
        return flightDao.getCountOfFlights();
    }
}
