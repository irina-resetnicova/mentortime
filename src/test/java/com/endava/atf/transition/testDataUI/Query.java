package com.endava.atf.transition.testDataUI;

import com.endava.atf.transition.config.DataBase.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query {
    private final PreparedStatement psDeleteAll;

    public PreparedStatement getPsDeleteAll() {
        return psDeleteAll;
    }

    public Query() {
        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/opencart", "opencart", "opencart");
            Connection connection = DbConnection.getInstance();


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
