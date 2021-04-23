package ru.luifuooj.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;

import java.sql.*;

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
}
