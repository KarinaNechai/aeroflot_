package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.CrewDao;
import com.github.nechai.aeroflot.model.Crew;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import com.github.nechai.aeroflot.service.ICrewService;

import java.util.List;

public class CrewService implements ICrewService {
    CrewDao crewDao = CrewDao.getInstance().getInstance();
    private static volatile ICrewService instance;

    private void CrewService(){
    }

    public static ICrewService getInstance() {
        ICrewService localInstance = instance;
        if (localInstance == null) {
            synchronized (ICrewService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CrewService();
                }
            }
        }
        return localInstance;
    }
    @Override
    public boolean insert(int flightId,List<Integer> listWorker) {
        if (!crewDao.insertCrewByFlightId(flightId))
        {
            return false;
        }
        int crewId=crewDao.getCrewIdByFlightId(flightId);
        if (listWorker!=null){
            for (int id:listWorker ) {
                if (!crewDao.insertWorkerToCrew(crewId,id)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Crew getCrewByFlightId(int flightId) {
        int crewid=crewDao.getCrewIdByFlightId(flightId);
        crewDao.getCrewListWorkerById(crewid);
        Crew crew=new Crew(crewid,flightId,crewDao.getCrewListWorkerById(crewid),true);
        return crew;
    }

    @Override
    public List<Worker> getCrewWorkerByProfession(Profession profession) {
       return null;
    }

}
