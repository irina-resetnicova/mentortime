package com.endava.atf.transition.testDataUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage extends WebPageSteps {

    protected WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        super(driver); ///
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToBePresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            log.error("the time is up: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("the exception: " + e.getMessage());
            throw e;
        }
    }

    public void clickWithJavascript(WebElement element) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (TimeoutException e) {
            log.error("the time is up: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("the exception: " + e.getMessage());
            throw e;
        }
    }

    public void fillField(WebElement element, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
        } catch (TimeoutException e) {
            log.error("the time is up: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("the exception: " + e.getMessage());
            throw e;
        }
    }

    public void submitElement(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
            if (visibleElement != null) {

                visibleElement.submit();
            }
        } catch (TimeoutException e) {
            log.error("the time is up: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("the exception: " + e.getMessage());
            throw e;
        }

    }

    public void clickElement(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement visibleElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            if (visibleElement != null) {
                visibleElement.click();
            }
        } catch (TimeoutException e) {
            log.error("the time is up: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("the exception: " + e.getMessage());
            throw e;
        }
    }

    public String getTextOfString(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));

            return element.getText();

        } catch (TimeoutException e) {
            log.error("the time is up: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("the exception: " + e.getMessage());
            throw e;
        }
    }
}





