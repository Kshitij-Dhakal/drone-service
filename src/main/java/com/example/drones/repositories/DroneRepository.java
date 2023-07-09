package com.example.drones.repositories;

import com.example.drones.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, String> {
    Optional<Drone> findBySerialNumber(String serialNumber);

    @Query("SELECT SUM(m.weight) FROM Drone d JOIN d.medications m WHERE d.id = :droneId")
    Optional<Integer> getTotalMedicationsWeightByDroneId(@Param("droneId") String droneId);
}