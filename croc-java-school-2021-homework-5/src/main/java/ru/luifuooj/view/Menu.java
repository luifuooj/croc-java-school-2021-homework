package ru.luifuooj.view;

import ru.luifuooj.model.Status;
import ru.luifuooj.model.Task;
import ru.luifuooj.service.TaskService;

import java.util.Scanner;

public class Menu {
    Scanner scanner;
    TaskService taskService;

    public Menu(Scanner scanner, TaskService taskService) {
        this.scanner = scanner;
        this.taskService = taskService;
    }

    /**
     * Главное меню.
     */
    public void mainMenu() {
        System.out.println("OOO ROGA I KOPITA\n" +
                "MENU\n\n" +
                "1: Create task\n" +
                "2: Edit task\n" +
                "3: Delete task\n" +
                "4: Load task\n");
        switch (scanner.nextInt()) {
            case 1:
                createTask();
                break;
            case 2:
                edit();
                break;
            case 3:
                deleteTask();
                break;
            case 4:
                load();
                break;
            default:
                System.out.println("Enter 1 - 4");
        }
        mainMenu();
    }


    /**
     * Пункт меню 1: Create task.
     */
    private void createTask() {
        System.out.println("Enter task id: ");
        Long id = scanner.nextLong();
        String stringtemp = scanner.nextLine();
        System.out.println("Enter task name: ");
        String name = scanner.nextLine();
        System.out.println("Enter task info: ");
        String info = scanner.nextLine();
        System.out.println("Enter task executor: ");
        String executor = scanner.nextLine();

        Task task = new Task(id, name, info, executor);
        taskService.addTask(task);

        System.out.println("New task created!");
        mainMenu();
    }

    /**
     * Пункт меню 2: Edit.
     */
    private void edit() {
        System.out.println("1: Edit status\n" +
                "2: Edit info\n" +
                "3: Edit executor\n" +
                "4: Return to menu\n");
        switch (scanner.nextInt()) {
            case 1:
                editStatus();
                break;
            case 2:
                editInfo();
                break;
            case 3:
                editExecutor();
                break;
            case 4:
                mainMenu();

        }
    }

    /**
     * Подпункт меню 2: Edit -> 1: Edit Status
     */
    private void editStatus() {
        System.out.println("Enter task id: ");
        Long id = scanner.nextLong();
        Task task = taskService.findTaskById(id);
        System.out.println("Current task status: " + task.getStatus());
        System.out.println("Choose new status: \n" +
                "1: TO DO\n" +
                "2: IN PROGRESS\n" +
                "3: DONE\n" +
                "4: CLOSED\n");


        switch (scanner.nextInt()) {
            case 1:
                task.setStatus(Status.TODO);
                break;
            case 2:
                task.setStatus(Status.PROGRESS);
                break;
            case 3:
                task.setStatus(Status.DONE);
                break;
            case 4:
                task.setStatus(Status.CLOSED);
                break;
        }
        taskService.updateTaskListOnDisk();

        System.out.println("Done");
        mainMenu();
    }

    /**
     * Подпункт меню 2: Edit -> 2: Edit Info
     */
    private void editInfo() {
        System.out.println("Enter task id: ");
        Long id = scanner.nextLong();
        String stringtemp = scanner.nextLine();
        System.out.println("Enter new task info: ");
        String info = scanner.nextLine();
        Task task = taskService.findTaskById(id);
        task.setInfo(info);
        taskService.updateTaskListOnDisk();

        System.out.println("Done");
        mainMenu();
    }

    /**
     * Подпункт меню 2: Edit -> 3: Edit Executor
     */
    private void editExecutor() {
        System.out.println("Enter task id: ");
        Long id = scanner.nextLong();
        Task task = taskService.findTaskById(id);
        System.out.println("Current task executor: " + task.getExecutor());
        String stringtemp = scanner.nextLine();
        System.out.println("Enter new executor: ");
        String executor = scanner.nextLine();
        task.setExecutor(executor);
        taskService.updateTaskListOnDisk();

        System.out.println("Done");
        mainMenu();
    }

    /**
     * Пункт меню 3: Delete task.
     */
    private void deleteTask() {
        System.out.println("Enter task id: ");
        Long id = scanner.nextLong();
        Task task = taskService.findTaskById(id);
        taskService.removeTask(task);

        System.out.println("Task deleted!");
        mainMenu();
    }

    /**
     * Пункт меню 4: Load task.
     */
    private void load() {
        System.out.println("1: Load one task\n" +
                "2: Load all tasks\n" +
                "3: Return to menu\n");
        switch (scanner.nextInt()) {
            case 1:
                loadOneTask();
                break;
            case 2:
                loadAllTasks();
                break;
            case 3:
                mainMenu();
        }
    }

    /**
     * Подпункт меню 4: Load task -> 1: Load one task
     */
    private void loadOneTask() {
        System.out.println("1: By id\n2: By name\n3: Return to menu");
        switch (scanner.nextInt()) {
            case 1:
                loadTaskById();
                break;
            case 2:
                loadTaskByName();
                break;
            case 3:
                mainMenu();
        }
    }

    /**
     * Подпункт меню 4: Load task -> 2: Load all tasks
     */
    private void loadAllTasks() {
        System.out.println(taskService.getTaskList().toString());
    }

    /**
     * Подпункт меню 4: Load task -> 1: Load one task -> 2: By name
     */
    private void loadTaskByName() {
        System.out.println("Enter task name: ");
        String temp = scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println(taskService.findTaskByName(name));
    }

    /**
     * Подпункт меню 4: Load task -> 1: Load one task -> 1: By id
     */
    private void loadTaskById() {
        System.out.println("Enter task id: ");
        Long id = scanner.nextLong();
        System.out.println(taskService.findTaskById(id));
    }
}
