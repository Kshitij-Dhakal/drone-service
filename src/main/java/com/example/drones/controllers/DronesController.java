package com.example.drones.controllers;

import com.example.drones.dtomappers.DroneMapper;
import com.example.drones.dtomappers.MedicationMapper;
import com.example.drones.dtos.*;
import com.example.drones.models.Drone;
import com.example.drones.models.Medication;
import com.example.drones.services.DroneService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DronesController {
    private final DroneMapper droneMapper;
    private final DroneService droneService;
    private final MedicationMapper medicationMapper;

    public DronesController(
            DroneMapper droneMapper,
            DroneService droneService,
            MedicationMapper medicationMapper) {
        this.droneMapper = droneMapper;
        this.droneService = droneService;
        this.medicationMapper = medicationMapper;
    }

    @PostMapping("/v1/drone")
    public DroneDto registerDrone(@Valid @RequestBody DroneDto droneDto) {
        Drone drone = droneMapper.droneDtoToDrone(droneDto);
        Drone registeredDrone = droneService.registerDrone(drone);
        return droneMapper.droneToDroneDto(registeredDrone);
    }

    @GetMapping("/v1/drones")
    public ListDronesResponseDto listDrones() {
        List<Drone> registeredDrones = droneService.listDrones();
        ListDronesResponseDto listDronesResponseDto = new ListDronesResponseDto();
        listDronesResponseDto.setDrones(droneMapper.droneToDroneDto(registeredDrones));
        return listDronesResponseDto;
    }

    @PostMapping("/v1/drone/{drone-id}/load")
    public LoadDroneMedicationResponseDto loadMedication(
            @PathVariable(name = "drone-id") String droneId,
            @Valid @RequestBody LoadMedicationRequestDto loadMedicationRequestDto) {
        List<Medication> medications = medicationMapper.medicationDtoToMedication(loadMedicationRequestDto.getMedications());
        droneService.loadMedications(droneId, medications);
        LoadDroneMedicationResponseDto loadDroneMedicationResponseDto = new LoadDroneMedicationResponseDto();
        loadDroneMedicationResponseDto.setSuccess(true);
        return loadDroneMedicationResponseDto;
    }

    @GetMapping("/v1/drone/{drone-id}/medications")
    public ListDroneMedicationsResponseDto listDroneMedications(@PathVariable(name = "drone-id") String droneId) {
        List<Medication> medications = droneService.listDroneMedications(droneId);
        ListDroneMedicationsResponseDto listDroneMedicationsResponseDto = new ListDroneMedicationsResponseDto();
        listDroneMedicationsResponseDto.setMedications(medicationMapper.medicationToMedicationDto(medications));
        return listDroneMedicationsResponseDto;
    }
}
