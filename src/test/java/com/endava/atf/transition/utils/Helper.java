package com.endava.atf.transition.utils;

import java.util.concurrent.TimeUnit;
import com.endava.atf.transition.config.Browser;
import com.endava.atf.transition.config.WebDriverFactory;
import org.openqa.selenium.WebDriver;


public class Helper {

    private static Helper helperClass;

    private static WebDriver driver;
//    public final static int TIMEOUT = 10;

    private Helper() {
        driver = WebDriverFactory.getDriver(Browser.CHROME);
    }

    public static void openPage() {
        driver.get("http://localhost:8080/en-gb?route=common/home");
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setUpDriver() {

        if (helperClass==null) {

            helperClass = new Helper();
        }
    }

    public static void tearDown() {

        if(driver!=null) {
            driver.close();
            driver.quit();
        }

        helperClass = null;
    }

}
