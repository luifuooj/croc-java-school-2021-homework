package ru.luifuooj.outputData;

import ru.luifuooj.inputData.Film;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Человек.
 */
public class Person {
    @XmlElement(name = "name")
    private String name;
    @XmlElementWrapper(name = "films")
    @XmlElement(name = "film")
    private List<FilmFormat> filmFormatList = new ArrayList<>();

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void addFilm(FilmFormat film) {
        this.filmFormatList.add(film);
    }

    public boolean filmExist(FilmFormat film) {
        return filmFormatList.contains(film);
    }

    /**
     * Возвращает существующий объект из списка с таким же названием (equals).
     * @param filmFormat объект из "внешней" среды
     * @return объект из списка
     */
    public FilmFormat getSameFilmFromList(FilmFormat filmFormat) {
        return filmFormatList.get(this.filmFormatList.indexOf(filmFormat));
    }

    /**
     * Переопределяем метод, чтобы проверка проходила не по ссылке, а по названию.
     * @param o проверяемый объект
     * @return false если названия разные (объекты тоже)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
