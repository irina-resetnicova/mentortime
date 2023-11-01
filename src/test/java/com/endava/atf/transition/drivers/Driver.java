package com.endava.atf.transition.drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver {

    private static WebDriver driver;
    public static String driverPath = "src/test/resources/chromedriver.exe";
    private static final Logger log = LogManager.getLogger(Driver.class);

    public static void setUpDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", driverPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("enable-automation");
            options.addArguments("--disable-infobars");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--port=8080");

            driver = new ChromeDriver(options);
            log.info("Driver has been initialized.");
        } else {
            log.info("Driver is already initialized.");
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver has not been initialized. Call setUpDriver() first.");
        }
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null; // Reset the driver instance
            log.info("WebDriver has been quit and reset.");
        }

        assert driver != null;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // неявные  ожидания
    }
}
