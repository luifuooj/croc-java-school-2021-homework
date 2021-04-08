package ru.luifuooj.inputData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.luifuooj.JaxbConverter;
import ru.luifuooj.XmlToNewFormatConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlToNewFormatConverterTest {

    String original="<films>" +
            "<film>" +
            "<title>Фильм 1</title>" +
            "<description>Описание 1</description>" +
            "<screenwriters>" +
            "<screenwriter name=\"Человек 1\"/>" +
            "<screenwriter name=\"Человек 2\"/>" +
            "</screenwriters>" +
            "<directors>" +
            "<director name=\"Человек 1\"/>" +
            "<director name=\"Человек 3\"/>" +
            "</directors>" +
            "</film>" +
            "<film>" +
            "<title>Фильм 2</title>" +
            "<description>Описание 2</description>" +
            "<screenwriters>" +
            "<screenwriter name=\"Человек 3\"/>" +
            "<screenwriter name=\"Человек 2\"/>" +
            "</screenwriters>" +
            "<directors>" +
            "<director name=\"Человек 2\"/>" +
            "<director name=\"Человек 4\"/>" +
            "<director name=\"Человек 3\"/>" +
            "</directors>" +
            "</film>" +
            "</films>";

    String custom="<people>" +
            "<person>" +
            "<name>Человек 1</name>" +
            "<films>" +
            "<film title=\"Фильм 1\">" +
            "<functions>" +
            "<function name=\"Режиссер\"/>" +
            "<function name=\"Сценарист\"/>" +
            "</functions>" +
            "</film>" +
            "</films>" +
            "</person>" +
            "<person>" +
            "<name>Человек 3</name>" +
            "<films>" +
            "<film title=\"Фильм 1\">" +
            "<functions>" +
            "<function name=\"Режиссер\"/>" +
            "</functions>" +
            "</film>" +
            "<film title=\"Фильм 2\">" +
            "<functions>" +
            "<function name=\"Сценарист\"/>" +
            "<function name=\"Режиссер\"/>" +
            "</functions>" +
            "</film>" +
            "</films>" +
            "</person>" +
            "<person>" +
            "<name>Человек 2</name>" +
            "<films>" +
            "<film title=\"Фильм 1\">" +
            "<functions>" +
            "<function name=\"Сценарист\"/>" +
            "</functions>" +
            "</film>" +
            "<film title=\"Фильм 2\">" +
            "<functions>" +
            "<function name=\"Режиссер\"/>" +
            "<function name=\"Сценарист\"/>" +
            "</functions>" +
            "</film>" +
            "</films>" +
            "</person>" +
            "<person>" +
            "<name>Человек 4</name>" +
            "<films>" +
            "<film title=\"Фильм 2\">" +
            "<functions>" +
            "<function name=\"Режиссер\"/>" +
            "</functions>" +
            "</film>" +
            "</films>" +
            "</person>" +
            "</people>";

    /**
     * Иногда падает, различия лишь в порядке должностей человека в фильме.
     * Фиксится непосредственным изменение порядка должностей в ожидаемой строки
     * @throws IOException
     */
    @Test
    public void testConvert() throws IOException {
        XmlToNewFormatConverter xmlToNewFormatConverter = new XmlToNewFormatConverter();
        Assertions.assertEquals(custom, xmlToNewFormatConverter.formatConvert(original));
    }
}
