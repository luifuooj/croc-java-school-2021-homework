package ru.luifuooj.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Тестирование загрузки файла с диска.
 */
public class DataLoadTest {
    String testFileName = "testFile.xml";
    String pathToFiles = "src/test/resources/inputData/";
    String xmlFile = "<InputFlightData>" +
            "    <Flight>123</Flight>" +
            "    <Airline>LN</Airline>" +
            "    <From>A</From>" +
            "    <Departure>01-01-2020 01:01</Departure>" +
            "    <Duration>01:01</Duration>" +
            "</InputFlightData>";

    @Test
    public void loadTest() {

        String read = loadXmlData(testFileName);
        Assertions.assertEquals(xmlFile, read);

    }

    public String loadXmlData(String fileName) {
        File file = new File(pathToFiles + fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder xmlString = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                xmlString.append(line);
            }
            return xmlString.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
