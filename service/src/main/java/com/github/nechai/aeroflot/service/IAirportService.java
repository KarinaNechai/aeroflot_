package com.github.nechai.aeroflot.service;

import com.github.nechai.aeroflot.model.Airport;

import java.util.List;

public interface IAirportService {
    int addAirport(Airport airport);
    int updateAirport(Airport airport);
    int deleteAirport(Airport airport);
    int deleteAirport(int airportId);

    Airport getAirportById(int airportId);

    List<Airport> getListAirport();
}
