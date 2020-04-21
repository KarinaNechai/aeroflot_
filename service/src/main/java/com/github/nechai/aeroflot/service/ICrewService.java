package com.github.nechai.aeroflot.service;

import com.github.nechai.aeroflot.model.Crew;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;

import java.util.List;

public interface ICrewService {
    public boolean insert(int flightId,List<Integer> listWorker);
    Crew getCrewByFlightId(int flightId);
    List <Worker> getCrewWorkerByProfession(Profession profession);
}
