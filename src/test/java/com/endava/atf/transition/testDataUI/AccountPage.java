package com.endava.atf.transition.testDataUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this); // Инициализация элементов страницы
    }

    @FindBy(xpath = ("/html/body/nav/div/div[2]/ul/li[2]/div/a/span"))
    public WebElement btnDropDownToggleLocator;

    @FindBy(xpath = ("/html/body/nav/div/div[2]/ul/li[2]/div/ul/li[5]/a"))
    public WebElement logoutLocator;

    @FindBy(xpath = ("/html/body/main/div[2]/div/div/div/a"))
    public WebElement btnContinueAccountLogout;

    public void accountPageLogOut() {
        clickElement(btnDropDownToggleLocator);
        clickElement(logoutLocator);
        clickElement(btnContinueAccountLogout);
    }

}











