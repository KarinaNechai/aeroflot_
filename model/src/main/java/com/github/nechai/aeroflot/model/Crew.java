package com.github.nechai.aeroflot.model;

import java.util.ArrayList;
import java.util.List;

public class Crew {
    private boolean isActual;
    private int flightId;
    private int crewId;
    private List<Worker> listWorker;

    public Crew(int flightId) {
        this.flightId = flightId;
    }

    public Crew(int crewId, int flightId,  List<Worker> listWorker,boolean isActual) {
        this.isActual = isActual;
        this.flightId = flightId;
        this.crewId = crewId;
        this.listWorker = listWorker;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getCrewId() {
        return crewId;
    }

    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    public List<Worker> getListWorker() {
        return listWorker;
    }
    public List<Worker> getListWorkerByProfession(Profession profession)
    {
        List<Worker> listWorker=this.getListWorker();
        List<Worker> listWorkerResult=new ArrayList<Worker>();
        for (Worker worker:listWorker) {
            if (worker.getProfession()==profession){
                listWorkerResult.add(worker);
            }
        }
        return listWorkerResult;
    }

    public void setListWorker(List<Worker> listWorker) {
        this.listWorker = listWorker;
    }
}
