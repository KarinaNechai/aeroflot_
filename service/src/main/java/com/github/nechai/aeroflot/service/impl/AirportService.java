package com.github.nechai.aeroflot.service.impl;
import com.github.nechai.aeroflot.dao.impl.AirportDao;
import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.service.IAirportService;

import java.util.List;

public class AirportService implements IAirportService {
    AirportDao airportDao =AirportDao.getInstance();
    private static volatile IAirportService instance;

    private void AirportService(){
    }

    public static IAirportService getInstance() {
        IAirportService localInstance = instance;
        if (localInstance == null) {
            synchronized (IAirportService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = (IAirportService) new AirportService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean addAirport(Airport airport) {
        return airportDao.insert(airport);
    }

    @Override
    public boolean updateAirport(Airport airport)
    {
        return airportDao.update(airport);
    }

    @Override
    public boolean deleteAirport(Airport airport) {
        return airportDao.delete(airport);
    }

    @Override
    public boolean deleteAirport(int airportId) {
        return airportDao.delete(airportId);
    }
    @Override
    public Airport getAirportById(int airportId) {
        return airportDao.getAirportById(airportId);
    }

    @Override
    public List<Airport> getListAirport() {
        return airportDao.getListAirport();
    }
}
