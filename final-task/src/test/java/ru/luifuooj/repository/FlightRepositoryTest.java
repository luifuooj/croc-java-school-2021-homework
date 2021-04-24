package ru.luifuooj.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.luifuooj.dbprovider.DataSourceProvider;
import ru.luifuooj.model.FlightData;
import ru.luifuooj.model.IncomingFlightData;
import ru.luifuooj.model.OutboundFlightData;

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
    IncomingFlightData incomingFlightData = new IncomingFlightData(
            "123",
            "LN",
            "A" ,
            LocalDateTime.of(2020,1, 1, 1, 1),
            LocalTime.of(1, 1));
    OutboundFlightData outboundFlightData = new OutboundFlightData(
            "123",
            "LN",
            "B",
            LocalDateTime.of(2020, 1 , 1, 2, 1),
            LocalTime.of(1,1));
    FlightData flightData = new FlightData(incomingFlightData, outboundFlightData);
    IncomingFlightData incomingFlightData2 = new IncomingFlightData(
            "231",
            "AW",
            "A" ,
            LocalDateTime.of(2020,2, 1, 1, 1),
            LocalTime.of(1, 1));
    OutboundFlightData outboundFlightData2 = new OutboundFlightData(
            "231",
            "AW",
            "B",
            LocalDateTime.of(2020, 2 , 1, 2, 1),
            LocalTime.of(1, 1));
    FlightData flightData2 = new FlightData(incomingFlightData2, outboundFlightData2);

    IncomingFlightData incomingFlightData3 = new IncomingFlightData(
            "lk87",
            "KZ",
            "A" ,
            LocalDateTime.of(2020,3, 1, 1, 1),
            LocalTime.of(1, 1));
    OutboundFlightData outboundFlightData3 = new OutboundFlightData(
            "lk87",
            "KZ",
            "B",
            LocalDateTime.of(2020, 3, 1, 2, 1),
            LocalTime.of(1,1));
    FlightData flightData3 = new FlightData(incomingFlightData3, outboundFlightData3);

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
        repository.create(flightData);
        System.out.println(repository.findAll());
        Assertions.assertTrue(repository.findAll().contains(flightData));
    }
    @Test
    public void updateTest() {
        repository.create(flightData);
        List<FlightData> flightDataList = repository.findAll();
        Assertions.assertTrue(flightDataList.contains(flightData));

        FlightData updateFlight = repository.findByFlightNumberAndAirline("123", "LN").get(0);
        updateFlight.setAirline("BN");
        repository.update(updateFlight);

        System.out.println(repository.findAll());

        flightDataList = repository.findAll();
        Assertions.assertFalse(flightDataList.contains(flightData));
        Assertions.assertTrue(flightDataList.contains(updateFlight));
    }

    @Test
    public void findAllTest() {
        repository.create(flightData);
        repository.create(flightData2);
        repository.create(flightData3);

        List<FlightData> flightDataList = repository.findAll();
        Assertions.assertTrue(flightDataList.contains(flightData));
        Assertions.assertTrue(flightDataList.contains(flightData2));
        Assertions.assertTrue(flightDataList.contains(flightData3));
    }

    @Test
    public void findByIdTest() {
        repository.create(flightData);
        repository.create(flightData2);
        List<FlightData> flightDataList = repository.findAll();
        Integer id1 = flightDataList.get(0).getId();

        List<FlightData> flightFindById = repository.findById(id1);
        Assertions.assertTrue(flightFindById.contains(flightData));
        Assertions.assertFalse(flightFindById.contains(flightData2));
    }

    @Test
    public void findByFlightNumberAndAirlineTest() {
        repository.create(flightData);
        repository.create(flightData2);
        repository.create(flightData3);

        List<FlightData> flightDataList = repository.findByFlightNumberAndAirline("123", "LN");
        Assertions.assertTrue(flightDataList.contains(flightData));
        Assertions.assertFalse(flightDataList.contains(flightData2));
    }

    @Test
    public void deleteByIdTest() {
        repository.create(flightData);
        repository.create(flightData2);
        List<FlightData> flightDataList = repository.findAll();
        Assertions.assertTrue(flightDataList.contains(flightData));
        Integer id1 = flightDataList.get(0).getId();

        repository.deleteById(id1);
        List<FlightData> flightDataList2 = repository.findAll();
        Assertions.assertFalse(flightDataList2.contains(flightData));
        Assertions.assertTrue(flightDataList2.contains(flightData2));
    }

    @Test
    public void deleteByFlightNumberAndAirlineTest() {
        repository.create(flightData);
        repository.create(flightData2);
        List<FlightData> flightDataList = repository.findByFlightNumberAndAirline("231", "AW");
        Assertions.assertTrue(flightDataList.contains(flightData2));

        repository.deleteByFlightNumberAndAirline("231", "AW");
        flightDataList = repository.findAll();
        Assertions.assertFalse(flightDataList.contains(flightData2));
    }
}
