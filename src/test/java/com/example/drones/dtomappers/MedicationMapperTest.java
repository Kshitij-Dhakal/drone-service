package com.example.drones.dtomappers;

import com.example.drones.dtos.MedicationDto;
import com.example.drones.models.Medication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MedicationMapperTest {
    @Autowired
    private MedicationMapper medicationMapper;

    @Test
    void testMedicationDtoToMedication() {
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setName(UUID.randomUUID().toString());
        medicationDto.setWeight(ThreadLocalRandom.current().nextInt(0, 500));
        medicationDto.setCode(UUID.randomUUID().toString());
        medicationDto.setImage(UUID.randomUUID().toString());

        Medication medication = medicationMapper.medicationDtoToMedication(medicationDto);

        assertEquals(medicationDto.getName(), medication.getName());
        assertEquals(medicationDto.getWeight(), medication.getWeight());
        assertEquals(medicationDto.getCode(), medication.getCode());
        assertEquals(medicationDto.getImage(), medication.getImage());
    }

    @Test
    void testMedicationToMedicationDto() {
        Medication medication = new Medication();
        medication.setName(UUID.randomUUID().toString());
        medication.setWeight(ThreadLocalRandom.current().nextInt(0, 500));
        medication.setCode(UUID.randomUUID().toString());
        medication.setImage(UUID.randomUUID().toString());

        MedicationDto medicationDto = medicationMapper.medicationToMedicationDto(medication);

        assertEquals(medication.getName(), medicationDto.getName());
        assertEquals(medication.getWeight(), medicationDto.getWeight());
        assertEquals(medication.getCode(), medicationDto.getCode());
        assertEquals(medication.getImage(), medicationDto.getImage());
    }

    @Test
    void testMedicationDtoToMedicationList() {
        List<MedicationDto> medicationDtos = new ArrayList<>();
        medicationDtos.add(new MedicationDto());
        medicationDtos.add(new MedicationDto());

        List<Medication> medications = medicationMapper.medicationDtoToMedication(medicationDtos);

        assertEquals(2, medications.size());
    }

    @Test
    void testMedicationToMedicationDtoList() {
        List<Medication> medications = new ArrayList<>();
        medications.add(new Medication());
        medications.add(new Medication());

        List<MedicationDto> medicationDtos = medicationMapper.medicationToMedicationDto(medications);

        assertEquals(2, medicationDtos.size());
    }
}