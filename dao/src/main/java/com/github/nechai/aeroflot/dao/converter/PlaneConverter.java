package com.github.nechai.aeroflot.dao.converter;

import com.github.nechai.aeroflot.dao.entity.PlaneEntity;
import com.github.nechai.aeroflot.model.Plane;

public class PlaneConverter {
    public static PlaneEntity toEntity(Plane plane){
        if (plane==null){
            return null;
        }
        final PlaneEntity planeEntity=new  PlaneEntity();
        planeEntity.setId(plane.getPlaneId());
        planeEntity.setPlaneName(plane.getPlaneName());
        planeEntity.setCapacity(plane.getCapacity());
        planeEntity.setRange(plane.getRange());
        planeEntity.setActFl(plane.isActual()?1:0);
        return planeEntity;
    }
    public static Plane fromEntity(PlaneEntity planeEntity){
        if (planeEntity==null){
            return null;
        }
        Plane plane=new Plane();
        plane.setPlaneId(planeEntity.getId());
        plane.setPlaneName(planeEntity.getPlaneName());
        plane.setCapacity(planeEntity.getCapacity());
        plane.setRange(planeEntity.getRange());
        plane.setActual(planeEntity.getActFl()==1);
        return plane;
    }
}
