package ru.luifuooj.model.personalmobility;

import java.time.LocalDate;

public class KickScooter extends PersonalMobilityVehicle {
    /**
     * Складной механизм.
     */
    private boolean isFoldingMechanism;

    public KickScooter(String id, String brand, LocalDate yearOfIssue, String color, Integer numberOfWheels, boolean isFoldingMechanism) {
        super(id, brand, yearOfIssue, color, numberOfWheels);
        this.isFoldingMechanism = isFoldingMechanism;
    }

    public boolean isFoldingMechanism() {
        return isFoldingMechanism;
    }
}

