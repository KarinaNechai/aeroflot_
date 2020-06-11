package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.config.DaoConfig;
import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.Page;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class})
//@Transactional
class AirportDaoTest {
    @Autowired
    public static AirportDao  airportDao;
    @Autowired
    public static SessionFactory sessionFactory;
    public static Airport testAirport=new Airport("Testb");
    @BeforeAll
    public static void init() {
        SessionFactory s=sessionFactory;
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

        List<Airport>  airportList=airportDao.getListAirport(new Page(1));
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
        assertEquals(testAirport,airportDao.getAirportById(
                testAirport.getId()));
    }

}