package com.example.drones.models;

public enum DroneState {
    DRONE_STATE_UNKNOWN(0, "DRONE_STATE_UNKNOWN"),
    DRONE_STATE_IDLE(1, "DRONE_STATE_IDLE"),
    DRONE_STATE_LOADING(2, "DRONE_STATE_LOADING"),
    DRONE_STATE_LOADED(3, "DRONE_STATE_LOADED"),
    DRONE_STATE_DELIVERING(4, "DRONE_STATE_DELIVERING"),
    DRONE_STATE_DELIVERED(5, "DRONE_STATE_DELIVERED"),
    DRONE_STATE_RETURNING(6, "DRONE_STATE_RETURNING"),
    ;
    private final int number;
    private final String name;

    DroneState(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static DroneState forName(String name) {
        for (DroneState value : DroneState.values()) {
            if (value.getName().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return DRONE_STATE_UNKNOWN;
    }

    public static DroneState forNumber(int n) {
        for (DroneState value : DroneState.values()) {
            if (value.getNumber() == n) {
                return value;
            }
        }
        return DRONE_STATE_UNKNOWN;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
