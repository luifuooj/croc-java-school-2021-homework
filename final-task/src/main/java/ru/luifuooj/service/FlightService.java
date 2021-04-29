package ru.luifuooj.service;

import ru.luifuooj.model.InputFlightData;
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
    public List<InputFlightData> getAll() {
        return repository.findAll();
    }

    /**
     * Получение рейса по номеру рейса и авиакомпании.
     *
     * @param flightNumber номер рейса
     * @param airline      авиакомпания
     * @return список
     */
    public List<InputFlightData> getFlightByNumberAndAirline(String flightNumber, String airline) {
        return repository.findByFlightNumberAndAirline(flightNumber, airline);
    }

    /**
     * Добавление рейса.
     *
     * @param iFlightData рейс
     */
    public void create(InputFlightData iFlightData, InputFlightData oFlightData) {
        repository.create(iFlightData, oFlightData);
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
