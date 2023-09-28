package com.endava.atf.transition.config;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverFactory {
    private static final String CHROMEDRIVER_PATH = "src/test/resources/chromedriver.exe";

    private static WebDriver driver;
    private static Browser currentBrowser;

    static {
        initChromeDriverProperties();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver getDriver(Browser browser) {
        if (null != driver) {
            if (browser == currentBrowser) {
                return driver;
            }
            driver.quit();
        }

        currentBrowser = browser;

        switch (browser) {
            case CHROME:
                //https://www.youtube.com/watch?v=M3RyBvUTOpk
//                System.setProperty("webdriver.http.factory", "jdk-http-client");
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("start-maximized");
//                options.addArguments("enable-automation");
//                options.addArguments("–disable-infobars");
//                options.addArguments("--remote-allow-origins=*");
//                options.addArguments("--remote-allow-origins=*");
//                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//                options.addArguments("--homepage \"http://localhost:8080/en-gb?route=account/\"");
//                options.addArguments("-no-startup-window");
//                driver = new ChromeDriver();
                break;
            default:
                throw new IllegalArgumentException("Must supply a supported Browser type!");
        }
        return driver;
    }

    private static void initChromeDriverProperties() {
        if (System.getProperty("webdriver.chrome.driver") == null && new File(CHROMEDRIVER_PATH).exists()) {
            System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        }
        if (System.getProperty("webdriver.chrome.driver") != null) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        } else {
            System.err.println("WARNING: Cannot locate Chrome WebDriver!");
        }
    }
}