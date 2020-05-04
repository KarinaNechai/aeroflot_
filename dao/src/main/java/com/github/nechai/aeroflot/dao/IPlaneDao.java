package com.github.nechai.aeroflot.dao;

import com.github.nechai.aeroflot.model.Plane;

import java.util.List;

public interface  IPlaneDao {
/*    public boolean insert (Plane plane);
    public boolean update(Plane plane);*/
    int save(Plane plane);
    int delete(int planeId);
    int delete(Plane plane);
    Plane getPlaneById(int planeId);
    List<Plane> getListPlane();

}
