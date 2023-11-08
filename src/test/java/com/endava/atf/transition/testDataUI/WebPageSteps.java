package com.endava.atf.transition.testDataUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebPageSteps {
    protected WebDriver driver;

    public WebPageSteps(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void clickElement(WebElement element);

    public abstract void submitElement(WebElement element);

    public abstract void clickWithJavascript(WebElement element);

    public abstract void fillField(WebElement element, String text);

    public abstract void waitForElementToBePresent(WebElement element);


}
