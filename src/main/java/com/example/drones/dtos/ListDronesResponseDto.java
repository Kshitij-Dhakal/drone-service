package com.example.drones.dtos;

import java.util.List;

public class ListDronesResponseDto {
    private List<DroneDto> drones;

    public List<DroneDto> getDrones() {
        return drones;
    }

    public void setDrones(List<DroneDto> drones) {
        this.drones = drones;
    }
}
