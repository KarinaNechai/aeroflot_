package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.AirportDao;
import com.github.nechai.aeroflot.model.Airport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AirportServiceTest {
    @InjectMocks
    AirportService airportService= (AirportService)AirportService.getInstance();
    @Mock
    private AirportDao airportDao;
    @Test
    public void addAirport() {
        Mockito.when(airportDao.insert(any())).thenReturn(true);
        Mockito.when(airportDao.insert(null)).thenReturn(false);
        assertTrue(airportService.addAirport(new Airport("Dallase")));
        assertFalse(airportService.addAirport(null));
    }

    @Test
    public void getListAirport() {
        List<Airport> airportList=new ArrayList<>() ;
        airportList.add(new Airport("Dallas"));
        airportList.add(new Airport("Minsk1"));
        airportList.add(new Airport("Domodedovo"));
        Mockito.when(airportDao.getListAirport()).thenReturn(airportList);
        assertEquals(airportList,airportService.getListAirport());
    }
}