package ru.luifuooj.model.motor;

import ru.luifuooj.model.Vehicle;

import java.time.LocalDate;

public class MotorVehicle extends Vehicle {
    /**
     * Тип двигателя.
     */
    private String engineType;

    public MotorVehicle(String id, String brand, LocalDate yearOfIssue, String color, String engineType) {
        super(id, brand, yearOfIssue, color);
        this.engineType = engineType;
    }

    @Override
    public String isRepairedInfo() {
        return "Автотранспортное средство исправно: " + isRepaired;
    }

    public String getEngineType() {
        return engineType;
    }

}
