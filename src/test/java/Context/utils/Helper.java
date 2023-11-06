package Context.utils;

import org.openqa.selenium.WebDriver;

public class Helper {

    private static WebDriver driver;

    public static void setDriver(WebDriver chromedriver) {
        driver = chromedriver;
    }

    public static void openRegisterPage() {
        driver.get("http://localhost:8080/en-gb?route=account/register");
    }

}
