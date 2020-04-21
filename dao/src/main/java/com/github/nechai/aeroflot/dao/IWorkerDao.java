package com.github.nechai.aeroflot.dao;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import java.util.List;

public interface IWorkerDao {
    boolean insert(Worker worker);
    boolean update(Worker worker);
    boolean delete(Worker worker);
    boolean delete(int workerId);
    Worker getWorkerById(int workerId);
    List<Worker> getWorkersOfSystem();
    List<Worker> getWorkersByProfession(Profession profession);

}
