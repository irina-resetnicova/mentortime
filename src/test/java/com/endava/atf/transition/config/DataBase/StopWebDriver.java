package com.endava.atf.transition.config.DataBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StopWebDriver {
    public static void main(String[] args) {
        // Установите путь к драйверу Chrome (ChromeDriver) - убедитесь, что драйвер совместим с версией вашего браузера.
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        // Инициализируйте объект WebDriver для браузера Chrome
        WebDriver driver = new ChromeDriver();

        // Откройте веб-страницу
//        driver.get("http://localhost:8080/");

        // Выполните какие-либо действия на странице...

        // Остановите WebDriver и закройте браузер
        driver.quit();
    }
}