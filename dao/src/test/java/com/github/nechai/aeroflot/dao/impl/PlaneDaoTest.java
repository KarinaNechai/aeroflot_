package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.model.Plane;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneDaoTest {
    public PlaneDao  planeDao=PlaneDao.getInstance();

    @Test
    @Disabled
    void insert() {
        Plane plane=new Plane(
                "Test",100,100);
        plane.getPlaneName();
        planeDao.insert(plane);

    }

    @Test
    void update() {
    }

    @Test
    @Disabled
    void getPlaneById() {
     Plane pl=  planeDao.getPlaneById(1);
     assertTrue(pl!=null);
    }

    @Test
    void delete() {
    }

    @Test
    void getListPlane() {
       List <Plane> pl=  planeDao.getListPlane();
    }
}