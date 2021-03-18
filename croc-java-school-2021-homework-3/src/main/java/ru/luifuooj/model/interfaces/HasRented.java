package ru.luifuooj.model.interfaces;

import java.time.LocalDate;

public interface HasRented {
    void getRent();
    LocalDate getRentDay();
    void returnRent();
    boolean isRented();
}
