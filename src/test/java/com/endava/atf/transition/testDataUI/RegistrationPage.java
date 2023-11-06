package com.endava.atf.transition.testDataUI;

import com.endava.atf.transition.definitions.BasePage;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

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

    @FindBy(xpath = ("//h1[text()='Your Account Has Been Created!']"))
    public WebElement inscriptionYourAccountHasBeenCreated;

    @FindBy(xpath = ("//a[contains(text(),'Continue')]"))
    public WebElement btnContinue;

    @FindBy(xpath = ("/html/body/nav/div/div[2]/ul/li[2]/div/a/span"))
    public WebElement btnDropDownToggleLocator;

    @FindBy(xpath = ("/html/body/nav/div/div[2]/ul/li[2]/div/ul/li[5]/a"))
    public WebElement logoutLocator;

    @FindBy(xpath = ("/html/body/main/div[2]/div/div/div/a"))
    public WebElement btnContinueAccountLogout;

    @FindBy(xpath = ("//*[@id=\"alert\"]"))
    public WebElement alertEmailAddressIsAlreadyRegistered;

    @FindBy(xpath = ("//div[@id='error-firstname']"))
    public WebElement firstNameError;


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @SneakyThrows
    public void fillRegistrationForm(String firstName, String lastName, String email, String password) {
        fillField(inputFirstNameLocator, firstName);
        fillField(inputLastNameLocator, lastName);
        fillField(inputEmailLocator, email);
        fillField(inputPasswordLocator, password);

        waitForElementToBePresent(agreeSliderLocator);

        clickWithJavascript(agreeSliderLocator);

        submitElement(btnContinueRegister);
    }


//    public String getActualInscription() {
//        return getTextOfString(inscriptionYourAccountHasBeenCreated);
//    }

    public WebElement getInscriptionYourAccountHasBeenCreated() {
        return inscriptionYourAccountHasBeenCreated;
    }


    public void yourAccountHasBeenCreated() {
        clickElement( btnContinue);
        clickElement( btnDropDownToggleLocator);
        clickElement( logoutLocator);
        clickElement( btnContinueAccountLogout);
    }

//    public By getLocatorByName(String warningMessage) {
//        By warningMessageLocator = By.xpath(String.format(findByNamePattern, warningMessage));
//        return warningMessageLocator;
//    }


    public WebElement getFirstNameError() {
        return firstNameError;
    }
}











