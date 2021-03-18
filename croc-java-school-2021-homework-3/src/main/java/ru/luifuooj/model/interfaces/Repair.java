package ru.luifuooj.model.interfaces;

import java.time.LocalDate;

public interface Repair {
    LocalDate repairDay();
    void setForRepair();
    void hasRepaired();
    String isRepairedInfo();
    boolean isRepaired();
}
