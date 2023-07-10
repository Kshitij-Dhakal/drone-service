package com.example.drones.services;

import com.example.drones.errors.FailedException;
import com.example.drones.errors.NotFoundException;
import com.example.drones.errors.ValidationException;
import com.example.drones.models.Drone;
import com.example.drones.models.DroneModel;
import com.example.drones.models.DroneState;
import com.example.drones.models.Medication;
import com.example.drones.repositories.DroneRepository;
import com.example.drones.repositories.MedicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DroneServiceImpl implements DroneService {
    private static final Logger log = LoggerFactory.getLogger(DroneServiceImpl.class);
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
        log.info("Registering drone. Serial number : {}", drone.getSerialNumber());
        drone.setId(UUID.randomUUID().toString());
        if (drone.getDroneModel() == DroneModel.DRONE_MODEL_UNKNOWN) {
            throw new ValidationException("Invalid drone model");
        }
        if (drone.getDroneState() == DroneState.DRONE_STATE_UNKNOWN) {
            drone.setDroneState(DroneState.DRONE_STATE_IDLE);
        }
        return droneRepository.save(drone);
    }

    @Override
    @Transactional
    public void loadMedications(String droneId, List<Medication> medications) {
        log.info("Loading medications. Drone id : {}", droneId);
        Drone drone = getDroneById(droneId);
        if (drone.getDroneState() != DroneState.DRONE_STATE_IDLE) {
            throw new FailedException("Drone is not in idle state.");
        }
        if (drone.getBatteryCapacity() < 25) {
            throw new FailedException("Drone's battery capacity is less than 25%.");
        }
        int weightLimit = drone.getWeightLimit();
        int currentWeight = 0;
        for (Medication medication : medications) {
            medication.setId(UUID.randomUUID().toString());
            currentWeight += medication.getWeight();
            if (currentWeight > weightLimit) {
                throw new FailedException("Medication's weight is greater that drone's carrying capacity.");
            }
        }
        medications = medicationRepository.saveAll(medications);
        drone.setMedications(medications);
        drone.setDroneState(DroneState.DRONE_STATE_LOADING);
    }

    @Override
    public Drone getDroneById(String droneId) {
        log.info("Getting drone by id : {}", droneId);
        Optional<Drone> droneById = droneRepository.findById(droneId);
        if (droneById.isEmpty()) {
            throw new NotFoundException("Drone not found for given id");
        }
        return droneById.get();
    }

    @Override
    public List<Medication> listDroneMedications(String droneId) {
        log.info("List drone medications for drone id : {}", droneId);
        Drone drone = getDroneById(droneId);
        return drone.getMedications();
    }

    @Override
    public List<Drone> listDrones() {
        log.info("List all drones");
        return droneRepository.findAll();
    }

    @Override
    public List<Drone> listAvailableDrones() {
        log.info("List available drones");
        return droneRepository.findByDroneStateAndBatteryCapacityGreaterThanEqual(DroneState.DRONE_STATE_IDLE, 25);
    }

    @Override
    @Transactional
    public Drone updateDroneState(String droneId, DroneState droneState) {
        log.info("Update drone state. Drone id : {}", droneId);
        if (droneState == DroneState.DRONE_STATE_UNKNOWN) {
            throw new ValidationException("Invalid drone state");
        }
        Drone droneById = getDroneById(droneId);
        droneById.setDroneState(droneState);
        return droneById;
    }

    @Override
    @Transactional
    public Drone updateDroneBattery(
            String droneId,
            @Min(0)
            @Max(100) int batteryCapacity) {
        log.info("Update drone battery. Drone id : {}", droneId);
        Drone droneById = getDroneById(droneId);
        droneById.setBatteryCapacity(batteryCapacity);
        return droneById;
    }
}
