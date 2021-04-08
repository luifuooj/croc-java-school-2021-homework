package ru.luifuooj.outputData;

import ru.luifuooj.inputData.profession.FilmMaker;
import ru.luifuooj.inputData.Director;
import ru.luifuooj.inputData.Screenwriter;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Функция сотрудника в этом фильме.
 */
public class Function {
    @XmlAttribute(name = "name")
    private String name;

    public Function() {
    }

    public Function(FilmMaker filmMaker) {
        setName(filmMaker);
    }

    /**
     * Установка должности (строка) в соответствии с должностью из старой структуры.
     * @param filmMaker должность из старой структуры
     */
    public void setName(FilmMaker filmMaker) {
        if (filmMaker instanceof Screenwriter) {
            this.name = "Сценарист";
        }
        if (filmMaker instanceof Director)
            this.name = "Режиссер";
    }
}
