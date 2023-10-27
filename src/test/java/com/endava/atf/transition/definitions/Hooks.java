package com.endava.atf.transition.definitions;

import com.endava.atf.transition.testDataUI.QueryDelete;
import com.endava.atf.transition.utils.Helper;
import io.cucumber.java.*;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.sql.SQLException;


public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);
    static QueryDelete queryDeleteAll;

    static {
        try {
            queryDeleteAll = new QueryDelete();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUpLogsBefore() {
        log.info("Test started");
    }

    @After
    public void setUpLogsAfter() {
        log.info("Test finished");
    }

    @Before("@API")
    public void setUpApi() {
        RestAssured.requestSpecification = ApiSpecifications.getRequestSpecification();
        RestAssured.responseSpecification = ApiSpecifications.getResponseSpecification();
//        RestAssured.port = 443;
    }


    @After("@API")
    public static void tearDown() {
        Helper.tearDown();
    }


    @Before("@UI")
    public void setUp() {
        RestAssured.port = 8080;
        Helper.setUpDriver();
    }

    @After("@UI")
    public static void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Helper.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
//        Helper.tearDown();


//    @Before("@CleanDB")
//    public static void deleteAllUsers() throws SQLException {
//        int rsDeleteAll = queryDeleteAll.getPsDeleteAll().executeUpdate();
//
//    }
    }
}












