package com.endava.atf.transition.definitions;

import Context.ScenarioContext;
import com.endava.atf.transition.drivers.Driver;
import com.endava.atf.transition.testDataUI.UserDao;
import com.endava.atf.transition.utils.Helper;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.config.MultiPartConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);

    private UserDao query;
    private static final int counter = 0;
    private ScenarioContext scenarioContext = ScenarioContext.getInstance();

    public Hooks(UserDao query) {
        this.query = query;
    }

    private static String currentScenarioName = null;

    @Before("@API")
    public void setUpApi() {
        log.info("Test started");
        scenarioContext.clearContext();
        ApiSpecifications.getRequestSpecification();
//        RestAssured.requestSpecification = ApiSpecifications.getRequestSpecification();
//        RestAssured.responseSpecification = ApiSpecifications.getResponseSpecification();
//        RestAssured.port = 443;
    }

    @Before("@UI")
    public void setUp(Scenario scenario) {
        String scenarioName = scenario.getName();
        System.out.println("\u001B[32mSCENARIO: " + scenarioName + "\u001B[0m");
        // log.info("Scenario name: " + scenarioName);
        Helper.setDriver(Driver.getDriver());

        scenarioContext.clearContext();

        log.info("Test started");
    }

    @Before("@DBClean")
    public void before_db_clean(Scenario scenario) throws SQLException {
        if (currentScenarioName == null) {
            currentScenarioName = scenario.getName();
            PreparedStatement psDeleteAll = query.deleteAllFromCustomers();
            psDeleteAll.executeUpdate();
        } else {
            if (!currentScenarioName.equals(scenario.getName())) {
                PreparedStatement psDeleteAll = query.deleteAllFromCustomers();
                psDeleteAll.executeUpdate();
                currentScenarioName = scenario.getName();
            }
        }
    }


    @After("@UI")
    public void closeWebdriver() {
        log.info("Scenario finished");

    }

    @AfterAll
    public static void tearDown() {
        log.info("Test finished");
        if (Driver.getDriver() != null) {
            Driver.tearDown(); // Закрывает браузер
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














