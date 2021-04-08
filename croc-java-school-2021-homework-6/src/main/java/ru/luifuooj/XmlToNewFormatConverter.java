package ru.luifuooj;

import ru.luifuooj.inputData.FilmsList;
import ru.luifuooj.inputData.profession.FilmMaker;
import ru.luifuooj.outputData.FilmFormat;
import ru.luifuooj.outputData.Function;
import ru.luifuooj.outputData.People;
import ru.luifuooj.inputData.*;
import ru.luifuooj.outputData.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Конвертер.
 */
public class XmlToNewFormatConverter {

    /**
     * Метод конвертации из старой структуры в новую.
     * @param inputData старая структура, xml
     * @return новая структура, xml
     * @throws IOException
     */
    public String formatConvert(String inputData) throws IOException {
       People people = new People();
       JaxbConverter jaxbConverter = new JaxbConverter();

       FilmsList filmsList = jaxbConverter.fromXml(inputData, FilmsList.class);
       //по всем фильмам
       for (Film eachFilm: filmsList.getFilms()) {
           //по всем создателям фильма
           List<FilmMaker> filmMakers = new ArrayList<>();
           filmMakers.addAll(eachFilm.getDirectorList());
           filmMakers.addAll(eachFilm.getScreenwriterList());
           for (FilmMaker eachFilmMaker : filmMakers) {

               Person person = new Person(eachFilmMaker.getName());

               if (people.contains(person)) {
                   person = people.getSamePersonFromList(person);
                   //создаем пустой фильм
                   FilmFormat film = new FilmFormat(eachFilm.getTitle());
                   boolean ifFilmExist = person.filmExist(film);
                   //проверка что фильм с тем же названием у человека существует
                   if (ifFilmExist) {
                       //если существует, заменяем на существующий
                       film = person.getSameFilmFromList(film);
                   }
                   film.addFunction(new Function(eachFilmMaker));
                   if (!ifFilmExist) {
                       person.addFilm(film);
                   }
               } else {
                   FilmFormat film = new FilmFormat(eachFilm.getTitle());
                   film.addFunction(new Function(eachFilmMaker));
                   person.addFilm(film);
                   people.addPerson(person);
               }

           }
       }
       return jaxbConverter.toXml(people);
    }

}
