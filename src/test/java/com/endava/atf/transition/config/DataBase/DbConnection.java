/*create class: DbConnection based on JDBC library
(https://stackoverflow.com/questions/74183544/get-connection-with-singleton-pattern)

        DbConnection class must implement Singleton pattern.*/

package com.endava.atf.transition.config.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection conn = null;

    private static Connection init() throws SQLException, ClassNotFoundException {
        final String DB_URL = "jdbc:mysql://localhost:3306/opencart";
        final String USER = "opencart";
        final String PASS = "opencart";
        final String DRIVER = "com.mysql.jdbc.Driver";

//        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        System.out.println("Connected to database");
        return conn;
    }
    public static Connection getInstance() {
        if (conn == null) {
            try {
                conn = init();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
}
