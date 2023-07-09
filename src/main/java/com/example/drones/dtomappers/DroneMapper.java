package com.example.drones.dtomappers;

import com.example.drones.dtos.DroneDto;
import com.example.drones.models.Drone;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface DroneMapper {
    DroneMapper INSTANCE = Mappers.getMapper(DroneMapper.class);

    Drone droneDtoToDrone(DroneDto drone);

    DroneDto droneToDroneDto(Drone drone);
}
