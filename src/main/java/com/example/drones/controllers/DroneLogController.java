package com.example.drones.controllers;

import com.example.drones.dtomappers.DroneLogMapper;
import com.example.drones.dtos.ListDroneLogsDto;
import com.example.drones.models.DroneLog;
import com.example.drones.services.DroneLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DroneLogController {
    private final DroneLogMapper droneLogMapper;
    private final DroneLogService droneLogService;

    public DroneLogController(
            DroneLogMapper droneLogMapper,
            DroneLogService droneLogService) {
        this.droneLogMapper = droneLogMapper;
        this.droneLogService = droneLogService;
    }

    @GetMapping("/v1/logs")
    public ListDroneLogsDto listDroneLogs(
            @RequestParam(value = "cursor", required = false) Long cursor,
            @RequestParam(value = "after", required = false) Long after,
            @RequestParam(value = "before", required = false) Long before,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        if (cursor == null) {
            cursor = Long.MAX_VALUE;
        }
        if (after == null) {
            after = 0L;
        }
        if (before == null) {
            before = System.currentTimeMillis();
        }
        List<DroneLog> droneLogs = droneLogService.listDroneLogs(cursor, after, before, limit);
        ListDroneLogsDto listDroneLogsDto = new ListDroneLogsDto();
        listDroneLogsDto.setDroneLogs(droneLogMapper.droneLogsToDroneLogDtos(droneLogs));
        return listDroneLogsDto;
    }

    @GetMapping("/v1/drone/{drone-id}/logs")
    public ListDroneLogsDto listDroneLogsByDroneId(
            @PathVariable("drone-id") String droneId,
            @RequestParam(value = "cursor", required = false) Long cursor,
            @RequestParam(value = "after", required = false) Long after,
            @RequestParam(value = "before", required = false) Long before,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        if (cursor == null) {
            cursor = Long.MAX_VALUE;
        }
        if (after == null) {
            after = 0L;
        }
        if (before == null) {
            before = System.currentTimeMillis();
        }
        List<DroneLog> droneLogs = droneLogService.listDroneLogsByDroneId(droneId, cursor, after, before, limit);
        ListDroneLogsDto listDroneLogsDto = new ListDroneLogsDto();
        listDroneLogsDto.setDroneLogs(droneLogMapper.droneLogsToDroneLogDtos(droneLogs));
        return listDroneLogsDto;
    }
}
