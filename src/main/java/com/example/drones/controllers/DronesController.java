package com.example.drones.controllers;

import com.example.drones.dtomappers.DroneMapper;
import com.example.drones.dtos.DroneDto;
import com.example.drones.models.Drone;
import com.example.drones.services.DroneService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DronesController {
    private final DroneMapper droneMapper;
    private final DroneService droneService;

    public DronesController(DroneMapper droneMapper, DroneService droneService) {
        this.droneMapper = droneMapper;
        this.droneService = droneService;
    }

    @PostMapping("/v1/drone")
    public DroneDto registerDrone(@Valid @RequestBody DroneDto droneDto) {
        Drone drone = droneMapper.droneDtoToDrone(droneDto);
        Drone registeredDrone = droneService.registerDrone(drone);
        return droneMapper.droneToDroneDto(registeredDrone);
    }
}
