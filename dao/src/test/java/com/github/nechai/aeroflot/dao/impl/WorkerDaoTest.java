package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class WorkerDaoTest {
    @Mock
    public WorkerDao  workerDao=WorkerDao.getInstance();


    @Test
    @Disabled
     public void getWorkerById() {
        String workerSurName="WorkerSurName2";
        String workerFirstName="WorkerFirstName";
        Worker worker=new Worker (
                workerSurName,
                workerFirstName,
                "",
                Profession.PILOT);
        workerDao.insert(worker);
       int id= workerDao.getId(worker);
        assertEquals( worker,workerDao.getWorkerById(id));

    }


    @Test
    @Disabled
    public void getId() {
        String workerSurName="WorkerSurName2";
        String workerFirstName="WorkerFirstName";
        Worker worker=new Worker (
                "WorkerSurName2",
                "WorkerFirstName",
                "",
                Profession.PILOT);
        workerDao.insert(worker);
        assertTrue( !(workerDao.getId(worker)==-1));
        workerDao.deleteFromBaseForTests(worker);
    }

    @Test
    @Disabled
    void deleteFromBaseForTests() {
        String workerSurName="WorkerSurName3";
        String workerFirstName="WorkerFirstName";
        Worker worker=new Worker (
                workerSurName,
                workerFirstName,
                "",
                Profession.PILOT);
        workerDao.insert(worker);
        assertTrue(workerDao.deleteFromBaseForTests(worker));
    }

}