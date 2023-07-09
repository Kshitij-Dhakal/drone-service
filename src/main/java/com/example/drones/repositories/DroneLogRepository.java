package com.example.drones.repositories;

import com.example.drones.models.DroneLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneLogRepository extends JpaRepository<DroneLog, String> {
    @Query("select d from DroneLog d where d.drone.id = ?1 and d.id < ?2 and d.timestamp > ?2 and d.timestamp < ?3 order by d.id DESC")
    List<DroneLog> findByDrone(String droneId, Long cursor, Long after, Long before, Pageable pageable);

    @Query("select d from DroneLog d where d.id < ?1 and d.timestamp > ?2 and d.timestamp < ?3 order by d.id DESC")
    List<DroneLog> findAll(Long cursor, Long after, Long before, Pageable pageable);
}