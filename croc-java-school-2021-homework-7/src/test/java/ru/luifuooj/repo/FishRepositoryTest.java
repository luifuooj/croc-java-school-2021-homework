package ru.luifuooj.repo;

import org.junit.jupiter.api.*;
import ru.luifuooj.provider.DataSourceProvider;
import ru.luifuooj.model.Fish;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

class FishRepositoryTest {
    private static FishRepository fishRepository;

    @BeforeAll
    static void prepareConnect() throws IOException {
        DataSourceProvider dataSourceProvider = null;
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
            throw e;
        }
        fishRepository = new FishRepository(dataSourceProvider.getDataSource());
    }

    @AfterEach
    void clearTable() throws SQLException {
        fishRepository.clearTable();
    }

    @Test
    void createTest() {
        Fish fish = new Fish("fish1", LocalDate.now(), LocalTime.of(00,01), 12, true);
        fishRepository.create(fish);
        System.out.println(fishRepository.findAll());
        Assertions.assertTrue(fishRepository.findAll().contains(fish));
    }

    @Test
    void updateTest() {
        Fish fish = new Fish("fish1", LocalDate.now(), LocalTime.of(00,01), 12, true);
        fishRepository.create(fish);
        List<Fish> fishList = fishRepository.findAll();
        Assertions.assertTrue(fishList.contains(fish));

        Fish updateFish = fishRepository.findByName("fish1").get(0);
        updateFish.setQuantity(14);
        fishRepository.update(updateFish);

        System.out.println(fishRepository.findAll());

        fishList = fishRepository.findAll();
        Assertions.assertFalse(fishList.contains(fish));
        Assertions.assertTrue(fishList.contains(updateFish));
    }

    @Test
    void findAllTest() {
        Fish fish1 = new Fish("fish1", LocalDate.now(), LocalTime.of(00,01), 12, true);
        Fish fish2 = new Fish("fish2", LocalDate.now(), LocalTime.of(00,01), 15, true);
        Fish fish3 = new Fish("fish3", LocalDate.now(), LocalTime.of(00,01), 15, true);
        Fish fish4 = new Fish("fish4", LocalDate.now(), LocalTime.of(00,01), 15, true);
        fishRepository.create(fish1);
        fishRepository.create(fish2);
        fishRepository.create(fish3);
        fishRepository.create(fish4);
        List<Fish> fishList = fishRepository.findAll();
        Assertions.assertTrue(fishList.contains(fish1));
        Assertions.assertTrue(fishList.contains(fish2));
        Assertions.assertTrue(fishList.contains(fish3));
        Assertions.assertTrue(fishList.contains(fish4));
    }

    @Test
    void findByNameTest() {
        Fish fish1 = new Fish("fish1", LocalDate.now(), LocalTime.of(00,01), 12, true);
        Fish fish2 = new Fish("fish2", LocalDate.now(), LocalTime.of(00,01), 13, true);
        fishRepository.create(fish1);
        fishRepository.create(fish2);
        List<Fish> fishList = fishRepository.findByName("fish1");
        Assertions.assertTrue(fishList.contains(fish1));
        Assertions.assertFalse(fishList.contains(fish2));
    }

    @Test
    void deleteByNameTest() {
        Fish fish1 = new Fish("fish1", LocalDate.now(), LocalTime.of(00,01), 12, true);
        fishRepository.create(fish1);
        List<Fish> fishList = fishRepository.findByName("fish1");
        Assertions.assertTrue(fishList.contains(fish1));

        fishRepository.deleteByName("fish1");
        fishList = fishRepository.findAll();
        Assertions.assertFalse(fishList.contains(fish1));
    }
}
