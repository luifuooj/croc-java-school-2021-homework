package ru.luifuooj.аnimals;

import java.util.ArrayList;
import java.util.List;

/**
 * Вольер
 * */
public class Cage {
    private int id;
    private List<Animal> animals;
    private boolean isClean =  true;
    private boolean isFeedToday = false;

    /**
     * Создаем запись о вольере
     * @param id идентификационный номер вольера
     * */
    public Cage(int id) {
        this.id = id;
        this.animals = new ArrayList<>();
    }

    /***
     * Добавление животного в вольер
     */
    public void add(Animal animal){
        this.animals.add(animal);
    }

    /***
     * Удаление животного из вольера
     */
    public void remove(Animal animal){
        this.animals.remove(animal);
    }

    public int getId() {
        return id;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public boolean isFeedToday() {
        return isFeedToday;
    }

    public void setFeedToday(boolean feedToday) {
        isFeedToday = feedToday;
    }


}

