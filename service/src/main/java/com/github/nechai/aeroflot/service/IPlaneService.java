package com.github.nechai.aeroflot.service;

import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Plane;
import java.util.List;

public interface IPlaneService {
    int addPlane (Plane plane);
    int update(Plane plane);
    int delete(Plane plane);
    int delete(int planeId);
    Plane getPlaneById(int planeId);
    List<Plane> getListPlane();
    List<Plane> getListPlane(Page page);
    int getCountOfPlanes();
}
