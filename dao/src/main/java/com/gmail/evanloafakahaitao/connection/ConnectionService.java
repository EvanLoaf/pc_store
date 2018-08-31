package com.gmail.evanloafakahaitao.connection;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    private Connection connection = null;

    private static ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    public ConnectionService() {
        try {
            Class.forName(configurationManager.getProperty(DatabaseProperties.DATABASE_DRIVER_NAME));
            System.out.println("Sql JDBC driver was successfully loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Sql JDBC driver loading failed");
            e.printStackTrace();
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
            System.out.println("Connection to DB established");
        } catch (SQLException e) {
            System.out.println("Couldn\'t establish connection to DB");
            e.printStackTrace();
        }
        return connection;
    }
}
