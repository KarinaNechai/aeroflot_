package com.github.nechai.aeroflot.dao;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import java.util.List;

public interface IWorkerDao {
    int save(Worker worker);
//    boolean update(Worker worker);
    int delete(Worker worker);
    int delete(int workerId);
    Worker getWorkerById(int workerId);
    List<Worker> getWorkersOfSystem();
    List<Worker> getWorkersByProfession(Profession profession);

}
