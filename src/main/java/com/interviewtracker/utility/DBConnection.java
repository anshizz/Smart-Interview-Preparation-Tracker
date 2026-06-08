package com.interviewtracker.utility;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            // Attempt to load from Environment Variables first (for Docker/Render deployment)
            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USERNAME");
            String dbPass = System.getenv("DB_PASSWORD");

            // Fallback to local properties
            if (dbUrl == null || dbUrl.isEmpty()) {
                Properties props = new Properties();
                try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
                    if (input != null) {
                        props.load(input);
                        dbUrl = props.getProperty("db.url");
                        dbUser = props.getProperty("db.username");
                        dbPass = props.getProperty("db.password");
                    }
                }
            }

            config.setJdbcUrl(dbUrl);
            config.setUsername(dbUser);
            config.setPassword(dbPass);
            
            // Connection Pool settings
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(30000);
            config.setMaxLifetime(1800000);
            config.setConnectionTimeout(30000);

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database connection pool", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
