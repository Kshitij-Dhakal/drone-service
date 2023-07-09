package com.example.drones.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medication {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "image", length = 2000)
    private String image;

    @Column(name = "code")
    private String code;

    @Column(name = "weight")
    private int weight;

    @Column(name = "name")
    private String name;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
