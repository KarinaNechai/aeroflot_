package com.github.nechai.aeroflot.dao.converter;

import com.github.nechai.aeroflot.dao.entity.WorkerEntity;
import com.github.nechai.aeroflot.model.Worker;

public class WorkerConverter {
    public static WorkerEntity toEntity(Worker worker){
        if (worker==null){
            return null;
        }
        final WorkerEntity workerEntity=new WorkerEntity();
        workerEntity.setId(worker.getWorkerid());
        workerEntity.setWorkeFirstname(worker.getWorkeFirstname());
        workerEntity.setWorkerSurname(worker.getWorkerSurname());
        workerEntity.setWorkerPatronomic(worker.getWorkerPatronomic());
        workerEntity.setProfession(ProfessionConverter.toEntity(worker.getProfession()));
        workerEntity.setActFl(worker.isActual()?1:0);
        return   workerEntity;
    }
    public static Worker fromEntity(WorkerEntity workerEntity){
        if (workerEntity==null){
            return null;
        }
        Worker worker=new Worker();
        worker.setWorkerid(workerEntity.getId());
        worker.setWorkeFirstname(workerEntity.getWorkeFirstname());
        worker.setWorkerSurname(workerEntity.getWorkerSurname());
        worker.setWorkerPatronomic(workerEntity.getWorkerPatronomic());
        worker.setProfession(ProfessionConverter.fromEntity(workerEntity.getProfession()));
        worker.setActual(workerEntity.getActFl()==1);
        return worker;
    }
}
