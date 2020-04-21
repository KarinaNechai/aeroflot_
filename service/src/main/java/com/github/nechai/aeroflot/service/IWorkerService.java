package com.github.nechai.aeroflot.service;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import java.util.List;

public interface IWorkerService {
    boolean addWorker(Worker worker);
    boolean updateWorker(Worker worker);
    boolean deleteWorker(Worker worker);
    boolean deleteWorker(int workerId);
    public Worker getWorkerById(int workerId);
    List<Worker> getWorkersOfSystem();
    List<Worker> getWorkersByProfession(Profession profession);
}
