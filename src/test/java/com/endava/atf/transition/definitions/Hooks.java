package com.endava.atf.transition.definitions;

import com.endava.atf.transition.utils.Helper;
import io.cucumber.java.*;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @Before
    public void setUpLogsBefore() {
        log.info("Test started");
    }
    @After
    public void setUpLogsAfter() {
        log.info("Test finished");
    }


    @Before("@UI")
    public static void setUp() {
        Helper.setUpDriver();
    }

    @Before("@UI")
    public static void info() {
        System.out.println("Test has been started");

    }

    @After("@UI")
    public static void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Helper.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
//        Helper.tearDown();


    }


    @After("@API")
    public static void tearDown() {
        Helper.tearDown();
    }

//    @Before("@API")
//    public void setup() {
//        RestAssured.port = 443;
//    }

    @Before("@UI")
    public void setupPort() {
        RestAssured.port = 8080;
    }
}







