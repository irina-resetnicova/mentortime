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

        submitElement(btnContinueRegister);

    }

    public String getActualInscription() {
        return getTextOfString(inscriptionYourAccountHasBeenCreated);
    }

    public String getActualAlert() {
        return getTextOfMessage(alertEmailAddressIsAlreadyRegistered);
    }



    public void yourAccountHasBeenCreated() {

        clickElement(btnContinue);
        clickElement(btnDropDownToggleLocator);
        clickElement(logoutLocator);
        clickElement(btnContinueAccountLogout);

    }

    public By getLocatorByName(String warningMessage) {
        By warningMessageLocator = By.xpath(String.format(findByNamePattern, warningMessage));
        return warningMessageLocator;
    }



    public By getBtnContinueAccountLogout() {
        return btnContinueAccountLogout;
    }




    //First Name must be between 1 and 32 characters!


    public By getAlertEmailAddressIsAlreadyRegistered() {
        return alertEmailAddressIsAlreadyRegistered;
    }


    public By getInscriptionYourAccountHasBeenCreated() {
        return inscriptionYourAccountHasBeenCreated;
    }


}











