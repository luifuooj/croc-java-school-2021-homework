package ru.luifuooj.model.personalmobility;

import ru.luifuooj.model.Vehicle;

import java.time.LocalDate;

public class PersonalMobilityVehicle extends Vehicle {
    /**
     * Количество колес.
     */
    private Integer numberOfWheels;

    public PersonalMobilityVehicle(String id, String brand, LocalDate yearOfIssue, String color,
                                   Integer numberOfWheels) {
        super(id, brand, yearOfIssue, color);
        this.numberOfWheels = numberOfWheels;
    }

    /**
     * Статус средства индивидуальной мобильности.
     * @return true - исправно
     */
    @Override
    public String isRepairedInfo() {
        return "Средство индивидуальной мобильности исправно: " + isRepaired;
    }

    public Integer getNumberOfWheels() {
        return numberOfWheels;
    }
}
