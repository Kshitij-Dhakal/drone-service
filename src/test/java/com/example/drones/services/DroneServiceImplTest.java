package com.example.drones.services;

import com.example.drones.errors.FailedException;
import com.example.drones.models.Drone;
import com.example.drones.models.DroneState;
import com.example.drones.models.Medication;
import com.example.drones.repositories.DroneRepository;
import com.example.drones.repositories.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class DroneServiceImplTest {

    private DroneServiceImpl droneService;
    private DroneRepository droneRepository;
    private MedicationRepository medicationRepository;

    @BeforeEach
    void setUp() {
        droneRepository = Mockito.mock(DroneRepository.class);
        medicationRepository = Mockito.mock(MedicationRepository.class);
        droneService = new DroneServiceImpl(droneRepository, medicationRepository);
    }

    @Test
    void testRegisterDrone() {
        Drone drone = new Drone();
        drone.setId(UUID.randomUUID().toString());
        Mockito.when(droneRepository.save(drone)).thenReturn(drone);
        Drone savedDrone = droneService.registerDrone(drone);
        assertEquals(drone.getId(), savedDrone.getId());
    }

    @Test
    void testLoadMedications() {
        String droneId = "12345";
        List<Medication> medications = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Medication medication = new Medication();
            medication.setId(UUID.randomUUID().toString());
            medication.setName("Medication " + i);
            medication.setWeight(i);
            medications.add(medication);
        }
        Drone drone = new Drone();
        drone.setDroneState(DroneState.DRONE_STATE_IDLE);
        drone.setBatteryCapacity(90);
        drone.setWeightLimit(500);

        Mockito.when(droneRepository.findById(droneId)).thenReturn(Optional.of(drone));
        Mockito.when(medicationRepository.saveAll(medications)).thenReturn(medications);
        droneService.loadMedications(droneId, medications);
        Mockito.verify(droneRepository, Mockito.times(1)).findById(droneId);
        Mockito.verify(medicationRepository, Mockito.times(1)).saveAll(medications);
    }

    @Test
    void testLoadMedicationsWhenDroneIsNotInIdleState() {
        String droneId = "12345";
        List<Medication> medications = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Medication medication = new Medication();
            medication.setId(UUID.randomUUID().toString());
            medication.setName("Medication " + i);
            medication.setWeight(i);
            medications.add(medication);
        }
        Mockito.when(droneRepository.findById(droneId)).thenReturn(Optional.of(new Drone()));
        Mockito.when(medicationRepository.saveAll(medications)).thenReturn(medications);
        try {
            droneService.loadMedications(droneId, medications);
        } catch (FailedException e) {
            assertEquals("Drone is not in idle state.", e.getMessage());
        }
        Mockito.verify(droneRepository, Mockito.times(1)).findById(droneId);
        Mockito.verify(medicationRepository, Mockito.never()).saveAll(medications);
    }

    @Test
    void testGetDroneById() {
        String droneId = "12345";
        Drone drone = new Drone();
        drone.setId(droneId);
        Mockito.when(droneRepository.findById(droneId)).thenReturn(Optional.of(drone));
        Drone foundDrone = droneService.getDroneById(droneId);
        assertEquals(drone, foundDrone);
        Mockito.verify(droneRepository).findById(droneId);
    }

    @Test
    void testListDroneMedications() {
        String droneId = "12345";
        List<Medication> medications = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Medication medication = new Medication();
            medication.setId(UUID.randomUUID().toString());
            medication.setName("Medication " + i);
            medication.setWeight(i);
            medications.add(medication);
        }
        Drone drone = new Drone();
        drone.setId(droneId);
        drone.setMedications(medications);

        Mockito.when(droneRepository.findById(droneId)).thenReturn(Optional.of(drone));

        List<Medication> meds = droneService.listDroneMedications(droneId);
        Mockito.verify(droneRepository, Mockito.times(1)).findById(droneId);

        assertEquals(drone.getMedications(), meds);
    }

    @Test
    void listDrones() {
        Mockito.when(droneRepository.findAll()).thenReturn(List.of(new Drone()));

        List<Drone> drones = droneService.listDrones();

        Mockito.verify(droneRepository).findAll();
        assertEquals(1, drones.size());
    }

    @Test
    void listAvailableDrones() {
        Mockito.when(droneRepository.findByDroneStateAndBatteryCapacityGreaterThanEqual(DroneState.DRONE_STATE_IDLE,
                        25))
                .thenReturn(List.of(new Drone()));

        List<Drone> drones = droneService.listAvailableDrones();

        Mockito.verify(droneRepository)
                .findByDroneStateAndBatteryCapacityGreaterThanEqual(DroneState.DRONE_STATE_IDLE, 25);
        assertEquals(1, drones.size());
    }

    @Test
    void updateDroneState() {
        Mockito.when(droneRepository.findById(anyString())).thenReturn(Optional.of(new Drone()));

        droneService.updateDroneState("droneId", DroneState.DRONE_STATE_IDLE);

        Mockito.verify(droneRepository).findById("droneId");
    }

    @Test
    void updateDroneBattery() {
        Mockito.when(droneRepository.findById(anyString())).thenReturn(Optional.of(new Drone()));

        droneService.updateDroneBattery("droneId", 50);

        Mockito.verify(droneRepository).findById("droneId");
    }
}