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
        int id=airportDao.save(testAirport);
        testAirport.setId(id);

    }
    @Test
    @Disabled
        void insert() {
        airportDao.save(new Airport("TestA"));
    }

    @Test
    void getListAirport() {
        List<Airport>  airportList=airportDao.getListAirport();
    }

    @Test
    @Disabled
    void update() {
        testAirport.setName("Upname");
        assertEquals(testAirport.getId(),airportDao.save(testAirport));
    }

    @Test
    void delete() {
        assertEquals(testAirport.getId(),airportDao.delete(testAirport));
    }

    @Test
    void testDelete() {
        assertEquals(testAirport.getId(),airportDao.delete(testAirport.getId()));
    }

    @Test
    void getAirportById() {
        assertEquals(testAirport,airportDao.getAirportById(testAirport.getId()));
    }
}