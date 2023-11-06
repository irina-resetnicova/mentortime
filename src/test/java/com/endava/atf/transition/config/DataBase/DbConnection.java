package com.endava.atf.transition.config.DataBase;

import com.endava.atf.transition.definitions.StepDefinitionsAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
    private static final Logger log = LogManager.getLogger(DbConnection.class);
    private static Connection connection = null;

    private DbConnection() {
    }

    private static Connection initialization() throws SQLException, ClassNotFoundException {
        final String DB_URL = "jdbc:mysql://localhost:3306/opencart";
        final String USER = "opencart";
        final String PASS = "opencart";

        // read values from AppConfiguration
        // установливается соединение Connection с базой данных(параметры) через JDBC drivers
        connection = DriverManager.getConnection(DB_URL, USER, PASS);

        log.info("Connected to database");
        return connection;
    }

    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = initialization();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if(connection!=null)
        connection = null;
    }

    public static Connection getConnection() {
        return connection;
    }
}


