package com.github.nechai.aeroflot.service;

import com.github.nechai.aeroflot.model.Plane;
import java.util.List;

public interface IPlaneService {
    boolean addPlane (Plane plane);
    boolean update(Plane plane);
    boolean delete(Plane plane);
    boolean delete(int planeId);
    Plane getPlaneById(int planeId);
    List<Plane> getListPlane();
}
