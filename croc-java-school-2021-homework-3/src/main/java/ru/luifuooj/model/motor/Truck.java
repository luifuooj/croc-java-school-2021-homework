package ru.luifuooj.model.motor;

import java.time.LocalDate;

public class Truck extends MotorVehicle {
    /**
     * Размер.
     */
    private Integer size;

    public Truck(String id, String brand, LocalDate yearOfIssue, String color, String engineType, Integer size) {
        super(id, brand, yearOfIssue, color, engineType);
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }
}
