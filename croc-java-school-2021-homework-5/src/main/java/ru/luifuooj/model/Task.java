package ru.luifuooj.model;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Идентификатор задачи.
     */
    private final Long id;

    /**
     * Наименование.
     */
    private final String name;

    /**
     * Описание.
     */
    private String info;

    /**
     * Исполнитель.
     */
    private String executor;

    /**
     * Статус.
     */
    private Status status;

    public Task(Long id, String name, String info, String executor) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.executor = executor;
        this.status = Status.TODO;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) &&
                name.equals(task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Задача\n" +
                "id: " + id +
                ", Наименование: '" + name + '\'' +
                ", Описание: '" + info + '\'' +
                ", Исполнитель: '" + executor + '\'' +
                ", Статус: " + status +
                '\n';
    }
}
