package ru.luifuooj.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.luifuooj.model.Task;
import ru.luifuooj.service.TaskService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class StorageUtilsTest {
    private final String pathToFiles = "src/Main/resources/temp/";
    private final String fileName = "BD";
    TaskService taskService;
    Task task1;
    Task task2;
    Task task3;
    Task task4;

    @BeforeEach
    public void setup() {
        taskService = new TaskService();
        task1 = new Task(1L, "Task1", "Info1", "Executor1");
        task2 = new Task(2L, "Task3", "Info2", "Executor2");
        task3 = new Task(3L, "Task2", "Info3", "Executor3");
        task4 = new Task(4L, "Task4", "Info4", "Executor4");
    }

    @AfterEach
    public void clear() throws IOException {
        File bdFile = new File(pathToFiles + fileName);
        if (bdFile.exists()) {
            if (!bdFile.delete()) {
                throw new IOException("Failed to delete bd file");
            }
        }
    }

    @Test
    public void testDelete() {
        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);
        taskService.addTask(task4);

        List<Task> actualList = taskService.getTaskList();

        Assertions.assertEquals(4, actualList.size());

        taskService.removeTask(task1);

        actualList = taskService.getTaskList();

        Assertions.assertFalse(actualList.contains(task1));
        Assertions.assertEquals(3, actualList.size());
    }

    @Test
    public void testSave() {
        File workFolder = new File(pathToFiles);
        File[] files = workFolder.listFiles();
        Assertions.assertEquals(0, files.length);

        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);
        taskService.addTask(task4);

        files = workFolder.listFiles();
        Assertions.assertEquals(1, files.length);

        List<Task> expectedList = readFromDisk();
        List<Task> actualList = taskService.getTaskList();

        Assertions.assertEquals(expectedList.size(), actualList.size());

        for (Task task: expectedList) {
            Assertions.assertTrue(actualList.contains(task));
        }
    }

    @Test
    public void editTest() {
        String execOld = "old";
        String execNew = "new";
        task1.setExecutor(execOld);
        taskService.addTask(task1);

        List<Task> expectedList = readFromDisk();
        Assertions.assertEquals(execOld, expectedList.get(0).getExecutor());

        taskService.findTaskById(task1.getId()).setExecutor(execNew);
        taskService.updateTaskListOnDisk();
        expectedList = readFromDisk();
        Assertions.assertEquals(execNew, expectedList.get(0).getExecutor());

    }

    private List<Task> readFromDisk() {
        try (FileInputStream fileInputStream = new FileInputStream(pathToFiles + fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (List<Task>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}

