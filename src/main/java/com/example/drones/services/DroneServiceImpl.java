package com.example.drones.services;

import com.example.drones.errors.InvalidStateException;
import com.example.drones.errors.NotFoundException;
import com.example.drones.models.Drone;
import com.example.drones.models.DroneState;
import com.example.drones.models.Medication;
import com.example.drones.repositories.DroneRepository;
import com.example.drones.repositories.MedicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    public DroneServiceImpl(
            DroneRepository droneRepository,
            MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public Drone registerDrone(Drone drone) {
        drone.setId(UUID.randomUUID().toString());
        drone.setRegisteredAt(new Date());
        return droneRepository.save(drone);
    }

    @Override
    @Transactional
    public void loadMedications(String droneId, List<Medication> medications) {
        Drone drone = getDroneById(droneId);
        int weightLimit = drone.getWeightLimit();
        int currentWeight = droneRepository.getTotalMedicationsWeightByDroneId(drone.getId()).orElse(0);
        drone.setDroneState(DroneState.DRONE_STATE_LOADING);
        for (Medication medication : medications) {
            medication.setId(UUID.randomUUID().toString());
            currentWeight += medication.getWeight();
            if (currentWeight > weightLimit) {
                throw new InvalidStateException("Current weight is greater that drone's carrying capacity.");
            }
        }
        medications = medicationRepository.saveAll(medications);
        drone.getMedications().addAll(medications);
    }

    @Override
    public Drone getDroneById(String droneId) {
        Optional<Drone> droneById = droneRepository.findById(droneId);
        if (droneById.isEmpty()) {
            throw new NotFoundException("Drone not found for given id");
        }
        return droneById.get();
    }

    @Override
    public List<Medication> listDroneMedications(String droneId) {
        Drone drone = getDroneById(droneId);
        return drone.getMedications();
    }

    @Override
    public List<Drone> listDrones() {
        return droneRepository.findAll();
    }
}
