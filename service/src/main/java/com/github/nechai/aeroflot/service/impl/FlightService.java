package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.FlightDao;
import com.github.nechai.aeroflot.model.Flight;
import com.github.nechai.aeroflot.service.IFlightService;

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
    public boolean insert(int airportFrom, int airportTo, Date date) {
        return flightDao.insert(airportFrom,airportTo,date);
    }

    @Override
    public Flight getFlightById(int flightId) {
        return flightDao.getFlightById(flightId);
    }

    @Override
    public List<Flight> getListFlight() {
        return flightDao.getListFlight();
    }
}
