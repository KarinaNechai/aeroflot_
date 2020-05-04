package com.github.nechai.aeroflot.service.impl;
import com.github.nechai.aeroflot.dao.impl.PlaneDao;
import com.github.nechai.aeroflot.model.Plane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PlaneServiceTest {
    @InjectMocks
    PlaneService planeService= (PlaneService) PlaneService.getInstance();
    @Mock
    private PlaneDao planeDao;

    @Test
    public void addPlane() {
        Mockito.when(planeDao.save(any())).thenReturn(1);
        assertEquals(1,planeService.addPlane(new Plane("Boing")));
    }

    @Test
    public void delete() {
        Mockito.when(planeDao.delete(any())).thenReturn(2);
        assertEquals(2,planeService.delete(new Plane("Tu")));
    }
}