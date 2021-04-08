package ru.luifuooj.inputData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Фильм, начальная структура.
 */
public class Film {
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "description")
    private String description;
    @XmlElementWrapper(name = "screenwriters")
    @XmlElement(name = "screenwriter")
    private List<Screenwriter> screenwriterList = new ArrayList<>();
    @XmlElementWrapper(name = "directors")
    @XmlElement(name = "director")
    private List<Director> directorList = new ArrayList<>();

    public Film() {
    }

    public Film(String title, String description, List<Screenwriter> screenwriterList, List<Director> directorList) {
        this.title = title;
        this.description = description;
        this.screenwriterList = screenwriterList;
        this.directorList = directorList;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Screenwriter> getScreenwriterList() {
        return screenwriterList;
    }

    public List<Director> getDirectorList() {
        return directorList;
    }

    public void setScreenwriterList(List<Screenwriter> screenwriterList) {
        this.screenwriterList = screenwriterList;
    }

    public void setDirectorList(List<Director> directorList) {
        this.directorList = directorList;
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
        Film film = (Film) o;
        return Objects.equals(title, film.title) &&
                Objects.equals(description, film.description) &&
                Objects.equals(screenwriterList, film.screenwriterList) &&
                Objects.equals(directorList, film.directorList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, screenwriterList, directorList);
    }
}
