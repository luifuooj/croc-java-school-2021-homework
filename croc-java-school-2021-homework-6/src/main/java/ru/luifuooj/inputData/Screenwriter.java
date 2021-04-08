package ru.luifuooj.inputData;

import ru.luifuooj.inputData.profession.FilmMaker;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * Сценарист, начальная структура.
 */
public class Screenwriter extends FilmMaker {
    @XmlAttribute
    private String name;

    public Screenwriter() {
    }

    public Screenwriter(String name) {
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
        Screenwriter that = (Screenwriter) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
