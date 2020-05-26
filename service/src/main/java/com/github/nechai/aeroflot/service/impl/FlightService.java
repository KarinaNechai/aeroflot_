package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.FlightDao;
import com.github.nechai.aeroflot.model.Flight;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.service.IFlightService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class FlightService implements IFlightService {
    FlightDao flightDao = FlightDao.getInstance().getInstance();
    private static volatile IFlightService instance;
    private void PlaneService(){
    }

    public static IFlightService getInstance() {
        IFlightService localInstance = instance;
        if (localInstance == null) {
            synchronized (IFlightService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = (IFlightService) new FlightService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Flight getFlightById(int flightId) {
        return flightDao.getFlightById(flightId);
    }

    @Override
    public List<Flight> getListFlight() {
        return flightDao.getListFlight();
    }

    @Override
    public int save(Flight flight) {
        return flightDao.save(flight);
    }

    @Override
    public List<Flight> getListFlight(Page page) {
        return flightDao.getListFlight(page);
    }

    @Override
    public int getCountOfFlights() {
        return flightDao.getCountOfFlights();
    }
}
