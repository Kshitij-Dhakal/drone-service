package com.example.drones.dtomappers;

import com.example.drones.dtos.DroneDto;
import com.example.drones.models.Drone;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface DroneMapper {
    Drone droneDtoToDrone(DroneDto drone);

    DroneDto droneToDroneDto(Drone drone);

    List<DroneDto> droneToDroneDto(List<Drone> drones);
}
