package com.endava.atf.transition.utils;

import com.endava.atf.transition.drivers.Driver;
import org.openqa.selenium.WebDriver;

public class Helper {

    private static final WebDriver driver = Driver.getDriver();

    public static void openRegisterPage() {
        driver.get("http://localhost:8080/en-gb?route=account/register");
    }

    public static void openYourAccountHasBeenCreatedPage() {
        driver.get("http://localhost:8080/en-gb?route=account/success&customer_token");
    }


}
