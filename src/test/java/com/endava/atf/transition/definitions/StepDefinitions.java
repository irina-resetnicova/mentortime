package com.endava.atf.transition.definitions;

import com.endava.atf.transition.drivers.Driver;
import com.endava.atf.transition.testDataUI.RegistrationPage;
import com.endava.atf.transition.testDataUI.UserDao;
import com.endava.atf.transition.utils.Helper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    //    private final RegistrationPage registrationPage = new RegistrationPage();
    private static final Logger log = LogManager.getLogger(StepDefinitions.class);
    private final WebDriver driver;
    private final UserDao query;
    private final RegistrationPage registrationPage;

    public StepDefinitions(UserDao query) {
        this.driver = Driver.getDriver();
        this.query = query;
        this.registrationPage = new RegistrationPage(driver);
    }


    @Given("User is on Register page")
    public void userIsOnRegisterPage() {
        try {
            Helper.openRegisterPage();
            log.info("User is on register page");
        } catch (RuntimeException e) {
            log.error(String.valueOf(e));
            throw e;
        }
    }

//    Scenario Outline: A new User is successfully registered

    @Given("User does not have any ACCOUNTS")
    public void userDoesNotHaveAnyACCOUNTS() {

        log.info("User does not have any ACCOUNTS");

    }

    @When("User registers")
    public void userRegisters(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            registrationPage.fillRegistrationForm(
                    row.get("firstName"),
                    row.get("lastName"),
                    row.get("email"),
                    row.get("password")
            );

            log.info("User registers: firstName, lastName, email, password");
            log.info("User moves slider on the rightSide");
            log.info("User presses the btn Continue");
        }
    }

    @Then("User is relocated on the page Your Account Has Been Created!")
    public void userIsRelocatedOnThePage() {
        log.info("User is relocated on the page Your Account Has Been Created!");

        System.out.println("current page" + driver.getCurrentUrl());
        String expectedURL = "http://localhost:8080/en-gb?route=account/register";
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals("The URLs are not equals", expectedURL, currentURL);

            log.info("User relocated onto another page");

    }

    @Then("The inscription {} is appeared on the screen")
    public void theInscriptionIsAppearedOnTheScreen(String string) {
        log.info("The inscription Your Account Has Been Created! is appeared on the screen");


        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        WebElement getActualElement = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getInscriptionYourAccountHasBeenCreated()));
        assertEquals(string, getActualElement.getText());
        registrationPage.yourAccountHasBeenCreated();

        try {
            Helper.openRegisterPage();
            log.info("User is on register page");
        } catch (RuntimeException e) {
            log.error(String.valueOf(e));
            throw e;
        }
    }

    @Then("User is registered")
    public void userIsRegistered() throws SQLException {
        log.info("User is registered");
        ResultSet rsSelectAll = query.getCustomerByEmail("john@gmail.com").executeQuery();

        try {
            while (rsSelectAll.next()) {
                System.out.println();
                System.out.println("firstname: " + rsSelectAll.getString("firstname"));
                System.out.println("lastname: " + rsSelectAll.getString("lastname"));
                System.out.println("email: " + rsSelectAll.getString("email"));
                System.out.println("password: " + rsSelectAll.getString("password"));
                System.out.println();
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new SQLException(ex);
        } finally {
            rsSelectAll.close();
        }
    }

//    Scenario Outline: User ca not be registered if first name does not confirm requirements

    @Given("User does not have any account with {}")
    public void userDoesNotHaveAccount(String email) throws SQLException {

        log.info("User does not have any account");
        int rsDeleteAll = query.deleteAllByEmail(email).executeUpdate();

    }

    @When("User registers with wrong firstname")
    public void userRegistersWithWrongFirstname(DataTable table) {

        List<Map<String, String>> rows = table.asMaps(String.class, String.class); // convert data table into List(Map)

        for (Map<String, String> row : rows) {
            registrationPage.fillRegistrationForm(
                    row.get("firstName"),
                    row.get("lastName"),
                    row.get("email"),
                    row.get("password")
            );

            log.info("User registers: firstName, lastName, email, password");
            log.info("User moves slider on the rightSide");
            log.info("User presses the btn Continue");
        }

    }

    @Then("User is not registered with entered {}")
    public void userIsNotRegisteredWithEnteredCredential(String email) throws SQLException {

        ResultSet rsSelect = query.selectAllUsersByEmail(email).executeQuery();

        while (rsSelect.next()) {
            log.error("email: " + rsSelect.getString("email"));
            System.out.println();

        }
        rsSelect.close();

        ResultSet rsCountAll = query.countUsersByEmail(email).executeQuery();

        while (rsCountAll.next()) {
            String eMail = rsCountAll.getString("email");
            int userCount = rsCountAll.getInt("user_count");

            int expectUserCount = 1;
            Assert.assertEquals(userCount, expectUserCount);

            if (userCount == 1) {
                log.info("User can not register with existing account");
            } else {
                log.error("User registered with existing account");
            }

        }
    }

    @Then("A warning message {} is appeared on the screen")
    public void aWarningMessageWarningMessageIsAppearedOnTheScreen(String warningMessage) {
        log.info("The warning message <First Name must be between 1 and 32 characters!> appears on the screen");
        WebElement element1 = driver.findElement(registrationPage.getLocatorByName(warningMessage));
        String warningAsText1 = element1.getText();

        String warningAsText2 = "First Name must be between 1 and 32 characters!";

        if (warningAsText1.equals(warningAsText2)) {
            log.info("The warning messages are equals");
        } else {
            log.error("The warning messages are not equals");
        }
        Assert.assertEquals(warningAsText1, warningAsText2);

    }


// Scenario: Registration with Existing User

    @Given("User has already its account")
    public void userIsAlreadyRegistered() throws SQLException {

        int rsDelete = query.deleteCustomerByEmail("john@gmail.com").executeUpdate();
        int rsInsert = query.insertCustomer("John", "Baiden", "john@gmail.com", "1234").executeUpdate();

        log.info("User has already its account");

    }


    @When("User try to register with existing account {}, {}, {}, {}")
    public void userTryToRegisterWithExistingAccountFirstNameLastNameEmailPassword(String firstname, String lastname, String email, String password) {


        registrationPage.fillRegistrationForm(firstname, lastname, email, password);

        log.info("User registers: firstName, lastName, email, password");
        log.info("User moves slider on the rightSide");
        log.info("User presses the btn Continue");
        log.info("User try to register with existing account");
    }


    @Then("User is not registered with existing {}")
    public void userIsNotRegisteredWithExistingEmail(String email) throws SQLException {
        ResultSet rsSelect = query.getCustomerByEmail(email).executeQuery();
//        System.out.println("List of customers with email: " + query.getEmailValue());

        while (rsSelect.next()) {
            System.out.println("\u001B[33mfirstname: " + rsSelect.getString("firstname") + "\u001B[0m");
            System.out.println("\u001B[33mlastname: " + rsSelect.getString("lastname") + "\u001B[0m");
            System.out.println("\u001B[33memail: " + rsSelect.getString("email") + "\u001B[0m");
            System.out.println("\u001B[33mpassword: " + rsSelect.getString("password") + "\u001B[0m");
            System.out.println();

        }
        rsSelect.close();

        ResultSet rsCount = query.countUsersByEmail(email).executeQuery();

        while (rsCount.next()) {
            String eMail = rsCount.getString("email");
            int userCount = rsCount.getInt("user_count");

            System.out.printf("Count of customers with email %s: %d%n%n", email, userCount);

            int expectUserCount = 1;
            Assert.assertEquals(userCount, expectUserCount);

            if (userCount == 1) {
                log.info("User can not register with existing account");
            } else {
                log.error("User registers with existing account");
            }
        }
    }

    @Then("User does not relocated onto another page")
    public void notRelocatedOntoAnotherPage() {
        String expectedURL = "http://localhost:8080/en-gb?route=account/register";
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(expectedURL, currentURL);

        if (expectedURL.equals(currentURL)) {
            log.info("User does not relocated onto another page");
        } else {
            log.error("The URLs are not equals");
        }
    }

    @Then("Warning message {} is appeared on the screen")
    public void aWarningEMailAddressIsAlreadyRegisteredIsAppearedOnTheScreen(String alert) {

        String getActualWarningMessage = driver.findElement(registrationPage.getAlertEmailAddressIsAlreadyRegistered()).getText();
        assertEquals(alert, getActualWarningMessage);
        log.info("Warning message <Warning: E-Mail Address is already registered!> is appeared on the screen");
    }


}


















