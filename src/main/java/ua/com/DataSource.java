package ua.com;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ua.com.util.PropertiesReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private static final HikariConfig config;
    private static final HikariDataSource dataSource;
    private static Properties properties = PropertiesReader.readProperties("hikari.properties");
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("jdbcUrl"));
        config.setUsername(properties.getProperty("username"));
        config.setPassword(properties.getProperty("password"));
        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
