package ru.luifuooj.accounting;

import ru.luifuooj.employees.Staff;
import java.time.LocalDateTime;

/**
 * Книга учета
 */
public class Accounting {
    private LocalDateTime dateRecord;
    private String action;
    private Staff staff;

    /**
     * Создание записи в книге учета
     * @param dateRecord дата создания записи
     * @param action действие
     * @param staff сотрудник
     */
    public Accounting(LocalDateTime dateRecord, String action, Staff staff) {
        this.dateRecord = dateRecord;
        this.action = action;
        this.staff = staff;
    }

    public LocalDateTime getDateRecord() {
        return dateRecord;
    }

    public String getAction() {
        return action;
    }

    public Staff getStaff() {
        return staff;
    }

    /**
     * Возвращает текстовое представление записи
     * @return текст
     */
    @Override
    public String toString() {
        return "Запись: \n" +
                "Дата создания записи: " + dateRecord + "\n" +
                "Действие: " + action + "\n" +
                "Сотрудник: " + staff.getName();
    }
}
