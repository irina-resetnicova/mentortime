package com.endava.atf.transition.testDataUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ("//div[@class = 'col-sm-10']//input[@id ='input-firstname']"))
    public WebElement inputFirstNameLocator;

    @FindBy(xpath = ("//div[@class = 'col-sm-10']//input[@id ='input-lastname']"))
    public WebElement inputLastNameLocator;

    @FindBy(xpath = ("//div[@class = 'col-sm-10']//input[@id ='input-email']"))
    public WebElement inputEmailLocator;

    @FindBy(xpath = ("//div[@class = 'col-sm-10']//input[@id ='input-password']"))
    public WebElement inputPasswordLocator;

    @FindBy(xpath = ("//*[@id='form-register']/div/div/input"))
    public WebElement agreeSliderLocator;

    @FindBy(xpath = ("//*[@id=\"form-register\"]/div/button"))
    public WebElement btnContinueRegister;

    @FindBy(xpath = ("//*[@id=\"alert\"]"))
    public WebElement alertEmailAddressIsAlreadyRegistered;

    @FindBy(xpath = ("//div[@id='error-firstname']"))
    public WebElement firstNameError;


    public void fillRegistrationForm(String firstName, String lastName, String email, String password) {
        fillField(inputFirstNameLocator, firstName);
        fillField(inputLastNameLocator, lastName);
        fillField(inputEmailLocator, email);
        fillField(inputPasswordLocator, password);

        waitForElementToBePresent(agreeSliderLocator);
        clickWithJavascript(agreeSliderLocator);
        submitElement(btnContinueRegister);
    }

    public WebElement getFirstNameError() {
        return firstNameError;
    }

    public WebElement getAlertEmailAddressIsAlreadyRegistered() {
        return alertEmailAddressIsAlreadyRegistered;
    }
}











