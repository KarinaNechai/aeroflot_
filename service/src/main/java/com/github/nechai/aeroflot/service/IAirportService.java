package com.github.nechai.aeroflot.service;

import com.github.nechai.aeroflot.model.Airport;

import java.util.List;

public interface IAirportService {
    boolean addAirport(Airport airport);
    boolean updateAirport(Airport airport);
    boolean deleteAirport(Airport airport);
    boolean deleteAirport(int airportId);

    Airport getAirportById(int airportId);

    List<Airport> getListAirport();
}
