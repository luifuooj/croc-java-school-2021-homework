package ru.luifuooj;

import ru.luifuooj.accounting.Accounting;
import ru.luifuooj.employees.Staff;
import ru.luifuooj.zoo.Zoo;
import ru.luifuooj.аnimals.Animal;
import ru.luifuooj.*;
import ru.luifuooj.аnimals.Cage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        Staff staffBoris = new Staff(1, "Boris");
        zoo.addStaff(staffBoris);
        Animal animalElephant = new Animal("elephant","Elephant", LocalDate.now());
        zoo.addAnimal(animalElephant); /** Добавление животного */
        Animal animalGiraffe = new Animal("giraffe","Giraffe", LocalDate.now());
        zoo.removeAnimal(animalGiraffe); /** Удаление животного */
        Cage cageElephant = new Cage(1);
        zoo.addCage(cageElephant);
        cageElephant.add(animalElephant);
        zoo.animalSetSick(animalElephant, true, staffBoris); /** Добавление записи о болезни животного */
        zoo.cageIsClean(cageElephant, true, staffBoris); /** Добавление записи об уборке вольера */
        zoo.cageIsFeed(cageElephant, true, staffBoris); /** Добавление записи о кормлении животных */
        zoo.animalSetSick(animalElephant, false, staffBoris); /** Добавление записи о болезни животного */
        zoo.cageIsClean(cageElephant, false, staffBoris); /** Добавление записи об уборке вольера */
        zoo.cageIsFeed(cageElephant, false, staffBoris); /** Добавление записи о кормлении животных */
        System.out.println(zoo.getAccountingInfo());
    }
}
