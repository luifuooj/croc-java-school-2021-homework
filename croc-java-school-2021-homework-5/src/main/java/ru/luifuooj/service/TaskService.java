package ru.luifuooj.service;

import ru.luifuooj.storage.StorageUtils;
import ru.luifuooj.model.Task;

import java.util.LinkedList;
import java.util.List;

public class TaskService {
    /**
     * Список задач.
     */
    private List<Task> taskList = new LinkedList<>();
    private final StorageUtils storageUtils = new StorageUtils();


    /**
     * Добавление задачи.
     * @param task задача
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        updateTaskListOnDisk();
    }

    /**
     * Удаление задачи.
     * @param task задача
     */
    public void removeTask(Task task) {
        this.taskList.remove(task);
        updateTaskListOnDisk();
    }

    /**
     * Получение задачи по id.
     * @param id идентификатор
     * @return задача
     */
    public Task findTaskById(Long id) {
        taskList = storageUtils.load();
        for (Task eachTask: taskList){
            if (eachTask.getId().equals(id)) {
                return eachTask;
            }
        }
        return null;
    }

    /**
     * Получение задачи по наименованию.
     * @param name наименование
     * @return задача
     */
    public Task findTaskByName(String name) {
        taskList = storageUtils.load();
        for (Task eachTask: taskList) {
            if (eachTask.getName().equals(name)) {
                return eachTask;
            }
        }
        return null;
    }

    /**
     * Обновление "БД".
     */
    public void updateTaskListOnDisk() {
        storageUtils.delete();
        storageUtils.save(taskList);
    }

    public List<Task> getTaskList() {
        taskList = storageUtils.load();
        return taskList;
    }


}
