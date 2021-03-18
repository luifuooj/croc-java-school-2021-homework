package ru.luifuooj.model.personalmobility;

import java.time.LocalDate;

public class Bicycle extends PersonalMobilityVehicle {
    /**
     * Тип.
     */
    private String types;

    public Bicycle(String id, String brand, LocalDate yearOfIssue, String color, Integer numberOfWheels, String types) {
        super(id, brand, yearOfIssue, color, numberOfWheels);
        this.types = types;
    }

    public String getTypes() {
        return types;
    }
}
