package com.endava.atf.transition.testDataUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Queries {
    private Connection connection;
    private PreparedStatement preparedStatementSelect;
    private PreparedStatement preparedStatementDelete;
    private PreparedStatement preparedStatementInsert;

    public PreparedStatement getPreparedStatementSelect() {
        return preparedStatementSelect;
    }

    public PreparedStatement getPreparedStatementDelete() {
        return preparedStatementDelete;
    }

    public PreparedStatement getPreparedStatementInsert() {
        return preparedStatementInsert;
    }

    private String firstnameValue = "John";
    private String lastnameValue = "Baiden";
    private String emailValue = "john@gmail.com";
    private String passwordValue = "1234";

    public void setParameters() throws SQLException {
        preparedStatementInsert.setString(1, firstnameValue);
        preparedStatementInsert.setString(2, lastnameValue);
        preparedStatementInsert.setString(3, emailValue);
        preparedStatementInsert.setString(4, passwordValue);
    }

    public Queries() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/opencart", "opencart", "opencart");

            String selectCustomer = "SELECT * FROM oc_customer WHERE email = 'john@gmail.com'";
            preparedStatementSelect = connection.prepareStatement(selectCustomer);

            String deleteCustomer = "DELETE FROM oc_customer WHERE email = 'john@gmail.com' AND customer_id IN (SELECT customer_id FROM oc_customer WHERE email = 'john@gmail.com');";
            preparedStatementDelete = connection.prepareStatement(deleteCustomer);

            String insertCustomer = "INSERT INTO oc_customer (customer_group_id, language_id,  firstname, lastname, email,telephone, password, custom_field, ip, status, safe, token, code, date_added) " +
                    "VALUES (55, 1, ?, ?, ?, 378, ?, '', '172.19.0.1', 1, 0, 123, 123, '2023-09-29 13:36:22')";
            preparedStatementInsert = connection.prepareStatement(insertCustomer);

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public String getFirstnameValue() {
        return firstnameValue;
    }

    public String getLastnameValue() {
        return lastnameValue;
    }

    public String getEmailValue() {
        return emailValue;
    }

    public String getPasswordValue() {
        return passwordValue;
    }


}
