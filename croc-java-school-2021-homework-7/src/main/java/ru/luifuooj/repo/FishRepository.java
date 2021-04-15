package ru.luifuooj.repo;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.luifuooj.model.Fish;

import java.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для доступа к таблице с данными о рыбах.
 */
public class FishRepository {

    private static final String TABLE_NAME = "fish";

    private EmbeddedDataSource dataSource;

    public FishRepository(EmbeddedDataSource dataSource) {
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
                                + " ("
                                + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY primary key,"
                                + "name VARCHAR(255), "
                                + "importDate DATE,"
                                + "importTime TIME,"
                                + "quantity INTEGER,"
                                + "isRare BOOLEAN"
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
     * Довавление новой рыбы в базу.
     *
     * @param fish рыба
     */
    public void create(Fish fish) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " (name, importDate, importTime, quantity, isRare)" +" VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setFilledStatement(fish, statement);
            statement.execute();
        } catch (Exception e) {
            printQueryException(e);
        }
    }

    private void setFilledStatement(Fish fish, PreparedStatement statement) throws SQLException {
        statement.setString(1, fish.getName());
        statement.setString(2, fish.getImportDate().toString());
        statement.setString(3, fish.getImportTime().toString());
        statement.setString(4, fish.getQuantity().toString());
        statement.setString(5, fish.isRare().toString());
    }

    /**
     * Обновление информации о рыбе.
     *
     * @param fish рыба
     */
    public void update(Fish fish) {
        if (fish.getId() == null) {
            create(fish);
            return;
        }
        String sqlQuery = "UPDATE " + TABLE_NAME
                + " SET name = ?,"
                + "importDate = ?,"
                + "importTime = ?,"
                + "quantity = ?,"
                + "isRare = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            setFilledStatement(fish, statement);
            statement.setInt(6, fish.getId());
            statement.execute();
        } catch (SQLException e) {
            printQueryException(e);
        }
    }

    /**
     * Удаление рыбы из базы по имени.
     *
     * @param name имя
     */
    public void deleteByName(String name) {
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE name = ? ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            printQueryException(e);
        }
    }

    /**
     * Получение списка всех рыб.
     *
     * @return список
     */
    public List<Fish> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return setFilledFishList(resultSet);
        } catch (SQLException e) {
            printQueryException(e);
        }
        return new ArrayList<>();
    }

    /**
     * Получение рыбы по имени.
     * @param name имя
     * @return список
     */
    public List<Fish> findByName(String name) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format
                    ("SELECT * FROM %s TABLE_NAME WHERE name = '%s' ", TABLE_NAME, name));
            return setFilledFishList(resultSet);
        } catch (SQLException e) {
            printQueryException(e);
        }
        return new ArrayList<>();
    }

    private List<Fish> setFilledFishList(ResultSet resultSet) throws SQLException {
        List<Fish> fishList = new ArrayList<>();
        while (resultSet.next()) {
            fishList.add(new Fish(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate(),
                    resultSet.getTime(4).toLocalTime(),
                    resultSet.getInt(5),
                    resultSet.getBoolean(6)));
        }
        return fishList;
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

    private void printQueryException(Exception e) {
        System.out.println("Ошибка выполнения запроса: " + e.getMessage());
    }
}
