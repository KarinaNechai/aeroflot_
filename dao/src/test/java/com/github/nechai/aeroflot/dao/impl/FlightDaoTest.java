package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.DateConverter;
import com.github.nechai.aeroflot.model.Flight;
import com.github.nechai.aeroflot.model.Page;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class FlightDaoTest {
    public static FlightDao flightDao = FlightDao.getInstance();
    public static Flight testFl;
    public static AirportDao airportDao = AirportDao.getInstance();
    public static Airport testAirportFrom = new Airport("TestFrom");
    public static Airport testAirportTo = new Airport("TestTo");

    @BeforeAll
    public static void init() {
        EntityManager entityManager = HibernateUtil.getEntityManager("TEST-UNIT");
        int id = airportDao.save(testAirportFrom);
        testAirportFrom.setId(id);
        id = airportDao.save(testAirportTo);
        testAirportTo.setId(id);
        testFl = new Flight(testAirportFrom, testAirportTo, DateConverter.DateConverter(LocalDateTime.now()));
        testFl.setFlightId(flightDao.save(testFl));
    }

    @Test
    @Disabled
    void getFlightById() {
        Flight flight=flightDao.getFlightById(testFl.getFlightId());
        assertEquals(testFl, flightDao.getFlightById(testFl.getFlightId()));
    }

    @Test
    void getListFlight() {
        flightDao.getListFlight();
    }

    @Test
    void getListFlightPage() {
        flightDao.getListFlight(new Page(1));
    }

    @Test
    void delete() {
        assertEquals(testFl.getFlightId(), flightDao.delete(testFl.getFlightId()));
    }
}