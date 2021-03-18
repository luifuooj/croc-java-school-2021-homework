package ru.luifuooj.model.flying;

import ru.luifuooj.model.Vehicle;

import java.time.LocalDate;

public class FlyingVehicle extends Vehicle {
    /**
     * Взлетная масса.
     */
    private double takeoffWeight;

    public FlyingVehicle(String id, String brand, LocalDate yearOfIssue,
                         String color, double takeoffWeight) {
        super(id, brand, yearOfIssue, color);
        this.takeoffWeight = takeoffWeight;
    }

    /**
     * Статус летательного средства.
     * @return true - исправно
     */
    @Override
    public String isRepairedInfo() {
        return "Летательное транспортное средство исправно: " + isRepaired;
    }

    public double getTakeoffWeight() {
        return takeoffWeight;
    }
}
