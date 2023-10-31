package com.endava.atf.transition.definitions;
import com.endava.atf.transition.drivers.Driver;
import com.endava.atf.transition.testDataUI.QueryDelete;
import com.mysql.cj.Query;
import io.cucumber.java.*;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;

import java.sql.SQLException;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);
    static QueryDelete queryDeleteAll;
    private static int counter = 0;


    static {
        try {
            queryDeleteAll = new QueryDelete();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static String currentScenarioName = null;

    @Before("@API")
    public void setUpApi() {
        log.info("Test started");
        RestAssured.requestSpecification = ApiSpecifications.getRequestSpecification();
        RestAssured.responseSpecification = ApiSpecifications.getResponseSpecification();
//        RestAssured.port = 443;
    }

    @After("@API")
    public static void tearDownAPI() {
        Driver.tearDown();
        log.info("Test finished");
    }

    @Before("@UI")
    public void setUp() {
            log.info("Test started");
//        RestAssured.port = 8080;
        Driver.setUpDriver();
    }

    @After("@UI")
    public static void tearDown(){
        log.info("Test finished");
//        Driver.tearDown();
        }

    @Before("@DBClean")
    public static void before_db_clean(Scenario scenario) throws SQLException {
        // Runs before all scenarios
        if(currentScenarioName == null)
        {
            currentScenarioName = scenario.getName();
            queryDeleteAll.getPsDeleteAll().executeUpdate();
        } else {
            if(!currentScenarioName.equals(scenario.getName())) {
                queryDeleteAll.getPsDeleteAll().executeUpdate();
                currentScenarioName = scenario.getName();
            }
        }

    }

//    @Before("@CleanDB")
//    public static void deleteAllUsers() throws SQLException {
//        int rsDeleteAll = queryDeleteAll.getPsDeleteAll().executeUpdate();
//
//    }
//    @BeforeClass
//    public void cleanDB() throws SQLException {
//            int rsDeleteAll = queryDeleteAll.getPsDeleteAll().executeUpdate();
//
//    }
    }













