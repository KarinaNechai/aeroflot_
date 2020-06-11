package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.IWorkerDao;
import com.github.nechai.aeroflot.dao.impl.WorkerDao;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import com.github.nechai.aeroflot.service.IWorkerService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class WorkerService implements IWorkerService {
    IWorkerDao workerDao ;

    public WorkerService(IWorkerDao workerDao) {
        this.workerDao=workerDao;
    }

    @Override
    @Transactional
    public int addWorker(Worker worker) {
        return workerDao.save(worker);
    }

    @Override
    @Transactional
    public int updateWorker(Worker worker) {
        return workerDao.save(worker);
    }

    @Override
    @Transactional
    public int deleteWorker(Worker worker) {
        return workerDao.delete(worker);
    }

    @Override
    @Transactional
    public int deleteWorker(int workerId) {
            return workerDao.delete(workerId);
    }

    @Override
    @Transactional
    public Worker getWorkerById(int workerId) {
        return workerDao.getWorkerById(workerId);
    }

    @Override
    @Transactional
    public List<Worker> getWorkersOfSystem() {
        return workerDao.getWorkersOfSystem();
    }

    @Override
    @Transactional
    public List<Worker> getWorkersByProfession(Profession profession) {
        return workerDao.getWorkersByProfession(profession);
    }

    @Override
    @Transactional
    public int getCountOfWorkers() {
        return workerDao.getCountOfWorkers();
    }

    @Override
    @Transactional
    public List<Worker> getWorkersOfSystem(Page page) {
        return workerDao.getWorkersOfSystem(page);
    }
}
