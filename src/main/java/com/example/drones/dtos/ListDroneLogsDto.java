package com.example.drones.dtos;

import java.util.List;

public class ListDroneLogsDto {
    List<DroneLogDto> droneLogs;

    public List<DroneLogDto> getDroneLogs() {
        return droneLogs;
    }

    public void setDroneLogs(List<DroneLogDto> droneLogs) {
        this.droneLogs = droneLogs;
    }
}
