package ru.luifuooj.model.flying;

import java.time.LocalDate;

public class Airplane extends FlyingVehicle {
    /**
     * Скорость полета.
     */
    private double maxSpeed;

    public Airplane(String id, String brand, LocalDate yearOfIssue, String color, double takeoffWeight, double speed) {
        super(id, brand, yearOfIssue, color, takeoffWeight);
        this.maxSpeed = speed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
