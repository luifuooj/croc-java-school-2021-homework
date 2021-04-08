package ru.luifuooj.outputData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Список людей.
 */
@XmlRootElement(name = "people")
public class People {
    @XmlElement(name = "person")
    private List<Person> personList = new ArrayList<>();

    public boolean contains(Person person) {
        return this.personList.contains(person);
    }

    /**
     * Возврат такого же объекта из списка.
     * @param person человек
     * @return человек из списка
     */
    public Person getSamePersonFromList(Person person) {
        return this.personList.get(this.personList.indexOf(person));
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void addPerson(Person person) {
        this.personList.add(person);
    }
}
