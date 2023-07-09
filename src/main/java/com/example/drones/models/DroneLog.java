package com.example.drones.models;

import javax.persistence.*;

@Entity
public class DroneLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;
    @Column(name = "drone_state")
    private DroneState droneState;
    @Column(name = "battery", nullable = false)
    private int battery;
    @Column(name = "timestamp")
    private Long timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public DroneState getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = droneState;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
