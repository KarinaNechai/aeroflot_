package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.AirportDao;
import com.github.nechai.aeroflot.dao.impl.FlightDao;
import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.DateConverter;
import com.github.nechai.aeroflot.model.Flight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {
    @InjectMocks
    FlightService flightService;
    @Mock
    private FlightDao flightDao;
    @Mock
    private AirportDao airportDao;
    @Test
    void getFlightById() {
        Flight flight=new Flight(new Airport("AirportFrom"),new Airport("AirportTo"), DateConverter.DateConverter(LocalDateTime.now()));
        Mockito.when(flightDao.getFlightById(1)).thenReturn(flight);
        assertEquals(flight,flightService.getFlightById(1));
    }


    @Test
    void save() {
        Mockito.when(flightDao.save(any())).thenReturn(1);
        Flight flight=new Flight(new Airport("AirportFrom"),new Airport("AirportTo"), DateConverter.DateConverter(LocalDateTime.now()));
        assertEquals(1,flightService.save(flight));
    }


    @Test
    void getCountOfFlights() {
        Mockito.when(flightDao.getCountOfFlights()).thenReturn(1);
        assertEquals(1,flightService.getCountOfFlights());
    }
}