package com.example.drones.dtomappers;

import com.example.drones.dtos.MedicationDto;
import com.example.drones.models.Medication;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface MedicationMapper {
    Medication medicationDtoToMedication(MedicationDto medication);

    List<Medication> medicationDtoToMedication(List<MedicationDto> medicationDtos);

    MedicationDto medicationToMedicationDto(Medication medication);

    List<MedicationDto> medicationToMedicationDto(List<Medication> medications);

}
