package ru.luifuooj.dbprovider;

import org.apache.derby.jdbc.EmbeddedDataSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataSourceProvider {

    /**
     * Data source.
     */
    private EmbeddedDataSource dataSource = null;

    /**
     * Параметры конфигурации.
     */
    private final Map<String, String> properties = new HashMap<>();

    public DataSourceProvider() throws IOException {
        loadProperties();
    }

    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(
                    Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream("application.properties"));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Error occurred during loading properties");
            throw e;
        }
    }

    public EmbeddedDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new EmbeddedDataSource();
            dataSource.setDatabaseName(properties.get("database.name"));
            String username = properties.get("database.username");
            String password = properties.get("database.password");
            if (username != null && !username.isEmpty()
                    && password != null && !password.isEmpty()) {
                dataSource.setUser(username);
                dataSource.setPassword(password);
            }
            dataSource.setCreateDatabase("create");
        }

        return dataSource;
    }
}
