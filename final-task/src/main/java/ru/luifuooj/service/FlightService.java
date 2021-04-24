package ru.luifuooj.service;


import ru.luifuooj.model.FlightData;
import ru.luifuooj.repository.FlightRepository;

import java.util.List;

/**
 * Сервис по работе с рейсами.
 */
public class FlightService {
    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    /**
     * Получение списка всех рейсов.
     *
     * @return список
     */
    public List<FlightData> getAll() {
        return repository.findAll();
    }

    /**
     * Получение рейса по номеру рейса и авиакомпании.
     *
     * @param flightNumber номер рейса
     * @param airline      авиакомпания
     * @return список
     */
    public List<FlightData> getFlightByNumberAndAirline(String flightNumber, String airline) {
        return repository.findByFlightNumberAndAirline(flightNumber, airline);
    }

    /**
     * Добавление рейса.
     *
     * @param flightData рейс
     */
    public void create(FlightData flightData) {
        repository.create(flightData);
    }

    /**
     * Удаление рейса по номеру рейса и авиакомпании.
     *
     * @param flightNumber номер рейса
     * @param airline      авиакомпания
     */
    public void delete(String flightNumber, String airline) {
        repository.deleteByFlightNumberAndAirline(flightNumber, airline);
    }

    /**
     * Очищение таблицы.
     */
    public void deleteAll() {
        repository.clearTable();
    }
}
