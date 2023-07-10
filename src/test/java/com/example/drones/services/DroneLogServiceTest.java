package com.example.drones.services;

import com.example.drones.models.DroneLog;
import com.example.drones.repositories.DroneLogRepository;
import com.example.drones.repositories.DroneRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DroneLogServiceTest {

    private DroneLogService droneLogService;
    private DroneRepository droneRepository;
    private DroneLogRepository droneLogRepository;

    @BeforeEach
    void setUp() {
        droneRepository = Mockito.mock(DroneRepository.class);
        droneLogRepository = Mockito.mock(DroneLogRepository.class);
        droneLogService = new DroneLogService(droneRepository, droneLogRepository);
    }

    @Test
    void testScheduleFixedDelayTask() {
        Mockito.when(droneRepository.findAll()).thenReturn(new ArrayList<>());
        droneLogService.scheduleFixedDelayTask();
        Mockito.verify(droneLogRepository, Mockito.times(1)).saveAllAndFlush(Mockito.anyList());
        Mockito.verifyNoMoreInteractions(droneLogRepository);
    }

    @Test
    void testListDroneLogs() {
        Mockito.when(droneLogRepository.findAll(Mockito.anyLong(),
                        Mockito.anyLong(),
                        Mockito.anyLong(),
                        Mockito.any()))
                .thenReturn(new ArrayList<>());
        List<DroneLog> droneLogs = droneLogService.listDroneLogs(1L, 2L, 3L, 4);
        assertEquals(0, droneLogs.size());
        Mockito.verify(droneLogRepository, Mockito.times(1)).findAll(1L, 2L, 3L, Pageable.ofSize(4));
        Mockito.verifyNoMoreInteractions(droneLogRepository);
    }

    @Test
    void testListDroneLogsByDroneId() {
        Mockito.when(droneLogRepository.findByDrone(Mockito.anyString(),
                        Mockito.anyLong(),
                        Mockito.anyLong(),
                        Mockito.anyLong(),
                        Mockito.any()))
                .thenReturn(new ArrayList<>());
        List<DroneLog> droneLogs = droneLogService.listDroneLogsByDroneId("12345", 1L, 2L, 3L, 4);
        assertEquals(0, droneLogs.size());
        Mockito.verify(droneLogRepository, Mockito.times(1)).findByDrone("12345", 1L, 2L, 3L, Pageable.ofSize(4));
        Mockito.verifyNoMoreInteractions(droneLogRepository);
    }
}