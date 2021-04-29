package ru.luifuooj.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.luifuooj.dbprovider.DataSourceProvider;
import ru.luifuooj.model.InputFlightData;
import ru.luifuooj.model.FlightType;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Тестирование методов репозитория.
 */
public class FlightRepositoryTest {

    private static FlightRepository repository;

    InputFlightData incomingFlightData = new InputFlightData(
            FlightType.INCOMING,
            "123",
            "LN",
            "A",
            LocalDateTime.of(2020, 1, 1, 1, 1),
            LocalTime.of(1, 1));

    InputFlightData outboundFlightData = new InputFlightData(
            FlightType.OUTBOUND,
            "123",
            "LN",
            "B",
            LocalDateTime.of(2020, 1, 1, 2, 1),
            LocalTime.of(1, 1));

    InputFlightData incomingFlightData2 = new InputFlightData(
            FlightType.INCOMING,
            "231",
            "AW",
            "A",
            LocalDateTime.of(2020, 2, 1, 1, 1),
            LocalTime.of(1, 1));

    InputFlightData outboundFlightData2 = new InputFlightData(
            FlightType.OUTBOUND,
            "231",
            "AW",
            "B",
            LocalDateTime.of(2020, 2, 1, 2, 1),
            LocalTime.of(1, 1));

    InputFlightData incomingFlightData3 = new InputFlightData(
            FlightType.INCOMING,
            "lk87",
            "KZ",
            "A",
            LocalDateTime.of(2020, 3, 1, 1, 1),
            LocalTime.of(1, 1));

    InputFlightData outboundFlightData3 = new InputFlightData(
            FlightType.OUTBOUND,
            "lk87",
            "KZ",
            "B",
            LocalDateTime.of(2020, 3, 1, 2, 1),
            LocalTime.of(1, 1));

    @BeforeAll
    public static void prepareConnect() throws IOException {
        DataSourceProvider dataSourceProvider = null;
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
            throw e;
        }
        repository = new FlightRepository(dataSourceProvider.getDataSource());
    }

    @AfterEach
    public void clearTable() throws SQLException {
        repository.clearTable();
    }

    @Test
    public void createTest() {
        repository.create(incomingFlightData, outboundFlightData);
        System.out.println(repository.findAll());
        assertTrue(repository.findAll().contains(incomingFlightData));
    }

    @Test
    public void updateTest() {
        repository.create(incomingFlightData, outboundFlightData);
        List<InputFlightData> flightDataList = repository.findAll();
        assertTrue(flightDataList.contains(incomingFlightData));

        InputFlightData updateFlight = repository.findByFlightNumberAndAirline("123", "LN").get(0);
        updateFlight.setAirline("BN");
        repository.update(updateFlight);

        System.out.println(repository.findAll());

        flightDataList = repository.findAll();
        assertFalse(flightDataList.contains(incomingFlightData));
        assertTrue(flightDataList.contains(updateFlight));
    }

    @Test
    public void findAllTest() {
        repository.create(incomingFlightData, outboundFlightData);
        repository.create(incomingFlightData2, outboundFlightData2);
        repository.create(incomingFlightData3, outboundFlightData3);

        List<InputFlightData> flightDataList = repository.findAll();
        assertTrue(flightDataList.contains(incomingFlightData));
        assertTrue(flightDataList.contains(incomingFlightData2));
        assertTrue(flightDataList.contains(incomingFlightData3));
    }

    @Test
    public void findByIdTest() {
        repository.create(incomingFlightData, outboundFlightData);
        repository.create(incomingFlightData2, outboundFlightData2);
        List<InputFlightData> flightDataList = repository.findAll();
        Integer id1 = flightDataList.get(0).getId();

        List<InputFlightData> flightFindById = repository.findById(id1);
        assertTrue(flightFindById.contains(incomingFlightData));
        assertFalse(flightFindById.contains(incomingFlightData2));
    }

    @Test
    public void findByFlightNumberAndAirlineTest() {
        repository.create(incomingFlightData, outboundFlightData);
        repository.create(incomingFlightData2, outboundFlightData2);

        List<InputFlightData> flightDataList = repository.findByFlightNumberAndAirline("123", "LN");
        assertTrue(flightDataList.contains(incomingFlightData));
        assertFalse(flightDataList.contains(incomingFlightData2));
    }

    @Test
    public void deleteByIdTest() {
        repository.create(incomingFlightData, outboundFlightData);
        repository.create(incomingFlightData2, outboundFlightData2);
        List<InputFlightData> flightDataList = repository.findAll();
        assertTrue(flightDataList.contains(incomingFlightData));
        Integer id1 = flightDataList.get(0).getId();

        repository.deleteById(id1);
        List<InputFlightData> flightDataList2 = repository.findAll();
        assertFalse(flightDataList2.contains(incomingFlightData));
        assertTrue(flightDataList2.contains(incomingFlightData2));
    }

    @Test
    public void deleteByFlightNumberAndAirlineTest() {
        repository.create(incomingFlightData, outboundFlightData);
        repository.create(incomingFlightData2, outboundFlightData2);
        List<InputFlightData> flightDataList = repository.findByFlightNumberAndAirline("231", "AW");
        assertTrue(flightDataList.contains(incomingFlightData2));

        repository.deleteByFlightNumberAndAirline("231", "AW");
        flightDataList = repository.findAll();
        assertFalse(flightDataList.contains(incomingFlightData2));
    }
}
