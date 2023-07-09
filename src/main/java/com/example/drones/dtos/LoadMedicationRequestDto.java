package com.example.drones.dtos;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class LoadMedicationRequestDto {
    @NotEmpty
    private List<MedicationDto> medications = new ArrayList<>();

    public List<MedicationDto> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDto> medications) {
        this.medications = medications;
    }
}
