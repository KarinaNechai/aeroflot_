package com.github.nechai.aeroflot.dao.converter;

import com.github.nechai.aeroflot.dao.entity.FlightEntity;
import com.github.nechai.aeroflot.dao.entity.WorkerEntity;
import com.github.nechai.aeroflot.model.DateConverter;
import com.github.nechai.aeroflot.model.Flight;
import com.github.nechai.aeroflot.model.Worker;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightConverter {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static FlightEntity toEntity(Flight flight) {
        if (flight == null) {
            return null;
        }
        final FlightEntity flightEntity = new FlightEntity();
        flightEntity.setAmountPassengers(flight.getAmountPassengers());
        flightEntity.setFlightRange(flight.getFlightRange());
        flightEntity.setId(flight.getFlightId());
        flightEntity.setPlane(PlaneConverter.toEntity(flight.getPlane()));
        flightEntity.setDatetime(DateConverter.DateConverter(flight.getDatetime()));
        flightEntity.setAirportTo(AirportConverter.toEntity(flight.getAirportTo()));
        flightEntity.setAirportFrom(AirportConverter.toEntity(flight.getAirportFrom()));
        flightEntity.setActFl(flight.isActual() ? 1 : 0);
        List<Worker> listWorker = flight.getListWorker();
        if (listWorker != null) {
            List<WorkerEntity> listWorkerEntity = new ArrayList<>();
            for (Worker w : listWorker) {
                listWorkerEntity.add(WorkerConverter.toEntity(w));
            }
            flightEntity.setListWorker(listWorkerEntity);
        }
        return flightEntity;
    }

    public static Flight fromEntity(FlightEntity flightEntity) {
        if (flightEntity == null) {
            return null;
        }
        Flight flight = new Flight();
        flight.setFlightId(flightEntity.getId());
        flight.setPlane(PlaneConverter.fromEntity(flightEntity.getPlane()));
        flight.setFlightRange(flightEntity.getFlightRange());
        flight.setDatetime(DateConverter.DateConverter(flightEntity.getDatetime()));
        flight.setAmountPassengers(flightEntity.getAmountPassengers());
        flight.setAirportTo(AirportConverter.fromEntity(flightEntity.getAirportTo()));
        flight.setAirportFrom(AirportConverter.fromEntity(flightEntity.getAirportFrom()));
        flight.setActual(flightEntity.getActFl() == 1);
        List<WorkerEntity> listWorkerEntity = flightEntity.getListWorker();
        if (listWorkerEntity.size()!=0) {
            List<Worker> listWorker = new ArrayList<>();
            for (WorkerEntity w : listWorkerEntity) {
                listWorker.add(WorkerConverter.fromEntity(w));
            }
            flight.setListWorker(listWorker);
        }
        return flight;
    }
}
