package ru.luifuooj.inputData;

import ru.luifuooj.inputData.profession.FilmMaker;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * Режиссер.
 */
public class Director extends FilmMaker {
    @XmlAttribute
    private String name;

    public Director() {
    }

    public Director(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
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
        Director director = (Director) o;
        return Objects.equals(name, director.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
