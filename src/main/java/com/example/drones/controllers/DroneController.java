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
public class DroneController {
    private final DroneMapper droneMapper;
    private final DroneService droneService;
    private final MedicationMapper medicationMapper;

    public DroneController(
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

    @GetMapping("/v1/drone/{drone-id}")
    public DroneDto getDroneById(@PathVariable("drone-id") String droneId) {
        Drone registeredDrone = droneService.getDroneById(droneId);
        return droneMapper.droneToDroneDto(registeredDrone);
    }

    @GetMapping("/v1/drones")
    public ListDronesResponseDto listDrones() {
        List<Drone> registeredDrones = droneService.listDrones();
        ListDronesResponseDto listDronesResponseDto = new ListDronesResponseDto();
        listDronesResponseDto.setDrones(droneMapper.droneToDroneDto(registeredDrones));
        return listDronesResponseDto;
    }

    @GetMapping("/v1/available-drones")
    public ListDronesResponseDto listAvailableDrones() {
        List<Drone> registeredDrones = droneService.listAvailableDrones();
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

    @PatchMapping("/v1/drone/{drone-id}/state")
    public DroneDto updateDroneState(
            @PathVariable("drone-id") String droneId,
            @RequestBody DroneDto droneDto) {
        Drone drone = droneService.updateDroneState(droneId, droneDto.getDroneState());
        return droneMapper.droneToDroneDto(drone);
    }

    @PatchMapping("/v1/drone/{drone-id}/battery")
    public DroneDto updateDroneBattery(
            @PathVariable("drone-id") String droneId,
            @RequestBody DroneDto droneDto) {
        Drone drone = droneService.updateDroneBattery(droneId, droneDto.getBatteryCapacity());
        return droneMapper.droneToDroneDto(drone);
    }
}
