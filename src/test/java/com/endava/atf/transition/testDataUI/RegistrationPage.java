package com.endava.atf.transition.testDataUI;

import com.endava.atf.transition.definitions.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    private final By inputFirstNameLocator = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-firstname']");
    private final By inputLastNameLocator = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-lastname']");
    private final By inputEmailLocator = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-email']");
    private final By inputPasswordLocator = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-password']");
    private final By agreeSliderLocator = By.xpath("//*[@id='form-register']/div/div/input");
    private final By btnContinueRegister = By.xpath("//*[@id=\"form-register\"]/div/button");  //  Register page

    private final By inscriptionYourAccountHasBeenCreated = By.xpath("//h1[text()='Your Account Has Been Created!']");

    private final By btnContinue = By.xpath("//*[@id=\"content\"]/div/a"); //  YourAccountHasBeenCreated page

    private final By btnDropDownToggleLocator = By.xpath("/html/body/nav/div/div[2]/ul/li[2]/div/a/span"); // dropdown "My Account"
    private final By logoutLocator = By.xpath("/html/body/nav/div/div[2]/ul/li[2]/div/ul/li[5]/a");

    private final By btnContinueAccountLogout = By.xpath("/html/body/main/div[2]/div/div/div/a"); // Account Logout page

    private final By alertEmailAddressIsAlreadyRegistered = By.xpath("//*[@id=\"alert\"]");

    private final String findByNamePattern = "//*[@id=\"error-firstname\"]";

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String password) {

        fillField(inputFirstNameLocator, firstName);

        fillField(inputLastNameLocator, lastName);

        fillField(inputEmailLocator, email);

        fillField(inputPasswordLocator, password);

        waitForElementToBePresent(agreeSliderLocator);
        clickWithJavascript(driver.findElement(agreeSliderLocator));

        waitForElementToBePresent(btnContinueRegister);
        submitElement(driver.findElement(btnContinueRegister));

    }

    public void yourAccountHasBeenCreated() {
        waitForElementToBePresent(inscriptionYourAccountHasBeenCreated);

        waitForElementToBePresent(btnContinue);
        clickElement(driver.findElement(btnContinue));

        waitForElementToBePresent(btnDropDownToggleLocator);
        clickElement(driver.findElement(btnDropDownToggleLocator));

        waitForElementToBePresent(logoutLocator);
        clickElement(driver.findElement(logoutLocator));

        waitForElementToBePresent(getBtnContinueAccountLogout());
        clickElement(driver.findElement(getBtnContinueAccountLogout()));

        String currentURL = driver.getCurrentUrl();
        System.out.println("testtesttest  " + currentURL);

    }


    public By getBtnContinueAccountLogout() {
        return btnContinueAccountLogout;
    }


    public String getFindByNamePattern() {
        return findByNamePattern;
    }

    //First Name must be between 1 and 32 characters!

    public By getLocatorByName(String warningMessage) {
        By warningMessageLocator = By.xpath(String.format(findByNamePattern, warningMessage));
        return warningMessageLocator;
    }

    public By getLogoutLocator() {
        return logoutLocator;
    }

    public By getBtnDropDownToggleLocator() {
        return btnDropDownToggleLocator;
    }

    public By getBtnContinueLocator() {
        return btnContinue;
    }

    public By getBtnContinueRegister() {
        return btnContinueRegister;
    }

    public By getInputFirstNameLocator() {
        return inputFirstNameLocator;
    }

    public By getInputLastNameLocator() {
        return inputLastNameLocator;
    }

    public By getAlertEmailAddressIsAlreadyRegistered() {
        return alertEmailAddressIsAlreadyRegistered;
    }

    public By getInputPasswordLocator() {
        return inputPasswordLocator;
    }

    public By getInputEmailLocator() {
        return inputEmailLocator;
    }

    public By getAgreeSliderLocator() {
        return agreeSliderLocator;
    }

    public By getInscriptionYourAccountHasBeenCreated() {
        return inscriptionYourAccountHasBeenCreated;
    }


}











