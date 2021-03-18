package ru.luifuooj.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.luifuooj.model.flying.FlyingVehicle;
import ru.luifuooj.model.motor.MotorVehicle;
import ru.luifuooj.model.motor.Truck;
import ru.luifuooj.model.personalmobility.KickScooter;
import ru.luifuooj.model.personalmobility.PersonalMobilityVehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepairTest {
    List<Vehicle> vehicles;

    @BeforeEach
    public void setup() {
        vehicles = new ArrayList<>();
        vehicles.add(new FlyingVehicle("A1", "GoodAirplane", LocalDate.of(2010, 1, 1), "White", 10.8));
        vehicles.add(new Truck("F1", "GoodCar", LocalDate.of(2001, 1, 1), "Black", "Engine1", 8));
        vehicles.add(new KickScooter("K1", "GoodScooter", LocalDate.of(2005, 5, 5),"Pink", 2, true));
    }

    @Test
    public void testSetForRepair() {
        for (Vehicle vehicle : vehicles) {
            vehicle.setForRepair();
            Assertions.assertFalse(vehicle.isRepaired());
        }
    }

    @Test
    public void hasRepaired() {
        for (Vehicle vehicle : vehicles) {
            vehicle.setForRepair();
            Assertions.assertFalse(vehicle.isRepaired());
            vehicle.hasRepaired();
            Assertions.assertTrue(vehicle.isRepaired());
        }
    }

    @Test
    public void repairDay() {
        for (Vehicle vehicle : vehicles) {
            vehicle.setForRepair();
            Assertions.assertEquals(LocalDate.now(), vehicle.repairDay());
        }
    }

    @Test
    public void isRepairedInfo() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof PersonalMobilityVehicle) {
                Assertions.assertEquals("Средство индивидуальной мобильности исправно: " + vehicle.isRepaired(), vehicle.isRepairedInfo());
            }
            if (vehicle instanceof MotorVehicle) {
                Assertions.assertEquals("Автотранспортное средство исправно: " + vehicle.isRepaired(), vehicle.isRepairedInfo());
            }
            if (vehicle instanceof FlyingVehicle) {
                Assertions.assertEquals("Летательное транспортное средство исправно: " + vehicle.isRepaired(), vehicle.isRepairedInfo());
            }
        }
    }
}

