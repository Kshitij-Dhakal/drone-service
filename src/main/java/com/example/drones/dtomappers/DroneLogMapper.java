package com.example.drones.dtomappers;

import com.example.drones.dtos.DroneLogDto;
import com.example.drones.models.DroneLog;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface DroneLogMapper {
    @Mapping(source = "drone.id", target = "droneId")
    @Mapping(source = "drone.serialNumber", target = "serialNumber")
    DroneLogDto droneLogToDroneLogDto(DroneLog droneLog);


    List<DroneLogDto> droneLogsToDroneLogDtos(List<DroneLog> droneLogs);
}
