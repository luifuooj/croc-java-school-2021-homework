package ru.luifuooj.outputData;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.*;

/**
 * Фильм, новый формат
 */
public class FilmFormat {
    @XmlAttribute
    private String title;
    @XmlElementWrapper(name = "functions")
    @XmlElement(name = "function")
    private Set<Function> functionList = new HashSet<>();

    public FilmFormat() {
    }

    public FilmFormat(String title) {
        this.title = title;
    }

    public void addFunction(Function function) {
        this.functionList.add(function);
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
        FilmFormat that = (FilmFormat) o;
        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
