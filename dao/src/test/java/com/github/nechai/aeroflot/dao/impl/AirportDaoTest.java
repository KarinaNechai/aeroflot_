package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.model.Airport;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportDaoTest {
    public static AirportDao  airportDao=AirportDao.getInstance();
    public static Airport testAirport=new Airport("Testb");
    @BeforeAll
    public static void init() {
        EntityManager entityManager = HibernateUtil.getEntityManager("TEST-UNIT");
        int id=airportDao.insert(testAirport);
        testAirport.setId(id);

    }
    @Test
        void insert() {
        airportDao.insert(new Airport("TestA"));
    }

    @Test
    void getListAirport() {
        List<Airport>  airportList=airportDao.getListAirport();
    }

    @Test
    void update() {
        testAirport.setName("Upname");
        assertTrue(airportDao.update(testAirport));
    }

    @Test
    void delete() {
        assertTrue(airportDao.delete(testAirport));
    }

    @Test
    void testDelete() {
        assertTrue(airportDao.delete(testAirport.getId()));
    }

    @Test
    void getAirportById() {
        assertEquals(testAirport,airportDao.getAirportById(testAirport.getId()));
    }
}