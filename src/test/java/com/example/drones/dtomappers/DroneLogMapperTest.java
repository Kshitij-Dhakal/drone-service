package com.example.drones.dtomappers;

import com.example.drones.models.Drone;
import com.example.drones.models.DroneLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
class DroneLogMapperTest {
    @Autowired
    private DroneLogMapper droneLogMapper;

    @Test
    void droneLogToDroneLogDto() {
        DroneLog droneLog = new DroneLog();
        Drone drone = new Drone();
        droneLog.setId(ThreadLocalRandom.current().nextLong());
        droneLog.setDrone(drone);
        droneLogMapper.droneLogToDroneLogDto(droneLog);
    }

    @Test
    void droneLogsToDroneLogDtos() {
    }
}