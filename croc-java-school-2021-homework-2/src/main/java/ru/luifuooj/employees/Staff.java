package ru.luifuooj.employees;
import ru.luifuooj.аnimals.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Сотрудник
 * */
public class Staff {
    private int id;
    private String name;
    private List<Animal> animals;

    /**
     * Добавление нового сотрудника
     * @param id идентификационный номер сотрудника
     * @param name имя сотрудника
     * */
    public Staff(int id, String name) {
        this.id = id;
        this.name = name;
        this.animals = new ArrayList<>();
    }

    /***
     * Добавление животного в список надзора смотрителя
     */
    public void add(Animal animal){
        this.animals.add(animal);
    }

    /***
     * Удаление животного из списка надзора смотрителя
     */
    public void remove(Animal animal){
        this.animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


}
