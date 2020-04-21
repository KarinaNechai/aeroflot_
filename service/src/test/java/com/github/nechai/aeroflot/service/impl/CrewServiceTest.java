package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.CrewDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

class CrewServiceTest {
    @InjectMocks
    CrewService crewService= (CrewService)CrewService.getInstance();
    @Mock
    private CrewDao crewDao;

    @Test
    void insert() {
 /*       List workerList=new ArrayList();
        workerList.add(1);
        workerList.add(2);
        Mockito.when(crewDao.insertCrewByFlightId(any())).thenReturn(true);
        Mockito.when(crewDao.insertWorkerToCrew(100,1)).thenReturn(true);
        Mockito.when(crewDao.insertWorkerToCrew(100,2)).thenReturn(true);
        Mockito.when(crewDao.getCrewIdByFlightId(any())).thenReturn(1);
        assertTrue(crewService.insert(100,workerList));*/
    }
}