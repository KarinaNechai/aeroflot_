package com.github.nechai.aeroflot.dao;

import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Plane;

import java.util.List;

public interface  IPlaneDao {
    int save(Plane plane);
    int delete(int planeId);
    int delete(Plane plane);
    Plane getPlaneById(int planeId);
    List<Plane> getListPlane();
    List<Plane> getListPlane(Page page);
    public int getCountOfPlanes();

}
