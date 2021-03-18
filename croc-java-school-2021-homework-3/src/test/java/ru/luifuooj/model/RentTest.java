package ru.luifuooj.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.luifuooj.model.personalmobility.Bicycle;

import java.time.LocalDate;

public class RentTest {
    private Vehicle bicycle;

    @BeforeEach
    public void setup() {
        bicycle = new Bicycle("S1", "GoodBicycle", LocalDate.of(1995, 3, 3), "Blue", 4, "MOUNTAIN");
    }

    @Test
    public void testGetRent() {
        bicycle.getRent();
        Assertions.assertTrue(bicycle.isRented());
    }

    @Test
    public void testGetRentDay() {
        bicycle.getRent();
        Assertions.assertEquals(LocalDate.now(), bicycle.getRentDay());
    }

    @Test
    public void testReturnRent() {
        bicycle.getRent();
        Assertions.assertTrue(bicycle.isRented());
        bicycle.returnRent();
        Assertions.assertFalse(bicycle.isRented());
    }
}
