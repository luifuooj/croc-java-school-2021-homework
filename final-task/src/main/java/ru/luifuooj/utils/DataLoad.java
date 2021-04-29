package ru.luifuooj.utils;

import java.io.*;

public class DataLoad {
    String pathToFiles = "src/main/resources/input/";

    /**
     * Чтение файла с диска.
     *
     * @param fileName имя файла
     * @return данные в виде строки
     */
    public String loadData(String fileName) {
        File file = new File(pathToFiles + fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
