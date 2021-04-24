package ru.luifuooj.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.luifuooj.model.FlightData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для доступа к таблице с данными о расписании авиарейсов.
 */
public class FlightRepository {

    private static final String TABLE_NAME = "flight_timetable";

    private EmbeddedDataSource dataSource;

    public FlightRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Инициализация таблицы.
     */
    private void initTable() {
        System.out.println(String.format("Start initializing %s table", TABLE_NAME));
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Table has already been initialized");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + "("
                                + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY primary key,"
                                + "flight_number VARCHAR(15), "
                                + "airline VARCHAR(255),"
                                + "point_of_departure VARCHAR(255),"
                                + "departure_date DATE,"
                                + "departure_time TIME, "
                                + "travel_time TIME,"
                                + "point_of_arrival VARCHAR(255),"
                                + "arrival_date DATE,"
                                + "arrival_time TIME "
                                + ")");
                System.out.println("Table was successfully initialized");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка инициализации: " + e.getMessage());
        } finally {
            System.out.println("=========================");
        }
    }

    /**
     * Добавление нового рейса в базу.
     */
    public void create(FlightData flightData) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME +
                "( flight_number, airline, point_of_departure, departure_date, " +
                "departure_time, travel_time, point_of_arrival, arrival_date, arrival_time ) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setFilledStatement(flightData, statement);
            statement.execute();
        } catch (Exception e) {
            printQueryException(e);
        }
    }

    private void setFilledStatement(FlightData flightData, PreparedStatement statement) throws SQLException {
        statement.setString(1, flightData.getFlightNumber());
        statement.setString(2, flightData.getAirline());
        statement.setString(3, flightData.getPointOfDeparture());
        statement.setDate(4, Date.valueOf(flightData.getDepartureDate()));
        statement.setTime(5, Time.valueOf(flightData.getDepartureTime()));
        statement.setTime(6, Time.valueOf(flightData.getTravelTime()));
        statement.setString(7, flightData.getPointOfArrival());
        statement.setDate(8, Date.valueOf(flightData.getArrivalDate()));
        statement.setTime(9, Time.valueOf(flightData.getArrivalTime()));
    }

    private void printQueryException(Exception e) {
        System.out.println("Ошибка выполнения запроса: " + e.getMessage());
    }

    /**
     * Обновление информации о рейсах.
     * @param flightData информация о рейсе
     */
    public void update(FlightData flightData) {
        if (flightData.getId() == null) {
            create(flightData);
            return;
        }
        String sqlQuery = "UPDATE " + TABLE_NAME
                + " SET flight_number = ?,"
                + "airline = ?,"
                + "point_of_departure = ?,"
                + "departure_date = ?,"
                + "departure_time = ?,"
                + "travel_time = ?,"
                + "point_of_arrival = ?,"
                + "arrival_date = ?,"
                + "arrival_time = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setFilledStatement(flightData, statement);
            statement.setInt(10, flightData.getId());
            statement.execute();
        } catch (SQLException e) {
            printQueryException(e);
        }
    }

    /**
     * Удаление рейса по номеру рейса и авиакомпании.
     *
     * @param flightNumber номер рейса
     * @param airline      авиакомпания
     */
    public void deleteByFlightNumberAndAirline(String flightNumber, String airline) {
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE flight_number = ? AND airline = ? ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, flightNumber);
            statement.setString(2, airline);
            statement.execute();
        } catch (SQLException e) {
            printQueryException(e);
        }
    }

    /**
     * Удаление рейса по айди.
     *
     * @param id айди записи
     */
    public void deleteById(Integer id) {
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            printQueryException(e);
        }
    }

    /**
     * Получение рейса по номеру рейса и авиакомпании.
     *
     * @param flightNumber номер рейса
     * @param airline      авиакомпания
     * @return список
     */
    public List<FlightData> findByFlightNumberAndAirline(String flightNumber, String airline) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format
                    ("SELECT * FROM %s WHERE flight_number = '%s' AND airline = '%s' ",
                            TABLE_NAME, flightNumber, airline));
            return setFilledFlightDataList(resultSet);
        } catch (SQLException e) {
            printQueryException(e);
        }
        return new ArrayList<>();
    }

    /**
     * Получение рейса айди.
     *
     * @param id айди записи
     * @return список
     */
    public List<FlightData> findById(Integer id) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format
                    ("SELECT * FROM %s WHERE id = %d", TABLE_NAME, id));
            return setFilledFlightDataList(resultSet);
        } catch (SQLException e) {
            printQueryException(e);
        }
        return new ArrayList<>();
    }

    /**
     * Получение списка всех авиарейсов.
     *
     * @return список
     */
    public List<FlightData> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return setFilledFlightDataList(resultSet);
        } catch (SQLException e) {
            printQueryException(e);
        }
        return new ArrayList<>();
    }

    private List<FlightData> setFilledFlightDataList(ResultSet resultSet) throws SQLException {
        List<FlightData> flightData = new ArrayList<>();
        while (resultSet.next()) {

           flightData.add(new FlightData(
                   resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6).toLocalTime(),
                    resultSet.getTime(7).toLocalTime(),
                    resultSet.getString(8),
                    resultSet.getDate(9).toLocalDate(),
                    resultSet.getTime(10).toLocalTime()
           ));
        }
        return flightData;
    }

    /**
     * Очищение таблицы.
     */
    public void clearTable() {
        String sqlQuery = "DELETE FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (SQLException e) {
            printQueryException(e);
        }
    }
}
