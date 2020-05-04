package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.converter.ProfessionConverter;
import com.github.nechai.aeroflot.dao.entity.ProfessionEntity;
import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class WorkerDaoTest {
    public static WorkerDao  workerDao=WorkerDao.getInstance();
    public static Worker testWorker=new Worker ("Test","Test","",Profession.PILOT);
    @BeforeAll
    public static void init(){
        EntityManager entityManager = HibernateUtil.getEntityManager("TEST-UNIT");
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        for (Profession p:Profession.values() ) {
        session.saveOrUpdate(ProfessionConverter.toEntity(p));
            }
        session.getTransaction().commit();
        ProfessionConverter.init();
        session.beginTransaction();
        int id=workerDao.save(testWorker);
        session.getTransaction().commit();
        testWorker.setWorkerid(id);
     }

    @Test
     public void getWorkerById() {
       assertEquals( testWorker,workerDao.getWorkerById(testWorker.getWorkerid()));
    }


    @Test
    void save() {
        assertEquals( testWorker.getWorkerid(),workerDao.save(testWorker));
    }

    @Test
    void delete() {
        assertEquals( testWorker.getWorkerid(),workerDao.delete(testWorker));
    }

    @Test
    void getWorkersOfSystem() {
        workerDao.getWorkersOfSystem();
    }

    @Test
    void getWorkersByProfession() {
        workerDao.getWorkersByProfession(Profession.PILOT);
    }
}