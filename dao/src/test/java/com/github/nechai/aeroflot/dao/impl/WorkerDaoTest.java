package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.config.DaoConfig;
import com.github.nechai.aeroflot.dao.config.HibernateConfig;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class})
class WorkerDaoTest {
    @Autowired
    public static WorkerDao workerDao;
    public static Worker testWorker = new Worker("Test", "Test", "", Profession.PILOT);

    @BeforeAll
    public static void init() {
   /*    EntityManager entityManager = HibernateUtil.getEntityManager("TEST-UNIT");
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        for (Profession p : Profession.values()) {
            session.saveOrUpdate(ProfessionConverter.toEntity(p));
        }
        session.getTransaction().commit();
        ProfessionConverter.init();
        session.beginTransaction();
        int id = workerDao.save(testWorker);
        session.getTransaction().commit();
        testWorker.setWorkerid(id);*/
    }

    @Test
    public void getWorkerById() {
        assertEquals(testWorker, workerDao.getWorkerById(testWorker.getWorkerid()));
    }


    @Test
    void save() {
        assertEquals(testWorker.getWorkerid(), workerDao.save(testWorker));
    }

    @Test
    void delete() {
        assertEquals(testWorker.getWorkerid(), workerDao.delete(testWorker));
    }

    @Test
    void getWorkersOfSystem() {
        workerDao.getWorkersOfSystem();
    }

    @Test
    void getWorkersByProfession() {
        workerDao.getWorkersByProfession(Profession.PILOT);
    }


    @Test
    void getCountOfWorkers() {
        assertNotNull(workerDao.getCountOfWorkers());
    }
}