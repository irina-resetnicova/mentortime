package com.endava.atf.transition.definitions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    private final WebDriverWait wait;

    // инжекшн через пикоконтейнер
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForElementToBePresent(By locator) {
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.err.println("the time is up: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("the exception: " + e.getMessage());
        }
    }

    public void clickWithJavascript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    //Метод fillField ожидает элемент, используя waitForElementToBePresent,
    // затем находит элемент по By локатору и вводит текст в это поле с помощью sendKeys.

    public void fillField(By locator, String text) {
//        waitForElementToBePresent(locator);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.sendKeys(text);
    }

    public void submitElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//        waitForElementToBePresent(locator);
        element.submit();
    }

    public void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//        waitForElementToBePresent(locator);
        element.click();
    }

    public String getTextOfString(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//        waitForElementToBePresent(locator);
        return element.getText();
    }

    public String getTextOfMessage(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//        waitForElementToBePresent(locator);
        return element.getText();

    }

}
