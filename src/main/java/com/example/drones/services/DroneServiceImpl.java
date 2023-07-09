package com.example.drones.services;

import com.example.drones.models.Drone;
import com.example.drones.repositories.DroneRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;

    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public Drone registerDrone(Drone drone) {
        drone.setId(UUID.randomUUID().toString());
        drone.setRegisteredAt(new Date());
        return droneRepository.save(drone);
    }
}
