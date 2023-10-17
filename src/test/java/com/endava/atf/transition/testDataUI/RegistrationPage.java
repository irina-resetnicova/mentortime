package com.endava.atf.transition.testDataUI;
import com.endava.atf.transition.config.DriverProvider;
import com.endava.atf.transition.config.WebDriverFactory;
import org.openqa.selenium.*;


public class RegistrationPage {

    private static WebDriver webdriver;
    static {
        webdriver = WebDriverFactory.getDriver(DriverProvider.CHROME);
    }

    private By inputFirstName = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-firstname']");
    private By inputLastName = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-lastname']");
    private By inputEmail = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-email']");
    private By inputPassword = By.xpath("//div[@class = 'col-sm-10']//input[@id ='input-password']");
    private By sliderAgree = By.xpath("//*[@id=\"form-register\"]/div/div/input");
    private By btnContinue = By.xpath("//*[@id=\"form-register\"]/div/button");
    private By inscriptionYourAccountHasBeenCreated = By.xpath("//*[@id=\"content\"]/h1");
//    private By inscriptionYourAccountHasBeenCreated = By.xpath("//*[@id=\"content\"]/h1");
//    private By inscriptionYourAccountHasBeenCreated = By.cssSelector("Your Account Has Been Created!");

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

    public By getSliderAgree() {
        return sliderAgree;
    }

    public By getInscriptionYourAccountHasBeenCreated() {
        return inscriptionYourAccountHasBeenCreated;
    }

    public By getBtnContinue() {
        return btnContinue;

    }


    public By getLocatorByName(String  warningMessage){
        By warningMessageLocator = By.xpath(String.format(findByNamePattern, warningMessage));
        return warningMessageLocator;

    }

}











