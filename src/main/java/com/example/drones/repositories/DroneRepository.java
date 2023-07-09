package com.example.drones.repositories;

import com.example.drones.models.Drone;
import com.example.drones.models.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, String> {
    List<Drone> findByDroneStateAndBatteryCapacityGreaterThanEqual(DroneState droneState, Integer batteryCapacity);
}