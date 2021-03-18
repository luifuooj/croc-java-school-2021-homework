package ru.luifuooj.model;

import ru.luifuooj.model.interfaces.HasRented;
import ru.luifuooj.model.interfaces.Repair;

import java.time.LocalDate;

/**
 * Транспорт.
 */
public abstract class Vehicle implements HasRented, Repair {

    /**
     * Идентификационный номер.
     */
    private String id;

    /**
     * Марка.
     */
    private String brand;

    /**
     * Год выпуска.
     */
    private LocalDate yearOfIssue = LocalDate.now();

    /**
     * Цвет.
     */
    private String color;

    /**
     * Находится ли в аренде.
     */
    private boolean isRented;

    /**
     * Дата аренды.
     */
    private LocalDate rentDay;

    /**
     * Дата направления в ремонт.
     */
    protected LocalDate repairDay;

    /**
     * Исправно ли средство.
     */
    protected boolean isRepaired = true;

    public Vehicle(String id, String brand, LocalDate yearOfIssue, String color) {
        this.id = id;
        this.brand = brand;
        this.yearOfIssue = yearOfIssue;
        this.color = color;
    }

    /**
     * Статус аренды.
     *
     * @return true - арендовано
     */
    @Override
    public boolean isRented() {
        return isRented;
    }

    /**
     * Средство исправно.
     *
     * @return true - исправно
     */
    @Override
    public boolean isRepaired() {
        return isRepaired;
    }

    /**
     * Сдать в аренду.
     */
    @Override
    public void getRent() {
        rentDay = LocalDate.now();
        isRented = true;
    }

    /**
     * Отправить средство в ремонт.
     */
    @Override
    public void setForRepair() {
        repairDay = LocalDate.now();
        isRepaired = false;
    }

    /**
     * Дата направления средства в ремонт.
     *
     * @return дата
     */
    @Override
    public LocalDate repairDay() {
        return repairDay;
    }

    /**
     * Завершить ремонт средства.
     */
    @Override
    public void hasRepaired() {
        isRepaired = true;
    }

    /**
     * Снять транспортное средство с аренды.
     */
    @Override
    public void returnRent() {
        isRented = false;
    }

    /**
     * Дата сдачи в аренду.
     *
     * @return дата
     */
    @Override
    public LocalDate getRentDay() {
        return rentDay;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public LocalDate getYearOfIssue() {
        return yearOfIssue;
    }
}
