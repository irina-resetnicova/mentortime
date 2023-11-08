package com.endava.atf.transition.dao;

import com.endava.atf.transition.configs.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    private final Connection connection;

    public PreparedStatement insertCustomer(String firstname, String lastname, String email, String password) throws SQLException {

        String insertCustomer = "INSERT INTO oc_customer (customer_group_id, language_id,  firstname, lastname, email,telephone, password, custom_field, ip, status, safe, token, code, date_added) " +
                "VALUES (55, 1, ?, ?, ?, 378, ?, '', '172.19.0.1', 1, 0, 123, 123, '2023-09-29 13:36:22')";


        PreparedStatement psInsert = connection.prepareStatement(insertCustomer);

        psInsert.setString(1, firstname);
        psInsert.setString(2, lastname);
        psInsert.setString(3, email);
        psInsert.setString(4, password);


        return psInsert;

    }

    public UserDao() {
        connection = DbConnection.getInstance();
    }

    public PreparedStatement getCustomerByEmail(String email) throws SQLException {
        String selectCustomer = String.format("SELECT * FROM oc_customer WHERE email = '%s'", email);
        return connection.prepareStatement(selectCustomer);

    }

    public PreparedStatement deleteCustomerByEmail(String email) throws SQLException {
        String deleteCustomer = String.format("DELETE FROM oc_customer WHERE email = '%s' AND customer_id IN (SELECT customer_id FROM oc_customer WHERE email = '%s')", email, email);
        return connection.prepareStatement(deleteCustomer);
    }

    public PreparedStatement countUsersByEmail(String email) throws SQLException {
        String countUsers = String.format("SELECT email, COUNT(*) as user_count FROM oc_customer WHERE email = '%s' GROUP BY email", email);
        return connection.prepareStatement(countUsers);
    }

    public PreparedStatement deleteAllByEmail(String email) throws SQLException {
        String deleteAllByEmail = String.format("DELETE FROM oc_customer WHERE email = '%s' AND customer_id IN (SELECT customer_id FROM oc_customer WHERE email = '%s')", email, email);
        return connection.prepareStatement(deleteAllByEmail);
    }

    public PreparedStatement selectAllUsersByEmail(String email) throws SQLException {
        String selectAllUsers = String.format("SELECT email, COUNT(*) as user_count FROM oc_customer WHERE email = '%s' GROUP BY email", email);
        return connection.prepareStatement(selectAllUsers);
    }

    public PreparedStatement selectAllCustomers() throws SQLException {
        String selectAll = "SELECT * FROM oc_customer";
        return connection.prepareStatement(selectAll);
    }

    public PreparedStatement deleteAllFromCustomers() throws SQLException {
        String deleteAllCustomers = "DELETE FROM oc_customer";
        return connection.prepareStatement(deleteAllCustomers);
    }

}
