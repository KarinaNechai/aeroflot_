package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.WorkerDao;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import com.github.nechai.aeroflot.service.IWorkerService;

import java.util.List;

public class WorkerService implements IWorkerService {
    WorkerDao workerDao = WorkerDao.getInstance();
    private static volatile IWorkerService instance;

    private void WorkerService(){
    }

    public static IWorkerService getInstance() {
        IWorkerService localInstance = instance;
        if (localInstance == null) {
            synchronized (IWorkerService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = (IWorkerService) new WorkerService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean addWorker(Worker worker) {
        return workerDao.insert(worker);
    }

    @Override
    public boolean updateWorker(Worker worker) {
        return workerDao.update(worker);
    }

    @Override
    public boolean deleteWorker(Worker worker) {
        return workerDao.delete(worker);
    }

    @Override
    public boolean deleteWorker(int workerId) {
            return workerDao.delete(workerId);
    }

    @Override
    public Worker getWorkerById(int workerId) {
        return workerDao.getWorkerById(workerId);
    }

    @Override
    public List<Worker> getWorkersOfSystem() {
        return workerDao.getWorkersOfSystem();
    }

    @Override
    public List<Worker> getWorkersByProfession(Profession profession) {
        return workerDao.getWorkersByProfession(profession);
    }
}
