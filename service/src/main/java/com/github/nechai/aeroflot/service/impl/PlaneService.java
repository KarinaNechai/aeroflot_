package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.IPlaneDao;
import com.github.nechai.aeroflot.dao.impl.PlaneDao;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Plane;
import com.github.nechai.aeroflot.service.IPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PlaneService implements IPlaneService {

    IPlaneDao planeDao;
    public PlaneService(IPlaneDao planeDao){
        this.planeDao=planeDao;
    }


    @Override
    @Transactional
    public int addPlane(Plane plane) {
        return planeDao.save(plane);
    }

    @Override
    @Transactional
    public int update(Plane plane) {
        return planeDao.save(plane);
    }

    @Override
    @Transactional
    public int delete(Plane plane) {
        return planeDao.delete(plane);
    }

    @Override
    @Transactional
    public int delete(int planeId) {
        return planeDao.delete(planeId);
    }

    @Override
    @Transactional
    public Plane getPlaneById(int planeId) {
        return planeDao.getPlaneById(planeId);
    }

    @Override
    @Transactional
    public List<Plane> getListPlane() {
        return planeDao.getListPlane();
    }

    @Override
    @Transactional
    public List<Plane> getListPlane(Page page) {
        return planeDao.getListPlane(page);
    }

    @Override
    @Transactional
    public int getCountOfPlanes() {
        return planeDao.getCountOfPlanes();
    }
}

