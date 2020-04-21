package com.github.nechai.aeroflot.dao;

import com.github.nechai.aeroflot.model.Plane;

import java.util.List;

public interface  IPlaneDao {
    public boolean insert (Plane plane);
    public boolean update(Plane plane);
    public boolean delete(int planeId);
    public boolean delete(Plane plane);
    public Plane getPlaneById(int planeId);
    List<Plane> getListPlane();

}
