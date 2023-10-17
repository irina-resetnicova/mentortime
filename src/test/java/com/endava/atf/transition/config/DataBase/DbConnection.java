/*create class: DbConnection based on JDBC library
(https://stackoverflow.com/questions/74183544/get-connection-with-singleton-pattern)

        DbConnection class must implement Singleton pattern.*/

package com.endava.atf.transition.config.DataBase;

import java.sql.Connection; // подготавливает подключение к базе
import java.sql.DriverManager; //обеспечивает подключение черезJDBC drivers
import java.sql.SQLException; //обрабатывает SQL-ошибки, возникающие при взаимодействии приложении и базы данных.


public class DbConnection {

    private static Connection connection = null;

    // инициализируем connection
    private static Connection initialization() throws SQLException, ClassNotFoundException {
        final String DB_URL = "jdbc:mysql://localhost:3306/opencart";
        final String USER = "opencart";
        final String PASS = "opencart";

// read values from AppConfiguration

        connection = DriverManager.getConnection(DB_URL, USER, PASS); // установливается соединение Connection с базой данных(параметры) через JDBC drivers

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



//JDBC driver уже есть в dependency
//<dependency>
//<groupId>mysql</groupId>
//<artifactId>mysql-connector-java</artifactId>
//<version>8.0.16</version>
//</dependency>


