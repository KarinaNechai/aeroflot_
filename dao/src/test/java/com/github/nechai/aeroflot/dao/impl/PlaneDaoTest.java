package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Plane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneDaoTest {
    public static PlaneDao planeDao = PlaneDao.getInstance();
    public static Plane testPlane = new Plane("TestPlane");

    @BeforeAll
    public static void init() {
        EntityManager entityManager = HibernateUtil.getEntityManager("TEST-UNIT");
        int id = planeDao.save(testPlane);
        testPlane.setPlaneId(id);
    }

    @Test
     void save() {
        Plane plane = new Plane(
                "Test", 100, 100);
        int i = planeDao.save(plane);
        plane.setPlaneId(i);
        assertEquals(i, plane.getPlaneId());
    }

    @Test
    void getPlaneById() {
        assertEquals(testPlane, planeDao.getPlaneById(testPlane.getPlaneId()));
    }

    @Test
    void delete() {
        assertEquals(testPlane.getPlaneId(), planeDao.delete(testPlane));
    }

    @Test
    void getListPlane() {

        List<Plane> pl = planeDao.getListPlane();
    }

    @Test
    void testGetListPlane() {
        List<Plane> pl = planeDao.getListPlane(new Page(1));
    }

    @Test
    void getCountOfPlanes() {
        int count=planeDao.getCountOfPlanes();
    }
}