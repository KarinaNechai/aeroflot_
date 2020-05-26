package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.DataSource;
import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.IWorkerDao;
import com.github.nechai.aeroflot.dao.converter.AirportConverter;
import com.github.nechai.aeroflot.dao.converter.ProfessionConverter;
import com.github.nechai.aeroflot.dao.converter.WorkerConverter;
import com.github.nechai.aeroflot.dao.entity.AirportEntity;
import com.github.nechai.aeroflot.dao.entity.ProfessionEntity;
import com.github.nechai.aeroflot.dao.entity.WorkerEntity;
import com.github.nechai.aeroflot.model.*;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDao implements IWorkerDao {
    private static volatile WorkerDao instance;

    private WorkerDao() {
    }

    public static WorkerDao getInstance() {
        WorkerDao localInstance = instance;
        if (localInstance == null) {
            synchronized (WorkerDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new WorkerDao();
                }
            }
        }
        return localInstance;
    }

 /*   public boolean deleteFromBaseForTests(Worker worker) {
        boolean result;
        int workerId = getId(worker);
        String str = "delete from myapp.worker where workerid=?";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement rs = connection.prepareStatement(str);
            rs.setInt(1, workerId);
            result = (rs.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }*/

 /*   private boolean isExist (Worker worker){
        try {
            String str="select * from myapp.worker t where t.workerid=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, worker.getWorkerid());
            ResultSet rs = ps.executeQuery();
            return (rs.next()) ;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }*/

  /* public int getId (Worker worker){
        try {
            String str="select t.workerid from myapp.worker t where t.workersurname=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, worker.getWorkerSurname() != null ? worker.getWorkerSurname() : "");
            ResultSet rs = ps.executeQuery();
            return (rs.next()?rs.getInt("workerid"):-1) ;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }*/

    public Worker getWorkerById(int workerId) {

        final Session session = HibernateUtil.getSession();
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
        final Session session = HibernateUtil.getSession();
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
        final Session session = HibernateUtil.getSession();
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
        final Session session = HibernateUtil.getSession();
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
        final Session session = HibernateUtil.getSession();
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
        final Session session = HibernateUtil.getSession();
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
