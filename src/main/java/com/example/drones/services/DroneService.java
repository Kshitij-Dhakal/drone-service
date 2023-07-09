package com.example.drones.services;

import com.example.drones.models.Drone;
import com.example.drones.models.Medication;

import java.util.List;

public interface DroneService {
    Drone registerDrone(Drone drone);

    void loadMedications(String droneId, List<Medication> medications);

    List<Medication> listDroneMedications(String droneId);

    List<Drone> listDrones();
}
