package com.endava.atf.transition.utils;

import com.endava.atf.transition.config.DriverProvider;
import com.endava.atf.transition.config.WebDriverFactory;
import org.openqa.selenium.WebDriver;


public class Helper {

    private static Helper helperClass;

    private static WebDriver driver;


    private Helper() {
        driver = WebDriverFactory.getDriver(DriverProvider.CHROME);
    }

    public static void openPage() {
        driver.get("http://localhost:8080/");
    }

    public static void openLoginPage() {
        driver.get("http://localhost:8080/en-gb?route=account/login");
    }


    public static WebDriver getDriver() {
        return driver;
    }

    public static void setUpDriver() {

     driver = WebDriverFactory.getDriver(DriverProvider.CHROME);
    }

    public static void tearDown() {

        if(driver!=null) {
            driver.close();
            driver.quit();
        }

        helperClass = null;
    }

}
