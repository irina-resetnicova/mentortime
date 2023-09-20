package com.endava.atf.transition.definitions;

import com.endava.atf.transition.config.DataBase.DbManager;
import com.endava.atf.transition.utils.Helper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private DbManager dbManager;

    public Hooks(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    //    @Before
    public static void setUp() {

        Helper.setUpDriver();
    }

//    @After
    public static void tearDown(Scenario scenario) {

        //validate if scenario has failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Helper.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        Helper.tearDown();
    }
}
