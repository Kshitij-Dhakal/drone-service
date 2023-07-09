package com.example.drones.dtos;

import java.util.List;

public class ListDroneMedicationsResponseDto {
    private List<MedicationDto> medications;

    public List<MedicationDto> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDto> medications) {
        this.medications = medications;
    }

}
