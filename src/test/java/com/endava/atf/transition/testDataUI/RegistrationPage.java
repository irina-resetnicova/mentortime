package com.endava.atf.transition.testDataUI;
import com.endava.atf.transition.drivers.DriverProvider;
import com.endava.atf.transition.drivers.WebDriverFactory;
import org.openqa.selenium.*;


public class RegistrationPage {

    private static WebDriver webdriver;
    static {
        webdriver = WebDriverFactory.getDriver(DriverProvider.CHROME);
    }

    private By inputFirstNameLocator = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-firstname']");
    private By inputLastNameLocator = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-lastname']");
    private By inputEmailLocator = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-email']");
    private By inputPasswordLocator = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-password']");
    private By agreeSliderLocator = By.xpath("//*[@id='form-register']/div/div/input");
    private By inscriptionYourAccountHasBeenCreated = By.xpath("//*[@id=\"content\"]/h1");
    private By btnContinue = By.xpath("//*[@id=\"content\"]/div/a");
    private By btnContinueRegister = By.xpath("//*[@id=\"form-register\"]/div/button");
    private By btnContinueDropDownLocator = By.xpath("//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/ul/li[5]/a");
    private By dropDownLocator = By.xpath("//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/a/span");
    private By logoutLocator = By.xpath("//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/ul/li[5]/a");

    private By alertEmailAddressIsAlreadyRegistered = By.xpath("//*[@id=\"alert\"]");

    //First Name must be between 1 and 32 characters!
    private String findByNamePattern = "//*[@id=\"error-firstname\"]";
    public By getLocatorByName(String  warningMessage){
        By warningMessageLocator = By.xpath(String.format(findByNamePattern, warningMessage));
        return warningMessageLocator;
    }

    public By getLogoutLocator() {
        return logoutLocator;
    }

    public By getDropDownLocator() {
        return dropDownLocator;
    }
    public By getBtnContinueDropDownLocator() {
        return btnContinueDropDownLocator;
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











