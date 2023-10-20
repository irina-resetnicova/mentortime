package com.endava.atf.transition.testDataUI;

import java.sql.*;

public class QueryDelete {
    private Connection connection;
//    private PreparedStatement psDeleteAllCustomers;
    private PreparedStatement psDeleteAll;

//    public PreparedStatement getPsDeleteAllCustomers() {
//        return psDeleteAllCustomers;
//    }

    public PreparedStatement getPsDeleteAll() {
        return psDeleteAll;
    }

    public QueryDelete() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/opencart", "opencart", "opencart");

//            String emailsToDelete = "email@gmail.com,emailemail@gmail.com,gmailemailemail@gmail.com";
//
//            String deleteMyCustomers = "DELETE FROM oc_customer " +
//                    "WHERE email IN (" + emailsToDelete + ") " +
//                    "AND customer_id IN (SELECT customer_id FROM oc_customer WHERE email IN (" + emailsToDelete + "))";
//
//            psDeleteAllCustomers = connection.prepareStatement(deleteMyCustomers);

            String deleteAllCustomers = "DELETE FROM oc_customer";
           psDeleteAll = connection.prepareStatement(deleteAllCustomers);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

