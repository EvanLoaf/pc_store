package com.gmail.evanloafakahaitao.dao.connection;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.DatabaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    private Connection connection = null;

    private static ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private static final Logger logger = LogManager.getLogger(ConnectionService.class);

    public ConnectionService() {
        try {
            Class.forName(configurationManager.getProperty(DatabaseProperties.DATABASE_DRIVER_NAME));
            logger.info("Sql JDBC driver was successfully loaded");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(
                    configurationManager.getProperty(DatabaseProperties.DATABASE_URL),
                    configurationManager.getProperty(DatabaseProperties.DATABASE_USERNAME),
                    configurationManager.getProperty(DatabaseProperties.DATABASE_PWD)
            );
            if (connection == null) {
                throw new RuntimeException("Cant gain access to DB");
            }
            logger.info("Connection to DB established");
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return connection;
    }
}
