package com.endava.atf.transition.definitions;

import com.endava.atf.transition.drivers.Driver;
import com.endava.atf.transition.testDataUI.Query;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);
    private Query queryAll;
    private static final int counter = 0;
    private WebDriver driver;

//    static {
//        try {
//            queryAll = new Query();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public Hooks(Query queryAll) {
        this.queryAll = queryAll;
    }

    private static String currentScenarioName = null;

    @Before("@API")
    public void setUpApi() {
        log.info("Test started");
        RestAssured.requestSpecification = ApiSpecifications.getRequestSpecification();
        RestAssured.responseSpecification = ApiSpecifications.getResponseSpecification();
//        RestAssured.port = 443;
    }


    @After("@UI")
    public void closeWebdriver() {
        log.info("Scenario finished");
    }

    @AfterAll
    public static void tearDown() {
        log.info("Test finished");
        if (Driver.getDriver() != null) {
////            Driver.getDriver().close(); // Закрывает tab
//            Driver.getDriver().quit(); // Закрывает браузер
        }
    }

    @Before("@UI")
    public void setUp(Scenario scenario) {
        Driver.setUpDriver();
        driver = Driver.getDriver();
        String scenarioName = scenario.getName();
        log.info("Scenario name: " + scenarioName);
        log.info("Test started");
    }


    @Before("@DBClean")
    public void before_db_clean(Scenario scenario) throws SQLException {
        if(currentScenarioName == null)
        {
            currentScenarioName = scenario.getName();
            queryAll.getPsDeleteAll().executeUpdate();
        } else {
            if(!currentScenarioName.equals(scenario.getName())) {
                queryAll.getPsDeleteAll().executeUpdate();
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













