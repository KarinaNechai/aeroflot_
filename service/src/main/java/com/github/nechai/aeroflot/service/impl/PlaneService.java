package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.PlaneDao;
import com.github.nechai.aeroflot.model.Plane;
import com.github.nechai.aeroflot.service.IPlaneService;

import java.util.List;

public class PlaneService implements IPlaneService {
    PlaneDao planeDao = PlaneDao.getInstance().getInstance();
    private static volatile IPlaneService instance;
    private void PlaneService(){
    }

    public static IPlaneService getInstance() {
        IPlaneService localInstance = instance;
        if (localInstance == null) {
            synchronized (IPlaneService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PlaneService();
                }
            }
        }
        return localInstance;
    }
    @Override
    public boolean addPlane(Plane plane) {
        return planeDao.insert(plane);
    }

    @Override
    public boolean update(Plane plane) {
        return planeDao.update(plane);
    }

    @Override
    public boolean delete(Plane plane) {
        return planeDao.delete(plane);
    }

    @Override
    public boolean delete(int planeId) {
        return planeDao.delete(planeId);
    }

    @Override
    public Plane getPlaneById(int planeId) {
        return planeDao.getPlaneById(planeId);
    }

    @Override
    public List<Plane> getListPlane() {
        return planeDao.getListPlane();
    }
}

