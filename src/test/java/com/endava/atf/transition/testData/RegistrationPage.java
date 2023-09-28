package com.endava.atf.transition.testData;
import com.endava.atf.transition.config.Browser;
import com.endava.atf.transition.config.WebDriverFactory;
import org.openqa.selenium.*;


public class RegistrationPage {

    private static WebDriver webdriver;
    static {
        webdriver = WebDriverFactory.getDriver(Browser.CHROME);
    }

    private By inputFirstName = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-firstname']");
    private By inputLastName = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-lastname']");
    private By inputEmail = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-email']");
    private By inputPassword = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-password']");
    private By btnAgree = By.xpath("//*[@id=\"form-register\"]/div/div/input");
    private By btnContinue = By.xpath("//*[@id=\"form-register\"]/div/button");
    private By inscriptionYourAccountHasBeenCreated = By.xpath("//*[text()=‘Your Account Has Been Created!’]");

    private By inscriptionFNBoundary = By.xpath("//*[@id=\"error-firstname\"]");
    private By inscriptionLNBoundary = By.xpath("//*[@id=\"error-lastname\"]");
    private By inscriptionEMail = By.xpath("//*[@id=\"error-email\"]");
    private By inscriptionPassword= By.xpath("//*[@id=\"error-password\"]");

    private String findByNamePattern = "//*[contains(text(),'%s')]";

    public By getInputFirstName() {
        return inputFirstName;
    }

    public By getInputLastName() {
        return inputLastName;
    }

    public By getInputPassword() {
        return inputPassword;
    }

    public By getInputEmail() {
        return inputEmail;
    }

    public By getBtnAgree() {
        return btnAgree;
    }

    public By getInscriptionYourAccountHasBeenCreated() {
        return inscriptionYourAccountHasBeenCreated;
    }

    public By getBtnContinue() {
        return btnContinue;

    }

    public By getInscriptionFNBoundary() {
        return inscriptionFNBoundary;
    }

    public By getInscriptionLNBoundary() {
        return inscriptionLNBoundary;
    }

    public By getInscriptionEMail() {
        return inscriptionEMail;
    }

    public By getInscriptionPassword() {
        return inscriptionPassword;
    }

    public By getLocatorByName(String  warningMessage){
        By warningMessageLocator = By.xpath(String.format(findByNamePattern, warningMessage));
        return warningMessageLocator;

    }
}











