package com.example.drones.dtomappers;

import com.example.drones.dtos.DroneLogDto;
import com.example.drones.models.Drone;
import com.example.drones.models.DroneLog;
import com.example.drones.models.DroneState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DroneLogMapperTest {
    @Autowired
    private DroneLogMapper droneLogMapper;

    @Test
    void droneLogToDroneLogDto() {
        DroneLog droneLog = new DroneLog();
        Drone drone = new Drone();
        drone.setId(UUID.randomUUID().toString());
        drone.setSerialNumber(UUID.randomUUID().toString());
        droneLog.setId(ThreadLocalRandom.current().nextLong());
        droneLog.setDrone(drone);
        DroneState[] states = DroneState.values();
        droneLog.setDroneState(states[ThreadLocalRandom.current().nextInt(states.length)]);
        droneLog.setBattery(ThreadLocalRandom.current().nextInt(0, 100));
        droneLog.setTimestamp(System.currentTimeMillis());

        DroneLogDto droneLogDto = droneLogMapper.droneLogToDroneLogDto(droneLog);

        assertNotNull(droneLogDto);
        assertEquals(droneLog.getId(), droneLogDto.getId());
        assertEquals(droneLog.getDrone().getId(), droneLogDto.getDroneId());
        assertEquals(droneLog.getDrone().getSerialNumber(), droneLogDto.getSerialNumber());
        assertEquals(droneLog.getDroneState(), droneLogDto.getDroneState());
        assertEquals(droneLog.getBattery(), droneLogDto.getBattery());
        assertEquals(droneLog.getTimestamp(), droneLogDto.getTimestamp());
    }

    @Test
    void droneLogsToDroneLogDtos() {
        List<DroneLog> logs = new ArrayList<>();
        logs.add(new DroneLog());
        logs.add(new DroneLog());

        List<DroneLogDto> droneLogDtos = droneLogMapper.droneLogsToDroneLogDtos(logs);
        assertNotNull(droneLogDtos);
        assertEquals(2, droneLogDtos.size());

    }
}