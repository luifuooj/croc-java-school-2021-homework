package ru.luifuooj.utils.file;

import java.io.*;


public class Storage {
    String pathToFiles = "src/main/resources/input/";

    /**
     * Чтение xml-файла с диска.
     * @param fileName имя файла
     * @return xml в виде строки
     */
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
