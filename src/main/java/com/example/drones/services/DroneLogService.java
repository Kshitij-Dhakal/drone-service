package com.example.drones.services;

import com.example.drones.models.Drone;
import com.example.drones.models.DroneLog;
import com.example.drones.repositories.DroneLogRepository;
import com.example.drones.repositories.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroneLogService {
    private static final Logger log = LoggerFactory.getLogger(DroneLogService.class);
    private final DroneRepository droneRepository;
    private final DroneLogRepository droneLogRepository;

    public DroneLogService(DroneRepository droneRepository, DroneLogRepository droneLogRepository) {
        this.droneRepository = droneRepository;
        this.droneLogRepository = droneLogRepository;
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduleFixedDelayTask() {
        log.debug("Saving drone logs");
        List<Drone> all =
                droneRepository.findAll();
        List<DroneLog> droneLogs = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            Drone drone = all.get(i);
            DroneLog droneLog = new DroneLog();
            droneLog.setDrone(drone);
            droneLog.setDroneState(drone.getDroneState());
            droneLog.setBattery(drone.getBatteryCapacity());
            droneLog.setTimestamp(System.currentTimeMillis());
            droneLogs.add(droneLog);
        }
        droneLogRepository.saveAllAndFlush(droneLogs);
    }

    public List<DroneLog> listDroneLogs(Long cursor, Long after, Long before, int limit) {
        return droneLogRepository.findAll(cursor, after, before, Pageable.ofSize(limit));
    }

    public List<DroneLog> listDroneLogsByDroneId(String droneId, Long cursor, Long after, Long before, int limit) {
        return droneLogRepository.findByDrone(droneId,
                cursor,
                after,
                before,
                Pageable.ofSize(limit));
    }
}
