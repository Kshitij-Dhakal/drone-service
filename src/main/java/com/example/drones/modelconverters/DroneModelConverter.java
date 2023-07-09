package com.example.drones.modelconverters;

import com.example.drones.models.DroneModel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DroneModelConverter implements AttributeConverter<DroneModel, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DroneModel droneModel) {
        return droneModel.getNumber();
    }

    @Override
    public DroneModel convertToEntityAttribute(Integer i) {
        return DroneModel.forNumber(i);
    }
}
