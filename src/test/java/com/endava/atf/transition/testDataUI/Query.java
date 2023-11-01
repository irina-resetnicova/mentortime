package com.endava.atf.transition.testDataUI;

import java.sql.*;

public class Query {
    private final PreparedStatement psDeleteAll;



    public PreparedStatement getPsDeleteAll() {
        return psDeleteAll;
    }

    public Query() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/opencart", "opencart", "opencart");


            String deleteAllCustomers = "DELETE FROM oc_customer";
           psDeleteAll = connection.prepareStatement(deleteAllCustomers);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

//    private PreparedStatement psDeleteAllCustomers;
//    public PreparedStatement getPsDeleteAllCustomers() {
//        return psDeleteAllCustomers;
//    }
//            String emailsToDelete = "email@gmail.com,emailemail@gmail.com,gmailemailemail@gmail.com";
//
//            String deleteMyCustomers = "DELETE FROM oc_customer " +
//                    "WHERE email IN (" + emailsToDelete + ") " +
//                    "AND customer_id IN (SELECT customer_id FROM oc_customer WHERE email IN (" + emailsToDelete + "))";
//
//            psDeleteAllCustomers = connection.prepareStatement(deleteMyCustomers);
