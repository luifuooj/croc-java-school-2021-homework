package ru.luifuooj;

import ru.luifuooj.model.Task;
import ru.luifuooj.service.TaskService;
import ru.luifuooj.view.Menu;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();
        Menu menu = new Menu(scanner, taskService);


        Task task1 = new Task(1L, "Task1", "Info1", "Executor1");
        Task task2 = new Task(2L, "Task3", "Info2", "Executor2");
        Task task3 = new Task(3L, "Task2", "Info3", "Executor3");
        Task task4 = new Task(4L, "Task4", "Info4", "Executor4");
        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);
        taskService.addTask(task4);

        menu.mainMenu();
    }
}
