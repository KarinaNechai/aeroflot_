package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.IAirportDao;
import com.github.nechai.aeroflot.dao.impl.AirportDao;
import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.service.IAirportService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AirportService implements IAirportService {
    IAirportDao airportDao;

    public AirportService(IAirportDao airportDao) {
        this.airportDao=airportDao;
    }

    @Override
    @Transactional
    public int addAirport(Airport airport) {
        return airportDao.save(airport);
    }

    @Override
    @Transactional
    public int updateAirport(Airport airport) {
        return airportDao.save(airport);
    }

    @Override
    @Transactional
    public int deleteAirport(Airport airport) {
        return airportDao.delete(airport);
    }

    @Override
    @Transactional
    public int deleteAirport(int airportId) {
        return airportDao.delete(airportId);
    }

    @Override
    @Transactional
    public Airport getAirportById(int airportId) {
        return airportDao.getAirportById(airportId);
    }

    @Override
    @Transactional
    public List<Airport> getListAirport(Page page) {
        return airportDao.getListAirport(page);
    }

    @Override
    @Transactional
    public int getCountOfAirports() {
        return airportDao.getCountOfAirports();
    }

    @Override
    @Transactional
    public List<Airport> getListAirport() {
        return airportDao.getListAirport();
    }
}
