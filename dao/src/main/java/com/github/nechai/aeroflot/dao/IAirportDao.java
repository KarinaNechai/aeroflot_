package com.github.nechai.aeroflot.dao;

import com.github.nechai.aeroflot.model.Airport;
import java.util.List;

public interface IAirportDao {
    boolean insert (Airport airport);
    boolean update(Airport airport);
    boolean delete(Airport airport);
    boolean delete(int airportId);
    List<Airport> getListAirport();

}
