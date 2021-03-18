package ru.luifuooj.model.flying;

import java.time.LocalDate;

public class Helicopter extends FlyingVehicle {
    /**
     * Категория.
     */
    private String category;

    public Helicopter(String id, String brand, LocalDate yearOfIssue, String color, double takeoffWeight, String category) {
        super(id, brand, yearOfIssue, color, takeoffWeight);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
