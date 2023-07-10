package com.example.drones.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroneModelTest {

    @Test
    void testForName() {
        assertEquals(DroneModel.LITTLE_WEIGHT, DroneModel.forName("light_weight"));
        assertEquals(DroneModel.DRONE_MODEL_UNKNOWN, DroneModel.forName("unknown"));
    }

    @Test
    void testForNumber() {
        assertEquals(DroneModel.LITTLE_WEIGHT, DroneModel.forNumber(1));
        assertEquals(DroneModel.DRONE_MODEL_UNKNOWN, DroneModel.forNumber(999));
    }

    @Test
    void testNumber() {
        assertEquals(1, DroneModel.LITTLE_WEIGHT.getNumber());
        assertEquals(0, DroneModel.DRONE_MODEL_UNKNOWN.getNumber());
    }

    @Test
    void testName() {
        assertEquals("LIGHT_WEIGHT", DroneModel.LITTLE_WEIGHT.getName());
        assertEquals("DRONE_MODEL_UNKNOWN", DroneModel.DRONE_MODEL_UNKNOWN.getName());
    }
}