package ru.luifuooj.аnimals;

import java.lang.*;
import java.time.LocalDate;


/**
 * Животное
 */
public class Animal {
    private String type;
    private String name;
    private LocalDate dateOfBirth;
    private boolean isSick = false;


    /**
     * Создаем запись о животном
     * @param type вид животного
     * @param name имя животного
     * @param dateOfBirth дата рождения/приобретения животного
     * */
    public Animal(String type, String name, LocalDate dateOfBirth) {
        this.type = type;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * Состояние здоровья животного
     * */
    public boolean isSick() {
        return isSick;

    }

    /**
     * Изменение состояния животного
     * */
    public void setSick(boolean sick) {
        this.isSick = sick;
    }

    /**
     * Смена имени животного
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает тип животного
     * @return текст
     * */
    public String getType() {
        return type;
    }

    /**
     * Возвращает имя животного
     * @return текст
     * */
    public String getName() {
        return name;
    }

    /**
     * Возвращает дату рождения/приобретения животного
     * @return дата
     * */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}

