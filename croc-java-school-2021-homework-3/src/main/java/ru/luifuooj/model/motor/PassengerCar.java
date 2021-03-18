package ru.luifuooj.model.motor;

import java.time.LocalDate;

public class PassengerCar extends MotorVehicle {
    /**
     * Класс автомобиля.
     */
    private String carClass;

    public PassengerCar(String id, String brand, LocalDate yearOfIssue, String color, String engineType, String carClass) {
        super(id, brand, yearOfIssue, color, engineType);
        this.carClass = carClass;
    }

    public String getCarClass() {
        return carClass;
    }
}
