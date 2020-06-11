package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.config.DaoConfig;
import com.github.nechai.aeroflot.dao.config.HibernateConfig;
import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.DateConverter;
import com.github.nechai.aeroflot.model.Flight;
import com.github.nechai.aeroflot.model.Page;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class})
class FlightDaoTest {
    @Autowired
    public static FlightDao flightDao;
    @Autowired
    public static AirportDao airportDao;
    public static Flight testFl;
    public static Airport testAirportFrom = new Airport("TestFrom");
    public static Airport testAirportTo = new Airport("TestTo");

    @BeforeAll
    public static void init() {
  //      EntityManager entityManager = HibernateUtil.getEntityManager("TEST-UNIT");
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
        Flight flight = flightDao.getFlightById(testFl.getFlightId());
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