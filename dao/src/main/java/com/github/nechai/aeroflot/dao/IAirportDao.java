package com.github.nechai.aeroflot.dao;

import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.Page;

import java.util.List;

public interface IAirportDao {
  //  int insert (Airport airport);
    int save (Airport airport);
    int delete(Airport airport);
    int delete(int airportId);
    List<Airport> getListAirport(Page page);
    List<Airport> getListAirport();
    int getCountOfAirports();

}
