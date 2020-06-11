package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.IWorkerDao;
import com.github.nechai.aeroflot.dao.converter.ProfessionConverter;
import com.github.nechai.aeroflot.dao.converter.WorkerConverter;
import com.github.nechai.aeroflot.dao.entity.ProfessionEntity;
import com.github.nechai.aeroflot.dao.entity.WorkerEntity;
import com.github.nechai.aeroflot.model.*;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class WorkerDao implements IWorkerDao {
    private final SessionFactory factory;

    public WorkerDao(SessionFactory factory) {
        this.factory = factory;
    }

     public Worker getWorkerById(int workerId) {
        final Session session = factory.getCurrentSession();
        Query query = session.createQuery("from WorkerEntity where id=:paramId");
        query.setParameter("paramId", workerId);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        return WorkerConverter.fromEntity((WorkerEntity) query.uniqueResult());
    }

    @Override
    public int save(Worker worker) {
        WorkerEntity workerEntity = WorkerConverter.toEntity(worker);
        final Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(workerEntity);
        session.getTransaction().commit();
        return workerEntity.getId();
    }

    @Override
    public int delete(Worker worker) {
        worker.setActual(false);
        return save(worker);
    }

    @Override
    public int delete(int workerId) {
        Worker worker = getWorkerById(workerId);
        worker.setActual(false);
        return save(worker);
    }

    @Override
    public List<Worker> getWorkersOfSystem() {
        List<Worker> workers = new ArrayList<>();
        final Session session = factory.getCurrentSession();
        Query query = session.createQuery("from WorkerEntity where actFl=:paramActFl order by workerSurname asc");
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List<WorkerEntity> workerEntityList = (List<WorkerEntity>) query.list();
        for (WorkerEntity a : workerEntityList) {
            workers.add(WorkerConverter.fromEntity(a));
        }
        return workers;
    }

    public List<Worker> getWorkersOfSystem(Page page) {
        List<Worker> workers = new ArrayList<>();
        final Session session = factory.getCurrentSession();
        Query query = session.createQuery("from WorkerEntity where actFl=:paramActFl order by workerSurname asc");
        query.setParameter("paramActFl", 1);
        query.setFirstResult(page.getFirst());
        query.setMaxResults(page.getMax());
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List<WorkerEntity> workerEntityList = (List<WorkerEntity>) query.list();
        for (WorkerEntity a : workerEntityList) {
            workers.add(WorkerConverter.fromEntity(a));
        }
        return workers;
    }


    @Override
    public List<Worker> getWorkersByProfession(Profession profession) {
        ProfessionEntity professionEntity = ProfessionConverter.toEntity(profession);
        List<Worker> workers = new ArrayList<>();
        final Session session = factory.getCurrentSession();
        Query query = session.createQuery("from WorkerEntity where actFl=:paramActFl and profession=:paramPr order by workerSurname asc");
        query.setParameter("paramActFl", 1);
        query.setParameter("paramPr", professionEntity);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List<WorkerEntity> workerEntityList = (List<WorkerEntity>) query.list();
        for (WorkerEntity a : workerEntityList) {
            workers.add(WorkerConverter.fromEntity(a));
        }
        return workers;
    }

    public int getCountOfWorkers() {
        final Session session = factory.getCurrentSession();
        Query query = session.createQuery("select count(*) from WorkerEntity where actFl=:paramActFl");
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT).
                // сущности и коллекции помечаюся как только для чтения
                        setReadOnly(true);
        long count = (Long) query.getSingleResult();
        return (int) count;
    }
}
