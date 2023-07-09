package com.example.drones.dtos;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MedicationDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
    private String name;
    @Min(0)
    @Max(500)
    private int weight;
    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
