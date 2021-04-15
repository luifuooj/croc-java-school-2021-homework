package ru.luifuooj.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Модель рыбы.
 */
public class Fish {
    private Integer id;
    private final String name;
    private final LocalDate importDate;
    private final LocalTime importTime;
    private Integer quantity;
    private Boolean isRare;

    public Fish(String name, LocalDate importDate, LocalTime importTime, Integer quantity, Boolean isRare) {
        this.name = name;
        this.importDate = importDate;
        this.importTime = importTime;
        this.quantity = quantity;
        this.isRare = isRare;
    }

    public Fish(Integer id, String name, LocalDate importDate, LocalTime importTime, Integer quantity, Boolean isRare) {
        this.id = id;
        this.name = name;
        this.importDate = importDate;
        this.importTime = importTime;
        this.quantity = quantity;
        this.isRare = isRare;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public LocalTime getImportTime() {
        return importTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean isRare() {
        return isRare;
    }

    public void setRare(Boolean rare) {
        isRare = rare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return name.equals(fish.name) &&
                importDate.equals(fish.importDate) &&
                importTime.equals(fish.importTime) &&
                quantity.equals(fish.quantity) &&
                isRare.equals(fish.isRare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, importDate, importTime, quantity, isRare);
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", importDate=" + importDate +
                ", importTime=" + importTime +
                ", quantity=" + quantity +
                ", isRare=" + isRare +
                '}';
    }
}
