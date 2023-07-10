package com.example.drones.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroneStateTest {

    @Test
    void testForName() {
        assertEquals(DroneState.DRONE_STATE_IDLE, DroneState.forName("DRONE_STATE_IDLE"));
        assertEquals(DroneState.DRONE_STATE_UNKNOWN, DroneState.forName("DRONE_STATE_UNKNOWN"));
    }

    @Test
    void testForNumber() {
        assertEquals(DroneState.DRONE_STATE_IDLE, DroneState.forNumber(1));
        assertEquals(DroneState.DRONE_STATE_UNKNOWN, DroneState.forNumber(999));
    }

    @Test
    void testNumber() {
        assertEquals(1, DroneState.DRONE_STATE_IDLE.getNumber());
        assertEquals(0, DroneState.DRONE_STATE_UNKNOWN.getNumber());
    }

    @Test
    void testName() {
        assertEquals("DRONE_STATE_IDLE", DroneState.DRONE_STATE_IDLE.getName());
        assertEquals("DRONE_STATE_UNKNOWN", DroneState.DRONE_STATE_UNKNOWN.getName());
    }
}