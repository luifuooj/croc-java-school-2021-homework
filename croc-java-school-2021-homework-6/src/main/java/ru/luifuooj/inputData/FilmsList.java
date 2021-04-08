package ru.luifuooj.inputData;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Список фильмов, начальная структура.
 */
@XmlRootElement(name = "films")
public class FilmsList {
    @XmlElement(name = "film")
    private List<Film> films = new ArrayList<>();

    public FilmsList() {
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmsList filmsList = (FilmsList) o;
        return Objects.equals(films, filmsList.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(films);
    }
}
