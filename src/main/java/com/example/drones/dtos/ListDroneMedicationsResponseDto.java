package com.example.drones.dtos;

import java.util.List;

public class ListDroneMedicationsResponseDto {
    public List<MedicationDto> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDto> medications) {
        this.medications = medications;
    }

    private List<MedicationDto> medications;

}
