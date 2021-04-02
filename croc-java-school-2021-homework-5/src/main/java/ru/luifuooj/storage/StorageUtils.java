package ru.luifuooj.storage;

import ru.luifuooj.model.Task;

import java.io.*;
import java.util.List;

public class StorageUtils {

    /**
     * Путь к файлу.
     */
    private final String pathToFiles = "src/Main/resources/temp/";

    /**
     * Название файла.
     */
    private final String fileName = "BD";



    /**
     * Сохранение в файл.
     */
    public void save(List<Task> taskList) {
        createPath();
        try (OutputStream outputStream = new FileOutputStream(pathToFiles + fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создание директории.
     */
    private void createPath() {
        try {
            File dir = new File(pathToFiles);
            dir.mkdirs();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Удаление файла.
     * @return true, если удалил, false, если нет
     */
    public boolean delete() {
        try {
            File file = new File(pathToFiles + fileName);
            return file.delete();
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Получение задачи из файла.
     * @return задача
     */
    public List<Task> load() {
        try (FileInputStream fileInputStream = new FileInputStream(pathToFiles + fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (List<Task>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
