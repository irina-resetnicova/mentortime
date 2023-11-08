package com.endava.atf.transition.testDataUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InscriptionPage extends BasePage {
    //    Конструктор будет вызываться при создании нового объекта RegistrationPage
    public InscriptionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this); // Инициализация элементов страницы представленные аннотациями @FindBy
    }

    @FindBy(xpath = ("//h1[text()='Your Account Has Been Created!']"))
    public WebElement inscriptionYourAccountHasBeenCreated;

    @FindBy(xpath = ("//a[contains(text(),'Continue')]"))
    public WebElement btnContinue;

    public WebElement getInscriptionYourAccountHasBeenCreated() {
        return inscriptionYourAccountHasBeenCreated;
    }

    public void yourAccountHasBeenCreated() {
        clickElement(btnContinue);


    }

}











