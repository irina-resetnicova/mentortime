package com.endava.atf.transition.config.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    private static Connection connection = null;

    // инициализируем connection
    private static Connection initialization() throws SQLException, ClassNotFoundException {
        final String DB_URL = "jdbc:mysql://localhost:3306/opencart";
        final String USER = "opencart";
        final String PASS = "opencart";

        // read values from AppConfiguration
        // установливается соединение Connection с базой данных(параметры) через JDBC drivers
        connection = DriverManager.getConnection(DB_URL, USER, PASS);

        System.out.println("Connected to database");
        return connection;
    }

    //создаем connection
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
}


    //Одиночный экземпляр: Синглтон гарантирует, что класс имеет только один экземпляр, и этот экземпляр может быть получен из любой части программы.
