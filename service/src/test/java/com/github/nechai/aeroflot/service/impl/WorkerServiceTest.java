package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.WorkerDao;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class WorkerServiceTest {
    @InjectMocks
    WorkerService workerService= (WorkerService) WorkerService.getInstance();
    @Mock
    private WorkerDao workerDao;
    @Test
    public void deleteWorker() {
        Mockito.when(workerDao.delete(any())).thenReturn(true);
        assertTrue(workerService.deleteWorker(new Worker("WorkerSurname", "WorkeFirstname",
                "WorkerPatronomic", Profession.RADIOMAN)));
    }

    @Test
    public void getWorkersOfSystem() {
        List<Worker> workerList=new ArrayList<>() ;
        workerList.add(new Worker("WorkerSurname", "WorkeFirstname",
                "WorkerPatronomic2", Profession.RADIOMAN));
        workerList.add(new Worker("WorkerSurname2", "WorkeFirstname2",
                "WorkerPatronomic2", Profession.NAVIGATOR));
        Mockito.when(workerDao.getWorkersOfSystem()).thenReturn(workerList);
        assertEquals( workerService.getWorkersOfSystem() , workerList);
    }
}