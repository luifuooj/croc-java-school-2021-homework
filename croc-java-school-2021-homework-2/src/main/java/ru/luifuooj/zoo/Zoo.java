package ru.luifuooj.zoo;

import ru.luifuooj.accounting.Accounting;
import ru.luifuooj.аnimals.Animal;
import ru.luifuooj.employees.Staff;
import ru.luifuooj.аnimals.Cage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Зоопарк
 */
public class Zoo {
    private final List<Animal> animals;
    private final List<Staff> staff;
    private final List<Cage> cages;
    private final List<Accounting> accounting;

    /**
     * Создание нового зоопарка
     */
    public Zoo() {
        this.animals = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.cages = new ArrayList<>();
        this.accounting = new ArrayList<>();
    }

    /**
     * Возвращает все записи
     * @return текст
     */
    public String getAccountingInfo() {
        StringBuilder info = new StringBuilder();
        for (Accounting record : accounting) {
            info.append(record.toString()).append("\n");
        }
        return info.toString();
    }

    /**
     * Добавление сотрудника
     *
     * @param staff сотрудник
     */
    public void addStaff(Staff staff) {
        this.staff.add(staff);
    }

    /**
     * Увольнение сотрудника
     *
     * @param staff сотрудник
     */
    public void removeStaff(Staff staff) {
        this.staff.remove(staff);
    }

    /**
     * Добавление животного
     *
     * @param animal животное
     */
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    /**
     * Исчезновение животного
     *
     * @param animal животное
     */
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    /**
     * Добавление вольера
     *
     * @param cage вольер
     */
    public void addCage(Cage cage) {
        this.cages.add(cage);
    }

    /**
     * Удаление вольера
     *
     * @param cage вольер
     */
    public void removeCage(Cage cage) {
        this.cages.remove(cage);
    }


    /**
     * Добавление записи в книге учета
     *
     * @param accounting запись
     */
    public void addRecord(Accounting accounting) {
        this.accounting.add(accounting);
    }

    /**
     * Добавление записи о состоянии животного
     *
     * @param animal животное
     * @param isSick если true, то болеет
     * @param staff  сотрудник, сделавший запись
     */
    public void animalSetSick(Animal animal, boolean isSick, Staff staff) {
        animal.setSick(isSick);
        Accounting record = new Accounting(LocalDateTime.now(), "Животное болеет: " + isSick, staff);
        this.accounting.add(record);
    }

    /**
     * Добавление записи о кормлении животных
     * @param cage вольер
     * @param isFeed true- животных покормили
     * @param staff сотрудник, сделавший запись
     */
    public void cageIsFeed(Cage cage, boolean isFeed, Staff staff) {
        cage.setFeedToday(isFeed);
        Accounting record = new Accounting(LocalDateTime.now(), "Животные покушали: " + isFeed, staff);
        this.accounting.add(record);
    }

    /**
     * Добавление записи об уборке вольера
     * @param cage вольер
     * @param isClean true - вольер чист
     * @param staff сотрудник, сделавший запись
     */
    public void cageIsClean(Cage cage, boolean isClean, Staff staff) {
        cage.setClean(isClean);
        Accounting record = new Accounting(LocalDateTime.now(), "Вольер чист: " + isClean, staff);
        this.accounting.add(record);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public List<Cage> getCages() {
        return cages;
    }

    public List<Accounting> getAccounting() {
        return accounting;
    }
}
