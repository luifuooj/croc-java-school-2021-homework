package ru.luifuooj.service;

import ru.luifuooj.model.Fish;
import ru.luifuooj.repo.FishRepository;

import java.util.List;

/**
 * Сервис по работе с рыбами.
 */
public class FishService {

    private final FishRepository repository;

    public FishService(FishRepository repository) {
        this.repository = repository;
    }

    /**
     * Получение списка всех рыб.
     * @return список
     */
    public List<Fish> getAll() {
        return repository.findAll();
    }

    /**
     * Получение рыбы по имени.
     * @param name идентификатор
     * @return список
     */
    public List<Fish> getFishByName(String name) {
        return repository.findByName(name);
    }

    /**
     * Добавление рыбы.
     * @param fish рыба
     */
    public void create(Fish fish) {
        repository.create(fish);
    }

    /**
     * Удаление рыбы.
     * @param name идентификатор
     */
    public void delete(String name) {
        repository.deleteByName(name);
    }
}
