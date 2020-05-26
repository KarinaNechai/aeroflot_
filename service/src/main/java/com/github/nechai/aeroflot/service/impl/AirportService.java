package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.AirportDao;
import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.service.IAirportService;

import java.util.List;

public class AirportService implements IAirportService {
    AirportDao airportDao = AirportDao.getInstance();
    private static volatile IAirportService instance;

    private AirportService() {
    }

    public static IAirportService getInstance() {
        IAirportService localInstance = instance;
        if (localInstance == null) {
            synchronized (IAirportService.class) {
                localInstance = instance;
                if (localInstance == null) instance = localInstance = new AirportService();
            }
        }
        return localInstance;
    }

    @Override
    public int addAirport(Airport airport) {
        return airportDao.save(airport);
    }

    @Override
    public int updateAirport(Airport airport) {
        return airportDao.save(airport);
    }

    @Override
    public int deleteAirport(Airport airport) {
        return airportDao.delete(airport);
    }

    @Override
    public int deleteAirport(int airportId) {
        return airportDao.delete(airportId);
    }

    @Override
    public Airport getAirportById(int airportId) {
        return airportDao.getAirportById(airportId);
    }

    @Override
    public List<Airport> getListAirport(Page page) {
        return airportDao.getListAirport(page);
    }

    @Override
    public int getCountOfAirports() {
        return airportDao.getCountOfAirports();
    }

    @Override
    public List<Airport> getListAirport() {
        return airportDao.getListAirport();
    }
}
