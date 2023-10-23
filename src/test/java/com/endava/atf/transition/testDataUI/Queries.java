package com.endava.atf.transition.testDataUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Queries {
    private Connection connection;
    private PreparedStatement psSelect;
    private PreparedStatement psDelete;
    private PreparedStatement psInsert;
    private PreparedStatement psCount;
    private PreparedStatement psDeleteAll;
    private PreparedStatement psSelectAllUsers;
    private PreparedStatement psCountAll;

    public PreparedStatement getPsCountAll() {
        return psCountAll;
    }

    public PreparedStatement getPsSelectAllUsers() {
        return psSelectAllUsers;
    }

    public PreparedStatement getPsDeleteAll() {
        return psDeleteAll;
    }

    public PreparedStatement getPsCount() {
        return psCount;
    }

    public PreparedStatement getPsSelect() {
        return psSelect;
    }

    public PreparedStatement getPsDelete() {
        return psDelete;
    }

    public PreparedStatement getPsInsert() {
        return psInsert;
    }

    private String firstnameValue = "John";
    private String lastnameValue = "Baiden";
    private String emailValue = "john@gmail.com";
    private String passwordValue = "1234";

    public void setParameters() throws SQLException {
        psInsert.setString(1, firstnameValue);
        psInsert.setString(2, lastnameValue);
        psInsert.setString(3, emailValue);
        psInsert.setString(4, passwordValue);
    }

    public Queries() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/opencart", "opencart", "opencart");

            String selectCustomer = "SELECT * FROM oc_customer WHERE email = 'john@gmail.com'";
            psSelect = connection.prepareStatement(selectCustomer);

            String deleteCustomer = "DELETE FROM oc_customer WHERE email = 'john@gmail.com' AND customer_id IN (SELECT customer_id FROM oc_customer WHERE email = 'john@gmail.com');";
            psDelete = connection.prepareStatement(deleteCustomer);

            String insertCustomer = "INSERT INTO oc_customer (customer_group_id, language_id,  firstname, lastname, email,telephone, password, custom_field, ip, status, safe, token, code, date_added) " +
                    "VALUES (55, 1, ?, ?, ?, 378, ?, '', '172.19.0.1', 1, 0, 123, 123, '2023-09-29 13:36:22')";
            psInsert = connection.prepareStatement(insertCustomer);

//            String countUsers ="SELECT email, COUNT(*) as user_count FROM oc_customer WHERE email = 'john@gmail.com' GROUP BY email HAVING user_count > 1";
            String countUsers ="SELECT email, COUNT(*) as user_count FROM oc_customer WHERE email = 'john@gmail.com' GROUP BY email";
            psCount = connection.prepareStatement(countUsers);



            String deleteAllCustomers = "DELETE FROM oc_customer WHERE email = 'petrov@gmail.com' AND customer_id IN (SELECT customer_id FROM oc_customer WHERE email = 'petrov@gmail.com');";
            psDeleteAll = connection.prepareStatement(deleteAllCustomers);

            String selectAllUsers ="SELECT email, COUNT(*) as user_count FROM oc_customer WHERE email = 'petrov@gmail.com' GROUP BY email";
            psSelectAllUsers = connection.prepareStatement(selectAllUsers);

            String countAllUsers ="SELECT email, COUNT(*) as user_count FROM oc_customer WHERE email = 'john@gmail.com' GROUP BY email";
             psCountAll = connection.prepareStatement(countAllUsers);

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
