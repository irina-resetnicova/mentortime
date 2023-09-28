package com.endava.atf.transition.definitions;

import com.endava.atf.transition.utils.Helper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {
    @Before
    public static void setUp() {
        Helper.setUpDriver();
    }

    @After
    public static void tearDown(Scenario scenario) {

        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Helper.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Helper.tearDown();
    }

}
