package com.github.nechai.aeroflot.dao.converter;

import com.github.nechai.aeroflot.dao.entity.AirportEntity;
import com.github.nechai.aeroflot.model.Airport;

public class AirportConverter {
    public static AirportEntity toEntity(Airport airport){
        if (airport==null){
            return null;
        }
        final AirportEntity airportEntity=new AirportEntity();
        airportEntity.setId(airport.getId());
        airportEntity.setName(airport.getName());
        airportEntity.setActFl(airport.isActual()?1:0);
        return  airportEntity;
    }
    public static Airport fromEntity(AirportEntity airportEntity){
        if (airportEntity==null){
            return null;
        }
        Airport airport=new Airport();
        airport.setId(airportEntity.getId());
        airport.setName(airportEntity.getName());
        airport.setActual(airportEntity.getActFl() == 1);
        return airport;
    }
}
