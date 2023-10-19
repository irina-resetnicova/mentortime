package com.endava.atf.transition.testDataUI;

import java.sql.*;

public class QueryDelete {
    private Connection connection;
    private PreparedStatement preparedStatementDeleteMy;
    private PreparedStatement preparedStatementDeleteAll;

    public PreparedStatement getPreparedStatementDeleteMy() {
        return preparedStatementDeleteMy;
    }

    public PreparedStatement getPreparedStatementDeleteAll() {
        return preparedStatementDeleteAll;
    }

    public QueryDelete() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/opencart", "opencart", "opencart");

            String emailsToDelete = "email@gmail.com,emailemail@gmail.com,gmailemailemail@gmail.com";

            String deleteMyCustomers = "DELETE FROM oc_customer " +
                    "WHERE email IN (" + emailsToDelete + ") " +
                    "AND customer_id IN (SELECT customer_id FROM oc_customer WHERE email IN (" + emailsToDelete + "))";

            preparedStatementDeleteMy = connection.prepareStatement(deleteMyCustomers);

            String deleteAllCustomers = "DELETE FROM oc_customer";
            preparedStatementDeleteAll = connection.prepareStatement(deleteAllCustomers);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
