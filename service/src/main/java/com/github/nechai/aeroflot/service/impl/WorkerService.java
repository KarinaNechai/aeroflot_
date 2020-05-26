package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.WorkerDao;
import com.github.nechai.aeroflot.model.Page;
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
                    instance = localInstance = new WorkerService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int addWorker(Worker worker) {
        return workerDao.save(worker);
    }

    @Override
    public int updateWorker(Worker worker) {
        return workerDao.save(worker);
    }

    @Override
    public int deleteWorker(Worker worker) {
        return workerDao.delete(worker);
    }

    @Override
    public int deleteWorker(int workerId) {
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

    @Override
    public int getCountOfWorkers() {
        return workerDao.getCountOfWorkers();
    }

    @Override
    public List<Worker> getWorkersOfSystem(Page page) {
        return workerDao.getWorkersOfSystem(page);
    }
}
