package com.example.drones.dtomappers;

import com.example.drones.dtos.DroneDto;
import com.example.drones.models.Drone;
import com.example.drones.models.DroneModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DroneMapperTest {
    @Autowired
    private DroneMapper droneMapper;

    @Test
    void testDroneDtoToDrone() {
        DroneDto droneDto = new DroneDto();
        droneDto.setSerialNumber(UUID.randomUUID().toString());
        DroneModel[] models = DroneModel.values();
        droneDto.setDroneModel(models[ThreadLocalRandom.current().nextInt(models.length)]);
        droneDto.setWeightLimit(ThreadLocalRandom.current().nextInt(500));
        droneDto.setBatteryCapacity(ThreadLocalRandom.current().nextInt(0, 100));

        Drone drone = droneMapper.droneDtoToDrone(droneDto);

        assertEquals(droneDto.getSerialNumber(), drone.getSerialNumber());
        assertEquals(droneDto.getDroneModel(), drone.getDroneModel());
        assertEquals(droneDto.getWeightLimit(), drone.getWeightLimit());
        assertEquals(droneDto.getBatteryCapacity(), drone.getBatteryCapacity());
    }

    @Test
    void testDroneToDroneDto() {
        Drone drone = new Drone();
        drone.setSerialNumber(UUID.randomUUID().toString());
        DroneModel[] models = DroneModel.values();
        drone.setDroneModel(models[ThreadLocalRandom.current().nextInt(models.length)]);
        drone.setWeightLimit(ThreadLocalRandom.current().nextInt(500));
        drone.setBatteryCapacity(ThreadLocalRandom.current().nextInt(0, 100));

        DroneDto droneDto = droneMapper.droneToDroneDto(drone);

        assertEquals(drone.getSerialNumber(), droneDto.getSerialNumber());
        assertEquals(drone.getDroneModel(), droneDto.getDroneModel());
        assertEquals(drone.getWeightLimit(), droneDto.getWeightLimit());
        assertEquals(drone.getBatteryCapacity(), droneDto.getBatteryCapacity());
    }

    @Test
    void testDroneToDroneDtoList() {
        List<Drone> drones = new ArrayList<>();
        drones.add(new Drone());
        drones.add(new Drone());

        List<DroneDto> droneDtos = droneMapper.droneToDroneDto(drones);

        assertEquals(2, droneDtos.size());
    }
}