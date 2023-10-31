package com.endava.atf.transition.testDataUI;

import org.openqa.selenium.*;

public class RegistrationPage {

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

    public By getBtnContinueAccountLogout() {
        return btnContinueAccountLogout;
    }

    public String getFindByNamePattern() {
        return findByNamePattern;
    }

    private final By alertEmailAddressIsAlreadyRegistered = By.xpath("//*[@id=\"alert\"]");

    //First Name must be between 1 and 32 characters!

    private String findByNamePattern = "//*[@id=\"error-firstname\"]";
    public By getLocatorByName(String  warningMessage){
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











