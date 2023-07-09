package com.example.drones.modelconverters;

import com.example.drones.models.DroneState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DroneStateConverter implements AttributeConverter<DroneState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DroneState droneState) {
        return droneState.getNumber();
    }

    @Override
    public DroneState convertToEntityAttribute(Integer i) {
        return DroneState.forNumber(i);
    }
}
