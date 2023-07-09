package com.example.drones.models;

public enum DroneModel {
    DRONE_MODEL_UNKNOWN(0, "DRONE_MODEL_UNKNOWN"),
    LITTLE_WEIGHT(1, "LIGHT_WEIGHT"),
    MIDDLE_WEIGHT(2, "MIDDLE_WEIGHT"),
    CRUISE_WEIGHT(3, "CRUISE_WEIGHT"),
    HEAVY_WEIGHT(4, "HEAVY_WEIGHT");

    private final int number;
    private final String name;

    DroneModel(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static DroneModel forName(String name) {
        for (DroneModel value : DroneModel.values()) {
            if (value.getName().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return DRONE_MODEL_UNKNOWN;
    }

    public static DroneModel forNumber(int n) {
        for (DroneModel value : DroneModel.values()) {
            if (value.getNumber() == n) {
                return value;
            }
        }
        return DRONE_MODEL_UNKNOWN;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
