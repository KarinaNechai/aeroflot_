package com.github.nechai.aeroflot.service;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import java.util.List;

public interface IWorkerService {
    int addWorker(Worker worker);
    int updateWorker(Worker worker);
    int deleteWorker(Worker worker);
    int deleteWorker(int workerId);
    Worker getWorkerById(int workerId);
    List<Worker> getWorkersOfSystem();
    List<Worker> getWorkersByProfession(Profession profession);
    int getCountOfWorkers();
    List<Worker> getWorkersOfSystem(Page page);
}
